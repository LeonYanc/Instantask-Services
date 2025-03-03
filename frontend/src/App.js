import React from 'react';
import UserListPage from './UserListPage';

function App() {
  return (
      <div style={{ padding: '1rem' }}>
        <h1>Welcome to My Instantask App</h1>
        {/* 这里直接渲染用户管理页面 */}
        <UserListPage />
      </div>
  );
}

export default App;
