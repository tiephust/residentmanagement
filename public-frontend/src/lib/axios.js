import axios from 'axios';

const api = axios.create({
    baseURL: process.env.REACT_APP_API_URL,
    withCredentials: true // For cookies
});

// Request interceptor
api.interceptors.request.use((config) => {
    // You can add auth headers here if needed
    return config;
}, (error) => {
    return Promise.reject(error);
});

// Response interceptor
api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            // Handle unauthorized
            window.location.href = '/login';
        } else if (error.response?.status === 403) {
            // Handle forbidden (role-based)
            window.location.href = '/';
        }
        return Promise.reject(error);
    }
);

export default api;