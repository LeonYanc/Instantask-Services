import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import InviteUserModal from './InviteUserModal';
import ChangeUserModal from './ChangeUserModal';
import './UserListPage.css';

/** User details card component */
function UserDetailCard({ user, currentUser, currentUserBoardRole, cardRef, onChangeClick, onRemoveClick }) {
    if (!user) return null;
    const { name = 'Unknown', email = 'unknown@example.com' } = user;
    const initials = name
        .split(' ')
        .map(part => part[0]?.toUpperCase())
        .join('')
        .slice(0, 2);

    // Display button based on condition
    let actionButton = null;
    if (currentUser && user.id === currentUser.id) {
        // Currently viewing yourself: display the Change button
        actionButton = (
            <button className="changesButton" onClick={() => onChangeClick(user)}>
                Change
            </button>
        );
    } else if (currentUser && currentUserBoardRole === 'ADMIN' && user.role !== 'ADMIN') {
        // The current user's role in this board is ADMIN and the role of the user being viewed is not ADMIN: Display the Remove user button
        actionButton = (
            <button className="changesButton" onClick={() => onRemoveClick(user)}>
                Remove user
            </button>
        );
    }

    return (
        <div ref={cardRef} className="userDetailCardInRow">
            <div className="avatarCircle">
                <div className="avatarText">{initials}</div>
            </div>
            <div className="infoLine">
                <strong>Full name:</strong> {name}
            </div>
            <div className="infoLine">
                <strong>Email address:</strong> {email}
            </div>
            {actionButton}
        </div>
    );
}



function UserListPage() {

    const [board, setBoard] = useState(null);
    // Original user list (from user-access)
    const [users, setUsers] = useState([]);
    // Search keywords
    const [searchTerm, setSearchTerm] = useState('');
    const [totalUsers, setTotalUsers] = useState(0);
    const [adminCount, setAdminCount] = useState(0);
    // Invite pop-up status
    const [showInviteModal, setShowInviteModal] = useState(false);
    // The user id displayed in the current details card
    const [detailUserId, setDetailUserId] = useState(null);
    const cardRef = useRef(null);
    // The currently logged in user (read from localStorage)
    const [currentUser, setCurrentUser] = useState(null);
    // Control ChangeUserModal
    const [showChangeModal, setShowChangeModal] = useState(false);
    const [userToChange, setUserToChange] = useState(null);

    const boardId = '67aa82366bdfc20047e57ec3';

    useEffect(() => {
        const stored = localStorage.getItem('currentUser');
        if (stored) {
            setCurrentUser(JSON.parse(stored));
        }
        loadBoardInfo();
        loadBoardUsers();
    }, []);

    function loadBoardInfo() {
        axios.get(`/boards/${boardId}`)
            .then(res => setBoard(res.data))
            .catch(err => console.error(err));
    }

    function loadBoardUsers() {
        axios.get(`/api/user-access/board/${boardId}/user-list`)
            .then(res => {
                const data = res.data;
                setTotalUsers(data.totalUsers);
                setAdminCount(data.totalAdmins);
                setUsers(data.userList || []);
            })
            .catch(err => console.error(err));
    }

    function handleInviteClick() {
        setShowInviteModal(true);
    }

    function handleActionsClick(id) {
        setDetailUserId(detailUserId === id ? null : id);
    }

    useEffect(() => {
        function handleClickOutside(e) {
            if (detailUserId !== null && cardRef.current && !cardRef.current.contains(e.target)) {
                setDetailUserId(null);
            }
        }
        document.addEventListener('mousedown', handleClickOutside);
        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, [detailUserId]);

    // Search filter
    const filteredUsers = users.filter(u => {
        const term = searchTerm.toLowerCase();
        return (
            u.name.toLowerCase().includes(term) ||
            u.email.toLowerCase().includes(term)
        );
    });

    // Calculate the role of the currently logged in user in the current board and search from the board user list
    const currentUserBoardRole = currentUser ? users.find(u => u.id === currentUser.id)?.role : null;

    function handleRemoveUser(userToRemove) {
        if (!window.confirm(`Are you sure to remove ${userToRemove.name}?`)) return;
        axios.delete('/api/user-access/remove', {
            params: { userId: userToRemove.id, boardId }
        })
            .then(() => {
                alert(`Removed ${userToRemove.name} successfully!`);
                loadBoardUsers();
                setDetailUserId(null);
            })
            .catch(err => {
                alert('Failed to remove user');
                console.error(err);
            });
    }

    function handleOpenChangeModal(user) {
        setUserToChange(user);
        setShowChangeModal(true);
        setDetailUserId(null);
    }

    return (
        <div className="desktop-1">
            <div className="rectangle1" />

            {board && (
                <>
                    <div className="user2">{board.name}</div>
                    <div className="manageproductaccessforalltheusersinyouorganization">
                        Manage product access for all the users in your organization
                    </div>
                </>
            )}

            <div className="totalUserLabel">Total user: {filteredUsers.length}</div>
            <div className="administratorsLabel">Administrators: {adminCount}</div>

            <div className="search-invite-row">
                <div className="rectangle3">
                    <div className="searchIconArea"></div>
                    <input
                        className="searchInput"
                        type="text"
                        placeholder="Enter public name or email address"
                        value={searchTerm}
                        onChange={e => setSearchTerm(e.target.value)}
                    />
                </div>
                <div className="component2">
                    <div className="rectangle2">
                        <div className="inviteusers" onClick={handleInviteClick}>
                            Invite Users
                        </div>
                    </div>
                </div>
            </div>

            <div className="rectangle5">
                <div className="user5">User</div>
                <div className="lastactive">Last active</div>
                <div className="status">Role</div>
                <div className="actions">Actions</div>
            </div>

            <div className="user-list-container">
                {filteredUsers.map(u => (
                    <div className="frame1" key={u.id}>
                        <div className="userRowNameEmail">
                            <div>
                                {u.name}
                                <br />
                                <small style={{ fontSize: '16px', color: '#666' }}>
                                    {u.email}
                                </small>
                            </div>
                        </div>
                        <div className="userRowLastActive">
                            {u.lastActiveTime || 'N/A'}
                        </div>
                        <div className="userRowStatus">
                            {u.role || 'active'}
                        </div>
                        <div className="rowActions" onClick={() => handleActionsClick(u.id)}>
                            <div className="actionRect" />
                            <div className="actionDots">...</div>
                            {detailUserId === u.id && (
                                <UserDetailCard
                                    user={u}
                                    currentUser={currentUser}
                                    currentUserBoardRole={currentUserBoardRole}
                                    cardRef={cardRef}
                                    onChangeClick={handleOpenChangeModal}
                                    onRemoveClick={handleRemoveUser}
                                />
                            )}
                        </div>
                    </div>
                ))}
            </div>

            {showInviteModal && (
                <InviteUserModal
                    onClose={() => setShowInviteModal(false)}
                    onInvited={() => {
                        setShowInviteModal(false);
                        loadBoardUsers();
                    }}
                    boardId={boardId}
                    existingUsers={users}
                />
            )}

            {showChangeModal && userToChange && (
                <ChangeUserModal
                    user={userToChange}
                    onClose={() => setShowChangeModal(false)}
                    onUpdated={loadBoardUsers}
                />
            )}
        </div>
    );
}

export default UserListPage;
