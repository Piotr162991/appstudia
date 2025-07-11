import React, {useState} from 'react';
import {Link} from "react-router-dom";
import './NavBar.scss';

const PrivateNavBar = () => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-logo">
                    WorkForce Pro
                </Link>

                <div className="menu-icon" onClick={toggleMenu}>
                    <i className={isMenuOpen ? 'fas fa-times' : 'fas fa-bars'}></i>
                </div>
                <ul className={isMenuOpen ? 'nav-menu active' : 'nav-menu'}>
                    <li className="nav-item">
                        <Link to="/logged/dashboard" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            Dashboard
                        </Link>
                    </li>
                </ul>

                <ul className={isMenuOpen ? 'nav-menu active' : 'nav-menu'}>
                    <li className="nav-item">
                        <Link to="/logged/menage-offers" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            Zarządzaj ogłoszeniami
                        </Link>
                    </li>
                </ul>


                <div className="login-button">
                    <Link to="/" className="login-link">
                        Wyloguj
                    </Link>
                </div>
            </div>
        </nav>
    );
};

export default PrivateNavBar;