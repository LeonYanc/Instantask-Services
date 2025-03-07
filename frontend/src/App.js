import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Login';
import UserListPage from './UserListPage';
import './App.css';

function App() {
    return (
        <BrowserRouter>
            <div className="app-wrapper">
                <div className="app-content">
                    <main className="app-main">
                        <Routes>
                            <Route path="/" element={<Login />} />
                            <Route path="/UserListPage" element={<UserListPage />} />
                        </Routes>
                    </main>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
