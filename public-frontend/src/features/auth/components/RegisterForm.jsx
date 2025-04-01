import { useState } from 'react';
import Input from '../../../components/common/Input';
import Button from '../../../components/common/Button';
import './RegisterForm.css';

const RegisterForm = ({ onSubmit, loading = false, isAdmin = false }) => {
    const [userData, setUserData] = useState({
        username: '',
        email: '',
        fullName: '',
        password: '',
        confirmPassword: '',
        phone: ''
    });
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = validate(userData);

        if (Object.keys(validationErrors).length === 0) {
            onSubmit(userData);
        } else {
            setErrors(validationErrors);
        }
    };

    const validate = (values) => {
        const errors = {};
        if (!values.username) errors.username = 'Username is required';
        if (!values.email) {
            errors.email = 'Email is required';
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(values.email)) {
            errors.email = 'Email is invalid';
        }
        if (!values.fullName) errors.fullName = 'Full name is required';
        if (!values.password) {
            errors.password = 'Password is required';
        } else if (values.password.length < 6) {
            errors.password = 'Password must be at least 6 characters';
        }
        if (values.password !== values.confirmPassword) {
            errors.confirmPassword = 'Passwords do not match';
        }
        if (isAdmin && !values.phone) {
            errors.phone = 'Phone number is required for admin';
        }
        return errors;
    };

    return (
        <form onSubmit={handleSubmit} className="register-form">
            <Input
                name="username"
                value={userData.username}
                onChange={handleChange}
                label="Username"
                error={errors.username}
                required
            />
            <Input
                name="email"
                type="email"
                value={userData.email}
                onChange={handleChange}
                label="Email"
                error={errors.email}
                required
            />
            <Input
                name="fullName"
                value={userData.fullName}
                onChange={handleChange}
                label="Full Name"
                error={errors.fullName}
                required
            />
            {isAdmin && (
                <Input
                    name="phone"
                    value={userData.phone}
                    onChange={handleChange}
                    label="Phone Number"
                    error={errors.phone}
                    required={isAdmin}
                />
            )}
            <Input
                name="password"
                type="password"
                value={userData.password}
                onChange={handleChange}
                label="Password"
                error={errors.password}
                required
            />
            <Input
                name="confirmPassword"
                type="password"
                value={userData.confirmPassword}
                onChange={handleChange}
                label="Confirm Password"
                error={errors.confirmPassword}
                required
            />
            <Button type="submit" variant="primary" disabled={loading}>
                {loading ? 'Registering...' : 'Register'}
            </Button>
        </form>
    );
};

export default RegisterForm;