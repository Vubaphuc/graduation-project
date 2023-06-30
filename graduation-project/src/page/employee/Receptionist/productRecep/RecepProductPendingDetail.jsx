import React from "react";
import { Controller } from "react-hook-form";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useDeletePorductBtIDMutation, useFindProductByIDQuery } from "../../../../app/apis/receptionist/productApi";
import { getStatusLabel } from "../../../formHTML/enum";
import { toast } from "react-toastify";

function RecepProductPendingDetail() {
    const { productId } = useParams();
    const navigate = useNavigate();


    const { data: productData, isLoading: productLoading } =
        useFindProductByIDQuery(productId);
    
    const [deleteProduct] = useDeletePorductBtIDMutation();

    if (productLoading) {
        return <h2>Loading...</h2>;
    }

    const handleDeleteProduct = (id) => {
        event.preventDefault();
        deleteProduct(id)
        .unwrap()
        .then(() => {
            toast.success("Xóa Thành công");
            setTimeout(() => {
                navigate("/receptionist/product-pending");
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link
                                    to={"/receptionist/under-repair"}
                                    className="btn btn-default"
                                >
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button 
                                    className="btn btn-danger px-4"
                                    onClick={() => handleDeleteProduct(productData?.id)}
                                >
                                    Hủy
                                </button>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-sp-kh">
                                            <div className="col-md-5">
                                                <h4 className="mb-4">Thông Tin Sản Phẩm</h4>
                                                <div className="form-group">
                                                    <label>ID Sản Phẩm</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="hang-san-pham"
                                                        defaultValue={productData?.id}

                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Hãng Điện Thoại</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="hang-san-pham"
                                                        defaultValue={productData?.phoneCompany}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="model"
                                                        defaultValue={productData?.nameModel}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Số IME</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="so-IME"
                                                        defaultValue={productData?.ime}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Mổ Tả Lỗi</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.defectName}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Giá Tiền</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.price}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Ngày Input</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={new Date(productData?.inputDate).toLocaleDateString()}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Họ Và Tên Khách Hàng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.customer.fullName}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Email Khách Hàng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.customer.email}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Số Điện Thoại</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.customer.phoneNumber}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Địa Chỉ</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.customer.address}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Trạng Thái</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={getStatusLabel(productData?.status)}
                                                        readOnly
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

export default RecepProductPendingDetail;