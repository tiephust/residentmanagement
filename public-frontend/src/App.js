import { Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './components/routes/ProtectedRoute';
import MainLayout from './layouts/MainLayout';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import AdminRegister from './pages/AdminRegister';
import Dashboard from './pages/Dashboard';
import ForgotPassword from './pages/ForgotPassword';
import HomeLayout from './layouts/HomeLayout';
import './App.css';

function App() {
    return (
        <AuthProvider>
            <Routes>
                <Route path="/" element={<Home />} />

                {/* Protected routes */}
                <Route element={<MainLayout />}>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/admin/register"
                       element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <AdminRegister />
                            </ProtectedRoute>
                        }
                    />
                    <Route path="/forgot-password" element={<ForgotPassword />} />
                    <Route
                        path="/dashboard"
                        element={
                            <ProtectedRoute>
                                <Dashboard />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/admin/dashboard"
                        element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <Dashboard />
                            </ProtectedRoute>
                        }
                    />
                </Route>
            </Routes>
        </AuthProvider>
    );
}

export default App;