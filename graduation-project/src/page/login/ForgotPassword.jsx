import React from "react";
import { Link } from "react-router-dom";
import hookForgotPassword from "../hookForm/hook/hookAccount/hookForgotPassword";

function ForgotPassword () {

    const { register, handleSubmit, errors, onSendMail } = hookForgotPassword();

    return (
        <>
            <div
                className="d-flex justify-content-center align-items-center"
                style={{ height: "100vh", overflow: "hidden" }}
            >
                <div className="login shadow p-5" style={{ width: 500 }}>
                    <h1 className="mb-3 text-center">Forgot password</h1>
                    <form
                        onSubmit={handleSubmit(onSendMail)}
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
                        <button type="submit" className="btn btn-primary btn-block">
                            Send Email
                        </button>
                        <div className="mt-3">
                            <Link to={"/login"} className="text-decoration-none">
                                Đăng Nhập
                            </Link>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}

export default ForgotPassword;