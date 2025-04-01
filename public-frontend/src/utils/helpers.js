export const getAuthToken = () => {
    return localStorage.getItem(process.env.REACT_APP_TOKEN_KEY);
};

export const setAuthToken = (token) => {
    localStorage.setItem(process.env.REACT_APP_TOKEN_KEY, token);
};

export const clearAuthToken = () => {
    localStorage.removeItem(process.env.REACT_APP_TOKEN_KEY);
};

export const formatDate = (dateString) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
};