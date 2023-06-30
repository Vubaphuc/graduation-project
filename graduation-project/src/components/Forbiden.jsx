import React from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

function Forbiden() {
    const { isAuthenticated, auth } = useSelector((state) => state.auth);
    const navigate = useNavigate();

    const handleOnClick = () => {
        if (isAuthenticated) {
            const roles = auth.roles.map((role) => role.name);

            if (roles.includes("NHANVIENLETAN")) {
                navigate("/receptionist")
            }
            if (roles.includes("NHANVIENSUACHUA")) {
                navigate("/engineer")
            }
            if (roles.includes("NHANVIENKHO")) {
                navigate("/warehouse")
            }
            if (roles.includes("NHANVIENBAOHANH")) {
                navigate("/warranty")
            }
            if (roles.includes("ADMIN")) {
                navigate("/admin")
            }
        }
    }
    return (
        <>
            <div className="pt-4 px-4">
                <div className="row vh-100 rounded align-items-center justify-content-center mx-0">
                    <div className="col-md-6 text-center p-4">
                        <i className="bi bi-exclamation-triangle display-1 text-primary"></i>
                        <h1 className="display-1 fw-bold">401</h1>
                        <h1 className="mb-4">Forbidden</h1>
                        <p className="mb-4">We apologize, but you are not authorized to access this page.
                            Please return to our homepage or try again with other pages?</p>
                        <button
                            className="btn btn-primary rounded-pill py-3 px-5"
                            onClick={handleOnClick}
                        >
                            Go Back To Home
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Forbiden;