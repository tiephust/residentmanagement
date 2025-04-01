import { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import * as authService from '../features/auth/services/authService';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const loadUser = async () => {
            try {
                const userData = await authService.getCurrentUser();
                setUser(userData);
            } catch (err) {
                console.error('Failed to load user', err);
            } finally {
                setLoading(false);
            }
        };

        loadUser();
    }, []);

    const login = async (credentials) => {
        const response = await authService.login(credentials);
        const userData = await authService.getCurrentUser();
        setUser(userData);
        return response;
    };

    const register = async (userData, isAdmin = false) => {
        if (isAdmin) {
            await authService.registerAdmin(userData);
        } else {
            await authService.registerUser(userData);
        }
    };

    const logout = async () => {
        await authService.logout();
        setUser(null);
    };

    const forgotPassword = async (username, phone) => {
        try {
            await authService.forgotPassword(username, phone);
            return { success: true };
        } catch (err) {
            throw new Error(err.message || 'Failed to process password reset');
        }
    };

    const value = {
        user,
        loading,
        login,
        register,
        logout,
        forgotPassword
    };

    return (
        <AuthContext.Provider value={value}>
            {!loading && children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);