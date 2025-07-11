import React from 'react';
import {Route, Routes} from "react-router-dom";
import PrivateLayout from "./components/layouts/privateLayout/PrivateLayout";
import Dashboard from "./components/containers/dashboard/Dashboard";
import Offers from "./components/containers/offers/Offers";

const PrivateRoutes = () => {
    return (
        <PrivateLayout>
            <Routes>
                <Route path="/dashboard" element={<Dashboard/>}/>
                <Route path="/menage-offers" element={<Offers/>}/>
            </Routes>
        </PrivateLayout>
    );
};

export default PrivateRoutes;