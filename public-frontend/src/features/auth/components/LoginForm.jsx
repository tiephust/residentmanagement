import { useState } from 'react';
import Input from '../../../components/common/Input';
import Button from '../../../components/common/Button';
import { Link } from 'react-router-dom';
import './LoginForm.css';

const LoginForm = ({ onSubmit, loading = false }) => {
    const [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = validate(credentials);

        if (Object.keys(validationErrors).length === 0) {
            onSubmit(credentials);
        } else {
            setErrors(validationErrors);
        }
    };

    const validate = (values) => {
        const errors = {};
        if (!values.username) errors.username = 'Username is required';
        if (!values.password) errors.password = 'Password is required';
        return errors;
    };

    return (
        <form onSubmit={handleSubmit} className="login-form">
            <Input
                name="username"
                value={credentials.username}
                onChange={handleChange}
                label="Username"
                error={errors.username}
                required
            />
            <Input
                type="password"
                name="password"
                value={credentials.password}
                onChange={handleChange}
                label="Password"
                error={errors.password}
                required
            />

            <div className="forgot-password-link">
                <Link to="/forgot-password">Forgot password?</Link>
            </div>

            <div className="form-actions">
                <Button type="submit" variant="primary" disabled={loading}>
                    {loading ? 'Logging in...' : 'Login'}
                </Button>
                <Link to="/register">
                    <Button type="button" variant="secondary">
                        Register
                    </Button>
                </Link>
            </div>
        </form>
    );
};

export default LoginForm;