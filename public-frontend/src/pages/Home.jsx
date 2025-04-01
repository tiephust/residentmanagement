import React from 'react';
import Header from '../components/layout/Header';
import Footer from '../components/layout/Footer';
import HomeLayout from "../layouts/HomeLayout";
import {Outlet} from "react-router-dom";

function Home() {
    return (
        <div className="app-container">
            <Header showAuthButtons={true} />
            <HomeLayout />
            <Footer />
        </div>
    );
}

export default Home;
