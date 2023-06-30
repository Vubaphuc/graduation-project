import React from "react";
import { useSelector } from "react-redux";
import { Link, Navigate } from "react-router-dom";
import hookLogin from "../hookForm/hook/hookAccount/hookLogin";

function LoginPage() {

    const { isAuthenticated, auth } = useSelector((state) => state.auth);

    const { register, handleSubmit, errors, onLogin } = hookLogin();

    // Nếu đã login -> chuyển hướng sang trang homepage
    if (isAuthenticated) {
        const roles = auth.roles.map((role) => role.name);

        if (roles.includes("NHANVIENLETAN")) {
            return <Navigate to={"/receptionist"} />;
        }
        if (roles.includes("NHANVIENSUACHUA")) {
            return <Navigate to={"/engineer"} />;
        }
        if (roles.includes("NHANVIENKHO")) {
            return <Navigate to={"/warehouse"} />;
        }
        if (roles.includes("NHANVIENBAOHANH")) {
            return <Navigate to={"/warranty"} />;
        }
        if (roles.includes("ADMIN")) {
            return <Navigate to={"/admin"} />;
        }
    }

    return (
        <>
            <div
                className="d-flex justify-content-center align-items-center"
                style={{ height: "100vh", overflow: "hidden" }}
            >
                <div className="login shadow p-5" style={{ width: 500 }}>
                    <h1 className="mb-3 text-center">Đăng nhập</h1>
                    <form
                        onSubmit={handleSubmit(onLogin)}
                        className="d-flex flex-column align-items-center"
                    >
                        <input
                            className="form-control mb-3"
                            type="text"
                            placeholder="Nhập email"
                            {...register("email")}
                        />
                        <p className="text-danger fst-italic mt-2">
                            {errors.email?.message}
                        </p>
                        <input
                            className="form-control mb-3"
                            type="password"
                            placeholder="Nhập password"
                            {...register("password")}
                        />
                        <p className="text-danger fst-italic mt-2">
                            {errors.password?.message}
                        </p>
                        <button type="submit" className="btn btn-primary  btn-block mt-1">
                            Đăng nhập
                        </button>
                        <div className="mt-3">
                            <Link to={"/forgot-password"} className="text-decoration-none">
                                Quên Mật Khẩu
                            </Link>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}

export default LoginPage;