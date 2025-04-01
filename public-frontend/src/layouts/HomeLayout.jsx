import { Outlet } from 'react-router-dom';
import './MainLayout.css';

const HomeLayout = () => {
    return (
        <div className="app-container">
            <h1>
                Home Layout
            </h1>
            <main className="main-container">
                <Outlet />
            </main>
        </div>
    );
};

export default HomeLayout;