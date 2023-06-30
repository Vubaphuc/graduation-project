import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import hookFetchQuery from "../page/hookForm/hook/hookAccount/hookFetchQuery";
import { logout } from "../app/slice/authSlice";

function Headers() {
    const { auth } = useSelector((state) => state.auth);
    const dispath = useDispatch();
    const navigate = useNavigate();

    const { avatarUrl } = hookFetchQuery();

    const handleLogout = () => {
        dispath(logout());
        navigate("/login");
      };

    return (
        <>
            <nav className="navbar navbar-expand navbar-dark sticky-top px-4 py-4">
                <div className="navbar-nav align-items-center ms-auto">
                    <div className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img className="rounded-circle me-lg-2 av-auth" src={avatarUrl} alt="" />
                            <span className="d-none d-lg-inline-flex">{auth?.employeeName}</span>

                        </a>
                        <div className="dropdown-menu dropdown-menu-end hd-auth m-0">
                            <Link to={"/employee/personal-information"} className="dropdown-item">Setting</Link>
                            <button className="dropdown-item" onClick={handleLogout}>
                                Logout
                            </button>
                        </div>

                    </div>
                </div>
            </nav>
        </>
    )
}

export default Headers;