import React from "react";
import { Controller } from "react-hook-form";
import Select from "react-select";
import { getAddress } from "../formHTML/options";
import addressQuery from "../formHTML/address";
import hookPersonalInformation from "../hookForm/hook/hookAccount/hookPersonalInformation";
import hookUpdateAvatar from "../hookForm/hook/hookAccount/hookUpdateAvatar";
import { useFindEmployeeByIdQuery } from "../../app/apis/employee/employeeApi";
import { useSelector } from "react-redux";
import hookFetchQuery from "../hookForm/hook/hookAccount/hookFetchQuery";

function PersonalInformation() {


    const { auth, avatarUrl } = hookFetchQuery();

    const { setFiles, handleChangeAvatar } = hookUpdateAvatar();

    const { control, register, handleSubmit, errors, onPersonalInformation } = hookPersonalInformation();

    const { provinces } = addressQuery();

    const { data: userData, isLoading: userLoading } = useFindEmployeeByIdQuery(auth.id);

    if (userLoading) {
        return <h2>Loading....</h2>
    }


    
    // chọn và hiển thị hình ảnh lên
    const handleReaderAvatar = (e) => {
        const file = e.target.files[0];
        setFiles(file);
        const reader = new FileReader();
        reader.onload = () => {
            const avatarImg = document.getElementById("avatar-img");
            avatarImg.src = reader.result;
        };
        reader.readAsDataURL(file);
    };


    const addressOptions = getAddress(provinces);

    const defaultAddress = {
        label: userData.address,
        value: userData.address,
    };

    const handleClose = () => {
        const avatarImg = document.getElementById("avatar-img");
        avatarImg.src = "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png";
    }

    return (
        <>
            <div className="container mt-5 mb-5">
                <h2 className="text-center text-uppercase mb-3">Thông tin user</h2>
                <form onSubmit={handleSubmit(onPersonalInformation)}>
                    <div className="row justify-content-center">
                        <div className="col-md-6">
                            <div className="bg-light p-4">
                                <div className="mb-3">
                                    <label className="col-form-label">Mã Nhân Viên</label>
                                    <input
                                        type="text"
                                        disabled
                                        id="maNhanVien"
                                        className="form-control"
                                        defaultValue={userData.employeeCode}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="col-form-label">Họ Và Tên</label>
                                    <input
                                        type="text"
                                        id="fullname"
                                        className="form-control"
                                        defaultValue={userData.employeeName}
                                        {...register("fullName")}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="col-form-label">Email</label>
                                    <input
                                        type="text"
                                        id="email"
                                        className="form-control"
                                        defaultValue={userData.email}
                                        disabled
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="col-form-label">Phone</label>
                                    <input
                                        type="text"
                                        id="phone"
                                        className="form-control"
                                        defaultValue={userData.phone}
                                        {...register("phone")}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="col-form-label">Address</label>
                                    <Controller
                                        name="address"
                                        control={control}
                                        defaultValue={defaultAddress.value}
                                        render={({ field }) => (
                                            <div>
                                                <Select
                                                    {...field}
                                                    placeholder="--Chọn địa chỉ--"
                                                    options={addressOptions}
                                                    defaultValue={defaultAddress}
                                                    value={addressOptions.find(
                                                        (c) => c.value === field.value
                                                    )}
                                                    onChange={(val) => field.onChange(val.value)}
                                                />
                                            </div>
                                        )}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label">Avatar</label>
                                    <div className="avatar-preview mb-3 rounded">
                                        <img src={avatarUrl} alt="avatar" className="rounded" />
                                    </div>
                                </div>
                            </div>

                            <div className="mb-3">
                                <label className="col-form-label">Thay Đổi Avatar</label>
                                <div className="">
                                    <button
                                        type="button"
                                        className="btn btn-primary"
                                        data-bs-toggle="modal"
                                        data-bs-target="#modal-change-password"
                                    >
                                        Đổi Avatar
                                    </button>
                                </div>
                            </div>

                            <div className="text-center mt-3">
                                <button type="submit" className="btn btn-success" id="btn-save">
                                    Cập nhật
                                </button>
                            </div>
                        </div>
                    </div>
                </form>

                <div
                    className="modal fade"
                    id="modal-change-password"
                    data-bs-backdrop="static"
                    data-bs-keyboard="false"
                    tabIndex="-1"
                    aria-labelledby="staticBackdropLabel"
                    aria-hidden="false"
                >
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="staticBackdropLabel">
                                    Đổi Avatar
                                </h5>
                                <button
                                    type="button"
                                    className="btn-close"
                                    data-bs-dismiss="modal"
                                    aria-label="Close"
                                ></button>
                            </div>

                            <div className="modal-body">
                                <div className="avatar-read mb-3 rounded">
                                    <img
                                        src="https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png"
                                        alt="avatar"
                                        id="avatar-img"
                                        className="rounded"
                                    />
                                </div>
                                <label className="btn btn-warning" htmlFor="input">
                                    Chọn ảnh
                                </label>
                                <input
                                    type="file"
                                    id="input"
                                    className="d-none"
                                    onChange={(e) => handleReaderAvatar(e)}
                                />
                            </div>
                            <div className="modal-footer">
                                <button
                                    type="button"
                                    className="btn btn-secondary"
                                    data-bs-dismiss="modal"
                                    onClick={handleClose}
                                >
                                    Đóng
                                </button>
                                <button
                                    type="button"
                                    className="btn btn-primary"
                                    id="btn-change-password"
                                    data-bs-dismiss="modal"
                                    onClick={handleChangeAvatar}
                                >
                                    Xác nhận
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default PersonalInformation;