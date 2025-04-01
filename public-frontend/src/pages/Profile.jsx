import { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import * as userService from '../features/user/services/userService';
import './Profile.css';

const Profile = () => {
    const { user } = useAuth();
    const [profile, setProfile] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const data = await userService.getUserProfile();
                setProfile(data);
            } catch (err) {
                setError('Failed to load profile');
            } finally {
                setLoading(false);
            }
        };

        if (user) {
            fetchProfile();
        }
    }, [user]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div className="profile-page">
            <h1>User Profile</h1>
            {profile && (
                <div className="profile-info">
                    <p><strong>Username:</strong> {profile.username}</p>
                    <p><strong>Email:</strong> {profile.email}</p>
                    <p><strong>Full Name:</strong> {profile.fullName}</p>
                </div>
            )}
        </div>
    );
};

export default Profile;