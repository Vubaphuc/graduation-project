import React from "react";
import { Link, useParams } from "react-router-dom";
import { useFindProductDeliveredByIDQuery } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";
import hookRegisterProductGuarantee from "../../../hookForm/hook/hookWarranty/hookRegisterProductGuarantee";

function WarrantyRegisterProductGuarantee() {
    const { productId } = useParams();

    const { control, register, handleSubmit, errors, onRegisterProductGuarantee } = hookRegisterProductGuarantee();

    const { data: productData, isLoading: productLoading } = useFindProductDeliveredByIDQuery(productId);

    if (productLoading) {
        return <h2>Loading...</h2>
    }

    console.log(productData)

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onRegisterProductGuarantee)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link
                                    to={"/warranty/product-delivered"}
                                    className="btn btn-default"
                                >
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button type="submit" className="btn btn-info px-4">
                                    Đăng Ký Sản Phẩm
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
                                                        defaultValue={productData?.id}
                                                        {...register("id")}
                                                        readOnly
                                                    />
                                                     <p className="text-danger fst-italic mt-2">
                                                        {errors.id?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Hãng Điện Thoại</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={productData?.phoneCompany}
                                                        readOnly
                                                    />
                                                     <p className="text-danger fst-italic mt-2">
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={productData?.nameModel}
                                                        readOnly
                                                    />
                                                     <p className="text-danger fst-italic mt-2">

                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Số IME</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={productData?.ime}
                                                        readOnly
                                                    />
                                                     <p className="text-danger fst-italic mt-2">
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Mổ Tả Lỗi</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        {...register("defectName")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.defectName?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Nguyên Nhân</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"            
                                                        {...register("cause")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.cause?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Giá Tiền</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"           
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

export default WarrantyRegisterProductGuarantee;