import React from 'react';
import "./breadcrumbs.scss"
import classNames from "classnames";
import {ReactComponent as arrowIcon} from "../../../assets/arrow.svg";
import Icon from "../../commonElements/icon/Icon";
import Button from "../../commonElements/button/Button";

const Breadcrumbs = ({
                         className,
                         title,
                         subtitle,
                         icon,
                         children,
                         link
                     }) => {
    return (
        <div className={classNames("breadcrumbs", className)}>
            <div className={classNames({active: !subtitle})}>
                {icon}
            </div>
            <Button variant="breadcrumbs" type="button" onClick={link}>
                <span className={classNames({active: !subtitle})}>{title}</span>
            </Button>
            {subtitle &&
                <span className="active">
                    <Icon className="arrow" icon={arrowIcon}/>
                    {subtitle}
                </span>}
            {children}
        </div>
    );
};

export default Breadcrumbs;