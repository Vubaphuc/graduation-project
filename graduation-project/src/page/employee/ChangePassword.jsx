import React from "react";
import hookChangePassword from "../hookForm/hook/hookAccount/hookChangePassword";

function ChangePassword() {

    const { register, handleSubmit, errors, onChangePassword } = hookChangePassword();

    return (
        <>
            <div
                className="d-flex justify-content-center align-items-center"
                style={{ height: "100vh", overflow: "hidden" }}
            >
                <div className="login shadow p-5" style={{ width: 500 }}>
                    <h1 className="mb-3 text-center">Đổi Mật Khẩu</h1>
                    <form
                        onSubmit={handleSubmit(onChangePassword)}
                        className="d-flex flex-column align-items-center"
                    >
                        <input
                            className="form-control mb-3"
                            type="password"
                            placeholder="Nhập mật khẩu cũ"
                            {...register("oldPassword")}
                        />
                        <p className="text-danger fst-italic mt-2">
                            {errors.oldPassword?.message}
                        </p>
                        <input
                            className="form-control mb-3"
                            type="password"
                            placeholder="Nhập mật khẩu mới"
                            {...register("newPassword")}
                        />
                        <p className="text-danger fst-italic mt-2">
                            {errors.newPassword?.message}
                        </p>
                        <input
                            className="form-control mb-3"
                            type="password"
                            placeholder="Nhập lại mật khẩu mới"
                            {...register("confirmNewPassword")}
                        />
                        <p className="text-danger fst-italic mt-2">
                            {errors.confirmNewPassword?.message}
                        </p>
                        <button type="submit" className="btn btn-primary  btn-block mt-1">
                            Đổi Mật Khẩu
                        </button>
                    </form>
                </div>
            </div>
        </>
    );
}

export default ChangePassword;