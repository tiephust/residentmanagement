import './Footer.css';

const Footer = () => {
    return (
        <footer className="app-footer">
            <div className="container">
                <p>&copy; {new Date().getFullYear()} Resident Management System. All rights reserved.</p>
            </div>
        </footer>
    );
};

export default Footer;