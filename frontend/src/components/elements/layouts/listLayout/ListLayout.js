import React from 'react';
import classNames from "classnames";
import './listLayout.scss'

const ListLayout = ({
                        children,
                        className
                    }) => {
    return (
        <div className={classNames("list", className)}>
            {children}
        </div>
    );
};

export default ListLayout;