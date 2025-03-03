import React, { useState } from 'react';
import axios from 'axios';
import './InviteUserModal.css';

function InviteUserModal({ onClose, onInvited }) {
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('admin');

    const handleInvite = () => {
        axios.post('/api/users/invite', { email })
            .then(res => {
                alert('Invited user: ' + email);
                onInvited();
            })
            .catch(err => {
                console.error(err);
                alert(err.response?.data || 'Invite failed');
            });
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
