import React from 'react';
import "./dualColumnLayout.scss"
import classNames from "classnames";

const DualColumnLayout = ({
                              children,
                              className
                          }) => {
    return (
        <div className={classNames("dual-column-layout", className)}>
            {children}
        </div>
    );
};

export default DualColumnLayout;