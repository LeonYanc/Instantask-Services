import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import InviteUserModal from './InviteUserModal';
import './UserListPage.css';


function UserDetailCard({ user, cardRef }) {
    if (!user) return null;


    const { name = 'Unknown', email = 'unknown@example.com' } = user;


    const initials = name
        .split(' ')
        .map(part => part[0]?.toUpperCase())
        .join('')
        .slice(0, 2);

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
            <button className="changesButton">Changes</button>
        </div>
    );
}

function UserListPage() {
    const [users, setUsers] = useState([]);
    const [totalUsers, setTotalUsers] = useState(0);
    const [showInviteModal, setShowInviteModal] = useState(false);


    const [detailUserId, setDetailUserId] = useState(null);

    const cardRef = useRef(null);

    useEffect(() => {
        loadUsers();
    }, []);

    function loadUsers() {
        axios.get('/api/users')
            .then(res => {
                const data = res.data;
                setTotalUsers(data.totalUsers);
                setUsers(data.userList);
            })
            .catch(err => console.error(err));
    }

    function handleInviteClick() {
        setShowInviteModal(true);
    }


    function handleActionsClick(id) {
        if (detailUserId === id) {
            setDetailUserId(null);
        } else {
            setDetailUserId(id);
        }
    }

    useEffect(() => {
        function handleClickOutside(e) {
            if (detailUserId !== null && cardRef.current && !cardRef.current.contains(e.target)) {
                setDetailUserId(null);
            }
        }
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [detailUserId]);

    return (
        <div className="desktop-1">

            <div className="rectangle1" />

            <div className="user2">User</div>
            <div className="manageproductaccessforalltheusersinyouorganization">
                Manage product access for all the users in your organization
            </div>

            <div className="totalUserLabel">Total user: {totalUsers}</div>
            <div className="administratorsLabel">Administrators: ?</div>

            <div className="search-invite-row">
                <div className="rectangle3">
                    <div className="searchIconArea"></div>
                    <input
                        className="searchInput"
                        type="text"
                        placeholder="Enter public name or email address"
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
                <div className="status">Status</div>
                <div className="actions">Actions</div>
            </div>


            <div className="user-list-container">
                {users.map((u) => (
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
                        {/* LastActive */}
                        <div className="userRowLastActive">
                            {u.lastActiveTime || 'N/A'}
                        </div>
                        {/* Status */}
                        <div className="userRowStatus">
                            {u.status}
                        </div>
                        {/* Actions */}
                        <div className="rowActions" onClick={() => handleActionsClick(u.id)}>
                            <div className="actionRect" />
                            <div className="actionDots">...</div>


                            {detailUserId === u.id && (
                                <UserDetailCard user={u} cardRef={cardRef} />
                            )}
                        </div>
                    </div>
                ))}
            </div>

            {/* 弹窗：邀请用户 */}
            {showInviteModal && (
                <InviteUserModal
                    onClose={() => setShowInviteModal(false)}
                    onInvited={() => {
                        setShowInviteModal(false);
                        loadUsers();
                    }}
                />
            )}
        </div>
    );
}

export default UserListPage;
