import { Outlet } from 'react-router-dom';
import Header from '../components/layout/Header';
import Footer from '../components/layout/Footer';
import './MainLayout.css';

const MainLayout = () => {
    return (
        <div className="app-container">
            <Header showAuthButtons={false} />
            <main className="main-content">
                <Outlet />
            </main>
            <Footer />
        </div>
    );
};

export default MainLayout;