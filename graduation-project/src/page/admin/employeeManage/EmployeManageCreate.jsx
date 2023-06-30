import { Controller } from "react-hook-form";
import { Link } from "react-router-dom";
import Select from "react-select";
import addressQuery from "../../formHTML/address";
import hookUpdateAvatar from "../../hookForm/hook/hookAccount/hookUpdateAvatar";
import hookAdminCreateEmployee from "../../hookForm/hook/hookAdmin/hookAdminCreateEmployee";
import { useFindRolesAllQuery } from "../../../app/apis/admin/employeeManageApi";
import { getAddress, getRoles } from "../../formHTML/options";

function EmployeeManageCreate() {

    const { provinces } = addressQuery();

    const { setFiles, handleChangeAvatar } = hookUpdateAvatar();

    const { control, register, handleSubmit, setValue, errors, onCreateEmployee } = hookAdminCreateEmployee();

    const { data: rolesData, isLoading: roleLoading } = useFindRolesAllQuery();

    if (roleLoading) {
        return <h2>Loading...</h2>
    }


    const roleOption = getRoles(rolesData);

    const addressOptions = getAddress(provinces);



    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onCreateEmployee)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link
                                    to={"/admin/employees"}
                                    className="btn btn-default"
                                >
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button
                                    type="submit"
                                    className="btn btn-success px-4"
                                >
                                    Lưu
                                </button>
                            </div>
                        </div>
                        <div className="container mt-5 mb-5">
                            <h2 className="text-center text-uppercase mb-3">Thông tin Nhân Viên</h2>

                            <div className="row justify-content-center">
                                <div className="col-md-6">
                                    <div className="bg-light p-4">
                                        <div className="mb-3">
                                            <label className="col-form-label">Họ Và Tên</label>
                                            <input
                                                type="text"
                                                id="fullname"
                                                className="form-control"
                                                {...register("fullName")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errors.fullName?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Email</label>
                                            <input
                                                type="text"
                                                id="email"
                                                className="form-control"
                                                {...register("email")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errors.email?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Phone</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                {...register("phoneNumber")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errors.phoneNumber?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Mật Khẩu</label>
                                            <input
                                                type="password"
                                                className="form-control"
                                                {...register("password")}
                                            />
                                            <p className="text-danger fst-italic mt-2">
                                                {errors.password?.message}
                                            </p>
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Address</label>
                                            <Controller
                                                name="address"
                                                control={control}
                                                render={({ field }) => (
                                                    <div>
                                                        <Select
                                                            {...field}
                                                            placeholder="--Chọn địa chỉ--"
                                                            options={addressOptions}
                                                            value={addressOptions.find(
                                                                (c) => c.value === field.value
                                                            )}
                                                            onChange={(val) => field.onChange(val.value)}
                                                        />
                                                        <p className="text-danger fst-italic mt-2">
                                                            {errors.address?.message}
                                                        </p>
                                                    </div>
                                                )}
                                            />
                                        </div>
                                        <div className="mb-3">
                                            <label className="col-form-label">Roles</label>
                                            <Controller
                                                name="roleIds"
                                                control={control}
                                                defaultValue={[]}
                                                render={({ field: { onChange, value, ref } }) => (
                                                    <div>
                                                        <Select
                                                            placeholder="--Chọn Roles--"
                                                            inputRef={ref}
                                                            options={roleOption}
                                                            onChange={(val) => {
                                                                onChange(val.map((c) => c.value))
                                                            }}
                                                            isMulti
                                                            value={value.map((val) => roleOption.find((option) => option.value === val))}
                                                        />
                                                        {errors.roleIds && (
                                                            <p className="text-danger fst-italic mt-2">
                                                                {errors.roleIds.message}
                                                            </p>
                                                        )}
                                                    </div>
                                                )}
                                            />
                                        </div>

                                    </div>


                                </div>
                            </div>

                        </div>
                    </form>
                </div>
            </section>
        </>
    )
}

export default EmployeeManageCreate;