import React from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useCreateNewGuaranteeMutation, useFindProductGuaranteeByIDQuery } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";
import { toast } from "react-toastify";
import { getStatusLabel } from "../../../formHTML/enum";

function WarrantyGuaranteeCreate () {
    const { productId } = useParams();
    const navigate = useNavigate();


    const { data: productData, isLoading: productLoading } = useFindProductGuaranteeByIDQuery(productId);

    const [createGuarantee] = useCreateNewGuaranteeMutation();

    if (productLoading) {
        return <h2>Loading...</h2>
    }

    const handleClickCreateGuarantee = () => {
        const data = {productId: productId}
        createGuarantee(data)
            .unwrap()
            .then(() => {
                toast.success("Đăng lý Thành Công");
                setTimeout(() => {
                    navigate("/warranty")
                }, 1500)
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
                            <Link to={"/warranty/product-guarantee-repaired"} className="btn btn-default">
                                <i className="fas fa-chevron-left"></i> Quay lại
                            </Link>
                            <button
                                type="button"
                                className="btn btn-info px-4"
                                onClick={handleClickCreateGuarantee}
                            >
                                Đăng Ký Bảo Hành
                            </button>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="card-body">
                                    <div className="table-sp-kh">
                                        <div className="col-md-5">
                                            <h4 className="mb-4">Thông tin sản phẩm</h4>
                                            <div className="form-group">
                                                <label>ID Sản Phẩm</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="maSanPham"
                                                    defaultValue={productData?.id}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Họ Và Tên Khách Hàng</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="fullNameKH"
                                                    defaultValue={productData?.customer.fullName}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Số điện thoại</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="phoneKH"
                                                    defaultValue={productData?.customer.phoneNumber}
                                                    readOnly
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label>Hãng Sản Phẩm</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="hangSanPham"
                                                    defaultValue={productData?.phoneCompany}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Tên Model</label>
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
                                                    id="soIME"
                                                    defaultValue={productData?.ime}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Tên Lỗi</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="tenLoi"
                                                    defaultValue={productData?.defectName}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Vị Trí Sửa</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="viTriSua"
                                                    defaultValue={productData?.location}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Loại Linh Kiện</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="loaiBaoHanh"
                                                    defaultValue={productData?.components.name}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Thời gian bảo hành</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="thoiGianBaoHanh"
                                                    defaultValue={productData?.components.warrantyPeriod + " Tháng"}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Thành Tiền</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="thanh-tien"
                                                    defaultValue={productData ? productData.price.toLocaleString("vi-VN") + " VND" : ""}
                                                    readOnly
                                                />
                                            </div>
                                            <div className="form-group">
                                                <label>Trạng Thái</label>
                                                <input
                                                    type="text"
                                                    className="form-control"
                                                    id="thanh-tien"
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
                </div>
            </section>
        </>
    );
}

export default WarrantyGuaranteeCreate;