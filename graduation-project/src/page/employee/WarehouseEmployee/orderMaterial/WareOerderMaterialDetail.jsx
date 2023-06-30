import React from "react";
import { Controller } from "react-hook-form";
import { Link, useParams } from "react-router-dom";
import Select from "react-select";
import hookApproverOrderMaterial from "../../../hookForm/hook/hookWarehouse/hookApproverOrderMaterial";
import { useGetOrderMaterialByIdQuery } from "../../../../app/apis/warehouseEmployee/approverOrderMaterialApi";

function WareOerderMaterialDetail() {
    const { orderId } = useParams();

    const { control, register, handleSubmit, errors, onApproverOrder } = hookApproverOrderMaterial(orderId);

    const { data: orderData, isLoading: orderLoading } = useGetOrderMaterialByIdQuery(orderId);

    if (orderLoading) {
        return <h2>Loading...</h2>;
    }


    const StatusOptions = [
        { label: "OK", value: true },
        { label: "PENDING", value: false },
    ];

    const defaultStatus = {
        label: orderData?.status === true ? "OK" : "PENDING",
        value: orderData?.status,
    };

    console.log(orderData)
    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onApproverOrder)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={"/warehouse/orderMaterials"} className="btn btn-default">
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button type="submit" className="btn btn-info px-4">
                                    Phê Duyệt
                                </button>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-sp-kh">
                                            <div className="col-md-5">
                                                <h4 className="mb-2">Thông Tin Order</h4>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Mã Order</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="full-name"
                                                        defaultValue={orderData?.orderCode}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Mã Vật Liệu</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="tenModel"
                                                        defaultValue={orderData?.material.code}
                                                        readOnly
                                                        {...register("materialCode")}
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Tên Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={orderData?.material.nameModel}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Loại Linh Kiện</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={orderData?.material.components.name}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Số Luọng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={orderData?.quantity}
                                                        readOnly
                                                        {...register("quantity")}
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Loại Linh Kiện</label>
                                                    <Controller
                                                        name="status"
                                                        control={control}
                                                        defaultValue={defaultStatus.value}
                                                        render={({ field }) => (
                                                            <div>
                                                                <Select
                                                                    {...field}
                                                                    placeholder="--Chọn Trạng Thái--"
                                                                    options={StatusOptions}
                                                                    value={StatusOptions.find(
                                                                        (c) => c.value === field.value
                                                                    )}
                                                                    onChange={(val) => field.onChange(val.value)}
                                                                />
                                                                <p className="text-danger fst-italic mt-2">
                                                                    {errors.status?.message}
                                                                </p>
                                                            </div>
                                                        )}
                                                    />
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

export default WareOerderMaterialDetail;