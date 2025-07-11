import React from 'react';
import Input from "../../elements/inputComponents/input/Input";
import {useFormik} from "formik";
import './LoginPage.scss';
import Button from "../../elements/commonElements/button/Button";
import {useNavigate} from "react-router-dom";

const LoginPage = () => {

    const navigate = useNavigate();

    const formik = useFormik({
        initialValues: {
            email: '',
            password: ''
        },
        onSubmit: (values) => {
            console.log("Login values: ", values);
        }
    });

    return (
        <div className="login-page">
            <div className="card">
                <h1 className="title">Zaloguj się</h1>
                <div className="input-wrapper">
                    <Input
                        label="Email"
                        formik={formik}
                        name="email"
                        type="email"
                        placeholder="Adres email"
                    />
                    <Input
                        label="Hasło"
                        formik={formik}
                        name="password"
                        type="password"
                        placeholder="Hasło"
                    />
                </div>
                <Button type="submit" className="login" onClick={() => navigate('/logged/dashboard')}>
                    Zaloguj
                </Button>
            </div>
        </div>
    );
};

export default LoginPage;