import React from 'react';
import "./mainLayout.scss";
import Navbar from "../navbar/Navbar";


const MainLayout = ({
                        children,
                    }) => {

    return (
        <div className="mainLayout">
            <Navbar/>
            <div className='content'>
                {children}
            </div>
        </div>
    );
};

export default MainLayout;