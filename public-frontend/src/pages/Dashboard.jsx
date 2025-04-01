import { useAuth } from '../context/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
    const { user } = useAuth();

    return (
        <div className="dashboard-container">
            <div className="dashboard-header">
                <h1 className="dashboard-title">Welcome, {user?.username}</h1>
                <span className="user-role">{user?.role}</span>
            </div>

            <div className="dashboard-content">
                <div className="dashboard-sidebar">
                    <h3>Navigation</h3>
                    <ul>
                        <li>Profile</li>
                        <li>Settings</li>
                        {user?.role === 'ADMIN' && <li>Admin Panel</li>}
                    </ul>
                </div>

                <div className="dashboard-main">
                    <h2>Your Dashboard</h2>
                    <p>Here you can manage your account and activities.</p>

                    {user?.role === 'ADMIN' && (
                        <div className="admin-features">
                            <h3>Admin Features</h3>
                            <ul>
                                <li>User Management</li>
                                <li>System Settings</li>
                                <li>Reports</li>
                            </ul>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Dashboard;