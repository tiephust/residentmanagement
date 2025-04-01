import { Link } from 'react-router-dom';
import './Header.css';

const Header = ({ showAuthButtons }) => {
    return (
        <header className="app-header">
            <div className="container">
                <div className="header-content">
                    <Link to="/" className="logo">Your Logo</Link>
                    <nav className="nav-menu">
                        {/* Các navigation links chung */}
                        {/*<Link to="/about" className="nav-link">About</Link>*/}
                        {/*<Link to="/services" className="nav-link">Services</Link>*/}

                        {/* Chỉ hiển thị khi showAuthButtons = true */}
                        {showAuthButtons && (
                            <>
                                <Link to="/login" className="nav-link auth-link">Login</Link>
                                <Link to="/register" className="nav-link auth-link">Register</Link>
                            </>
                        )}
                    </nav>
                </div>
            </div>
        </header>
    );
};

export default Header;