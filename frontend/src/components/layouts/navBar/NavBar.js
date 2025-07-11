import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './NavBar.scss';

const NavBar = () => {
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
                        <Link to="/" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            Strona główna
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/ogloszenia" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            Ogłoszenia
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/employer" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            Pracodawcy
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/o-nas" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            O nas
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/kontakt" className="nav-link" onClick={() => setIsMenuOpen(false)}>
                            Kontakt
                        </Link>
                    </li>
                </ul>

                <div className="login-button">
                    <Link to="/login" className="login-link">
                        Zaloguj się
                    </Link>
                </div>
            </div>
        </nav>
    );
};

export default NavBar;