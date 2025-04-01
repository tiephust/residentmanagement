import api from '../../../lib/axios';

export const login = async (credentials) => {
    const response = await api.post('/login', credentials, {
        withCredentials: true // Để nhận cookie từ server
    });
    return response.data;
};

export const registerUser = async (userData) => {
    const response = await api.post('/user/register', userData);
    return response.data;
};

export const registerAdmin = async (adminData) => {
    const response = await api.post('/admin/register', adminData);
    return response.data;
};

export const logout = async () => {
    const response = await api.post('/logout', {}, {
        withCredentials: true
    });
    return response.data;
};

export const forgotPassword = async (username, phone) => {
    const response = await api.post('/forgot-password', { username, phone });
    return response.data;
};

export const getCurrentUser = async () => {
    try {
        const response = await api.get('/user/dashboard');
        return response.data;
    } catch (error) {
        return null;
    }
};