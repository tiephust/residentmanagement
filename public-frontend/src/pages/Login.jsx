import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import LoginForm from '../features/auth/components/LoginForm';
import './Login.css';
import RegisterForm from "../features/auth/components/RegisterForm";

const Login = () => {
    const { login } = useAuth();
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleSubmit = async (credentials) => {
        try {
            await login(credentials);
            navigate('/dashboard');
        } catch (err) {
            setError(err.message || 'Login failed');
        }
    };

    return (
        <div className="login-container">
            <h1>Login</h1>
            {error && <div className="error-message">{error}</div>}
            <LoginForm onSubmit={handleSubmit} />
        </div>
    );
};

export default Login;