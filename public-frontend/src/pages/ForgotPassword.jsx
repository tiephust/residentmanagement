import { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import Input from '../components/common/Input';
import Button from '../components/common/Button';
import './ForgotPassword.css';

const ForgotPassword = () => {
    const { forgotPassword } = useAuth();
    const [formData, setFormData] = useState({
        username: '',
        phone: ''
    });
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await forgotPassword(formData.username, formData.phone);
            setSuccess('Password reset instructions have been sent');
            setError('');
        } catch (err) {
            setError(err.message || 'Failed to process password reset');
            setSuccess('');
        }
    };

    return (
        <div className="forgot-password-container">
            <h1>Forgot Password</h1>
            {error && <div className="error-message">{error}</div>}
            {success && <div className="success-message">{success}</div>}
            <form onSubmit={handleSubmit}>
                <Input
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    label="Username"
                    required
                />
                <Input
                    name="phone"
                    value={formData.phone}
                    onChange={handleChange}
                    label="Phone Number"
                    required
                />
                <Button type="submit" variant="primary">
                    Submit
                </Button>
            </form>
        </div>
    );
};

export default ForgotPassword;