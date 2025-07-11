import React from 'react';
import {NavLink} from 'react-router-dom';
import './navbar.scss'
import Button from "../../commonElements/button/Button";
import Icon from "../../commonElements/icon/Icon";
import {ReactComponent as tenantsIcon} from "../../../assets/tenants.svg";
import {ReactComponent as estateIcon} from "../../../assets/estate.svg";
import {ReactComponent as settingsIcon} from "../../../assets/settings.svg";
import {ReactComponent as logoutIcon} from "../../../assets/logout.svg";


const Navbar = () => {

    const logoutHelper = ()=>{
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("refreshToken")
        sessionStorage.removeItem("jwtToken");
        sessionStorage.removeItem("refreshToken")
    }

    return (
        <div className='navbar'>
            <div className="top-section">
                <NavLink to="/estates" activeClassName="active">
                    <Button variant="nav">
                        <Icon className="nav" icon={estateIcon}/>
                        Estates
                    </Button>
                </NavLink>
                <NavLink to="/tenants" activeClassName="active">
                    <Button variant="nav">
                        <Icon className="nav" icon={tenantsIcon}/>
                        Tenants
                    </Button>
                </NavLink>
            </div>
            <div className="bottom-section">
                <NavLink to="/settings" activeClassName="active">
                    <Button variant="nav">
                        <Icon className="nav" icon={settingsIcon}/>
                        Settings
                    </Button>
                </NavLink>
                <NavLink to="/" activeClassName="active">
                    <Button variant="nav" onClick={() => logoutHelper()}>
                        <Icon className="nav" icon={logoutIcon}/>
                        Log Out
                    </Button>
                </NavLink>
            </div>
        </div>
    );
};

export default Navbar;