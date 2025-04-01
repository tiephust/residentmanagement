import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import RegisterForm from '../features/auth/components/RegisterForm';
import './Register.css';

const Register = ({ isAdmin = false }) => {
    const { register } = useAuth();
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleSubmit = async (userData) => {
        try {
            await register(userData, isAdmin);
            navigate(isAdmin ? '/admin/dashboard' : '/dashboard');
        } catch (err) {
            setError(err.message || 'Registration failed');
        }
    };

    return (
        <div className="register-page">
            <h1>{isAdmin ? 'Admin Registration' : 'User Registration'}</h1>
            {error && <div className="error-message">{error}</div>}
            <RegisterForm onSubmit={handleSubmit} isAdmin={isAdmin} />
        </div>
    );
};

export default Register;