import React from "react";
import { useDeleteOrderByIdMutation, useGetOrderMaterialByIdQuery } from "../../../../app/apis/engineer/engineerOrderMaterialApi";
import { Link, useNavigate, useParams } from "react-router-dom";
import hookUpdateQuantityOrderMaterial from "../../../hookForm/hook/hookEngineer/hookUpdateQuantityOrderMaterial";
import { toast } from "react-toastify";

function EngiOrderMaterialDetail() {
    const { orderId } = useParams();
    const navigate = useNavigate();

    const [deleteOrder] = useDeleteOrderByIdMutation();

    const { register, handleSubmit, errors, onUpdateQuantity } =
        hookUpdateQuantityOrderMaterial(orderId);

    const { data: orderData, isLoading: orderLoading } =
        useGetOrderMaterialByIdQuery(orderId);

    if (orderLoading) {
        return <h2>Loading...</h2>;
    }

    console.log(orderData);

    const isOrderStatusTrue = orderData?.status === true;

    const handleDeleteOrder = (id) => {
        event.preventDefault();
        deleteOrder(id)
            .unwrap()
            .then(() => {
                toast.success("Hủy Thành Công");
                setTimeout(() => {
                    navigate("/engineer/orders");
                },1500)              
            })
            .catch((err) => {
                toast.error(err.data.message);
            });
    };

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="row py-2">
                        <div className="col-6">
                            <Link
                                to={"/engineer/orders"}
                                className="btn btn-default"
                            >
                                <i className="fas fa-chevron-left"></i> Quay lại
                            </Link>
                            {!isOrderStatusTrue && (
                                <button
                                    type="button"
                                    className="btn btn-info px-4"
                                    onClick={() => handleDeleteOrder(orderData?.id)}
                                >
                                    Hủy
                                </button>
                            )}
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-12">
                            <form onSubmit={handleSubmit(onUpdateQuantity)}>
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-sp-kh">
                                            <div className="col-md-5">
                                                <h4 className="mb-2">Thông Tin Vật Liệu</h4>
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
                                                        id="full-name"
                                                        defaultValue={orderData?.material.code}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Tên Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="tenModel"
                                                        defaultValue={orderData?.material.nameModel}
                                                        readOnly
                                                    />
                                                </div>                                             
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">số lượng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={orderData?.quantity}
                                                        {...register("quantity")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.quantity?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">
                                                        Mã Nhân Viên Order
                                                    </label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={orderData?.orderer.employeeCode}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">
                                                        Tên Nhân Viên Order
                                                    </label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={orderData?.orderer.employeeName}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Trạng Thái</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="soLuong"
                                                        defaultValue={
                                                            orderData?.status === true ? "OK" : "PENDING"
                                                        }
                                                        readOnly
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    {!isOrderStatusTrue && (
                                        <div className="text-center mt-3">
                                            <button
                                                type="submit"
                                                className="btn btn-success"
                                                id="btn-save"
                                            >
                                                Cập nhật
                                            </button>
                                        </div>
                                    )}
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default EngiOrderMaterialDetail;