import { Controller } from "react-hook-form";
import { Link, useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import { getAddress } from "../../formHTML/options";
import addressQuery from "../../formHTML/address";
import { useDeleteEmployeeByIdMutation, useFindEmployeeByIdQuery } from "../../../app/apis/admin/employeeManageApi";
import hookAdminInformationEmployee from "../../hookForm/hook/hookAdmin/hookAdminInformationEmployee";
import hookAdminUpdatePassword from "../../hookForm/hook/hookAdmin/hookAdminUpdatePassword";

function EmployeeManageDetail () {
    const { employeeId } = useParams();
    const navigate = useNavigate();

    const { provinces } = addressQuery();

    const [deleteEmployee] = useDeleteEmployeeByIdMutation();

    const { control, handleSubmit: handleSubmitUpdateInformation, errors: errorsUpdateInformation, register: registerUpdateInformation, onUpdateInformationEmployee } = hookAdminInformationEmployee();
    const { register: registerPassword, handleSubmit: handleSubmitPassword, errors: errorsPassword, onUpdatePassword } = hookAdminUpdatePassword(employeeId);

    const { data: employeeData, isLoading: employeeLoading } = useFindEmployeeByIdQuery(employeeId);




    if (employeeLoading) {
        return <h2>Loading...</h2>
    }


    const defaultAddress = {
        value: employeeData?.address,
        label: employeeData?.address
    }

    const addressOptions = getAddress(provinces);

    const handleDeleteEmployee = (id) => {
        event.preventDefault();
        deleteEmployee(id)
        .unwrap()
        .then(() => {
            toast.success("Xóa User Thành Công");
            setTimeout(() => {
                navigate("/admin/employees")
            });
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }



    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="row py-2">
                        <div className="col-6">
                            <Link
                                to={"/admin/employees"}
                                className="btn btn-default"
                            >
                                <i className="fas fa-chevron-left"></i> Quay lại
                            </Link>
                            <button
                                type="button"
                                className="btn btn-danger px-4"
                                onClick={() => handleDeleteEmployee(employeeData?.id)}
                            >
                                xóa
                            </button>
                        </div>
                    </div>
                    <div className="container mt-5 mb-5">
                        <h2 className="text-center text-uppercase mb-3">Thông tin Nhân Viên</h2>
                        <form onSubmit={handleSubmitUpdateInformation(onUpdateInformationEmployee)}>
                            <div className="row justify-content-center">
                                <div className="col-md-6">
                                    <div className="bg-light p-4">
                                        <div className="mb-3">
                                            <label className="col-form-label">ID Nhân Viên</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                defaultValue={employeeData?.id}
                                                {...registerUpdateInformation("id")}
                                                readOnly
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errorsUpdateInformation.id?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Mã Nhân Viên</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                defaultValue={employeeData?.employeeCode}
                                                readOnly
                                            />
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Họ Và Tên</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                defaultValue={employeeData?.employeeName}
                                                {...registerUpdateInformation("fullName")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errorsUpdateInformation.fullName?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Email</label>
                                            <input
                                                type="text"
                                                id="email"
                                                className="form-control"
                                                defaultValue={employeeData?.email}
                                                {...registerUpdateInformation("email")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errorsUpdateInformation.email?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Phone</label>
                                            <input
                                                type="text"
                                                id="phone"
                                                className="form-control"
                                                defaultValue={employeeData?.phoneNumber}
                                                {...registerUpdateInformation("phone")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errorsUpdateInformation.phone?.message}
                                            </p>
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
                                                        <p className="text-danger fst-italic mt-2">
                                                            {errorsUpdateInformation.address?.message}
                                                        </p>
                                                    </div>
                                                )}
                                            />
                                        </div>
                                    </div>
                                    <div className="text-center mt-3">
                                        <button type="submit" className="btn btn-success" id="btn-save">
                                            Cập nhật
                                        </button>
                                        <button
                                            type="button"
                                            className="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#modal-change-password"
                                        >
                                            Đổi Mật Khẩu
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
                                    <form onSubmit={handleSubmitPassword(onUpdatePassword)}>
                                        <div className="modal-header">
                                            <h5 className="modal-title" id="staticBackdropLabel">
                                                Đổi mật khẩu
                                            </h5>
                                            <button
                                                type="submit"
                                                className="btn-close"
                                                data-bs-dismiss="modal"
                                                aria-label="Close"
                                            ></button>
                                        </div>

                                        <div className="modal-body">
                                            <div className="mb-3">
                                                <label className="col-form-label">Mật khẩu Mới</label>
                                                <input
                                                    type="text"
                                                    id="old-password"
                                                    className="form-control"
                                                    {...registerPassword("password")}
                                                />
                                                <p className="text-danger fst-italic mt-2">
                                                    {errorsPassword.password?.message}
                                                </p>
                                            </div>
                                        </div>
                                        <div className="modal-footer">
                                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                                                Đóng
                                            </button>
                                            <button type="submit" className="btn btn-primary" id="btn-change-password" data-bs-dismiss="modal">
                                                Xác nhận
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default EmployeeManageDetail;