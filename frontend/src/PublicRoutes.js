import React from 'react';
import {Route, Routes} from "react-router-dom";
import MainPage from "./components/containers/mainPage/MainPage";
import MainLayout from "./components/layouts/mainLayout/MainLayout";
import Employer from "./components/containers/employer/Employer";
import LoginPage from "./components/containers/loginPage/LoginPage";

const PublicRoutes = () => {
    return (
        <MainLayout>
            <Routes>
                <Route path="/" element={<MainPage/>}/>
                <Route path="/employer" element={<Employer/>}/>
                <Route path="/login" element={<LoginPage/>}/>
            </Routes>
        </MainLayout>
    );
};

export default PublicRoutes;