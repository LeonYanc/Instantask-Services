import React, { useState } from 'react';
import axios from 'axios';
import './InviteUserModal.css';

function InviteUserModal({ onClose, onInvited, boardId, existingUsers }) {
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('admin');

    const handleInvite = async () => {
        // First check if the email already exists in the user list of the current board
        const alreadyInvited = existingUsers.find(
            user => user.email.toLowerCase() === email.toLowerCase()
        );
        if (alreadyInvited) {
            alert(`User with email ${email} is already in this board.`);
            return;
        }

        try {
            // 1) First check if the user exists (call /api/users/check)
            const checkRes = await axios.post('/api/users/check', { email });
            // If successful, the user exists and returns user details

            // 2) Call the invitation interface to invite the user to the current board
            const inviteDto = {
                email,
                boardId,
                role: role.toUpperCase() // Convert to uppercase,such as "ADMIN"
            };

            const inviteRes = await axios.post('/api/user-access/invite', inviteDto);
            alert(`Invited user: ${email} as ${role}`);
            onInvited();
        } catch (err) {
            console.error(err);
            if (err.response && err.response.data) {
                alert(err.response.data);
            } else {
                alert('Invite failed');
            }
        }
    };

    return (
        <div className="inviteUserModal-overlay">
            <div className="frame3">
                <div className="inviteuser">Invite User</div>
                <div className="rectangle6">
                    <input
                        type="text"
                        placeholder="Enter email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    />
                </div>
                <div className="assignrole">Assign Role</div>
                <div className="rectangle7">
                    <select value={role} onChange={e => setRole(e.target.value)}>
                        <option value="admin">Admin</option>
                        <option value="editor">Editor</option>
                        <option value="viewer">Viewer</option>
                    </select>
                </div>
                <div className="invite-buttons">
                    <button className="blue-button" onClick={handleInvite}>Invite</button>
                    <button className="gray-button" onClick={onClose}>Cancel</button>
                </div>
            </div>
        </div>
    );
}

export default InviteUserModal;
