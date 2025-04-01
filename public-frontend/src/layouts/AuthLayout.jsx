import { Outlet, Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './AuthLayout.css';

const AuthLayout = () => {
    const { user } = useAuth();

    if (user) {
        return <Navigate to="/" replace />;
    }

    return (
        <div className="auth-container">
            <div className="auth-card">
                <Outlet />
            </div>
        </div>
    );
};

export default AuthLayout;