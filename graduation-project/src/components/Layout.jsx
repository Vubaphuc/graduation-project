import React from "react";
import Sidebar from "./Sidebar";
import Headers from "./Headers";
import { Outlet } from "react-router-dom";
import { useSelector } from "react-redux";
import Footer from "./Footer";

function Layout () {
    const { auth } = useSelector((state) => state.auth);

    const roles = auth.roles.map((role) => role.name);
    const isAdmin = roles.includes("ADMIN");


    return (
        <>
            <div>
                <Sidebar/>
                <div className="wrapper-container">
                    <Headers />
                    <Outlet />
                </div>
            </div>
        </>
    )
}

export default Layout;