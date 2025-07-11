import React from 'react';
import NavBar from "../navBar/NavBar";
import './MainLayout.scss';

const MainLayout = ({children}) => {
    return (
        <div className="layout">
            <NavBar />
            <div className="content">
                {children}
            </div>

        </div>
    );
};

export default MainLayout;