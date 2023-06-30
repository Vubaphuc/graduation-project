import React from "react";
import { Controller } from "react-hook-form";
import { Link, useParams } from "react-router-dom";
import Select from "react-select";
import { useGetCustomerByIdQuery } from "../../../../app/apis/receptionist/customerApi";
import { getPhoneCompany } from "../../../formHTML/options";
import hookRecepProductCreate from "../../../hookForm/hook/hookReceptionist/hookRecepProductCreate";

function RecepProductCreate() {
    const { customerId } = useParams();

    const { control, register, handleSubmit, errors, onCreateProduct } =
        hookRecepProductCreate();

    const { data: customerData, isLoading: customerLoading } =
        useGetCustomerByIdQuery(customerId);

    if (customerLoading) {
        return <h2>Loading...</h2>;
    }

    const phoneCompanyOptions = getPhoneCompany();

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onCreateProduct)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link
                                    to={"/receptionist"}
                                    className="btn btn-default"
                                >
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button type="submit" className="btn btn-info px-4">
                                    Lưu
                                </button>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-sp-kh">
                                            <div className="col-md-5">
                                                <h4 className="mb-4">Thông Tin Khách Hàng</h4>
                                                <div className="form-group">
                                                    <label>Mã Khách Hàng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="full-name"
                                                        defaultValue={customerData?.id}
                                                        readOnly
                                                        {...register("customerId")}
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Họ Và Tên</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="full-name"
                                                        defaultValue={customerData?.fullName}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Số Điện Thoại</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="phone"
                                                        defaultValue={customerData?.phoneNumber}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Email</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="email"
                                                        defaultValue={customerData?.email}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Address</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="email"
                                                        defaultValue={customerData?.address}
                                                        readOnly
                                                    />
                                                </div>
                                            </div>
                                            <div className="col-md-5">
                                                <h4 className="mb-4">Thông Tin Sản Phẩm</h4>
                                                <div className="form-group">
                                                    <label>Hãng Điện Thoại</label>
                                                    <Controller
                                                        name="phoneCompany"
                                                        control={control}
                                                        render={({ field }) => (
                                                            <div>
                                                                <Select
                                                                    {...field}
                                                                    placeholder="--Chọn Hãng Sản Xuất--"
                                                                    options={phoneCompanyOptions}
                                                                    value={phoneCompanyOptions.find(
                                                                        (c) => c.value === field.value
                                                                    )}
                                                                    onChange={(val) => field.onChange(val.value)}
                                                                />
                                                                <p className="text-danger fst-italic mt-2">
                                                                    {errors.phoneCompany?.message}
                                                                </p>
                                                            </div>
                                                        )}
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="model"
                                                        {...register("model")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.model?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Số IME</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="so-IME"
                                                        {...register("ime")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.ime?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Mổ Tả Lỗi</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        {...register("defectName")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.defectName?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Giá Tiền</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="gia-tien"
                                                        {...register("price")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.price?.message}
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </>
    );
}

export default RecepProductCreate;