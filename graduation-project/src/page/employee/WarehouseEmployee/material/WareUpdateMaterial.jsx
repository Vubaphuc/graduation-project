import React from "react";
import { Link, useParams } from "react-router-dom";
import hookUpdateMaterial from "../../../hookForm/hook/hookWarehouse/hookUpdateMaterial";
import { useGetMaterialByIdQuery } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";

function WareUpdateMaterial () {
    const { materialId } = useParams();

    const { register, handleSubmit, errors, onUpdateMaterial } = hookUpdateMaterial(materialId);

    const { data: materialData, isLoading: materialLoading } = useGetMaterialByIdQuery(materialId);

    if (materialLoading) {
        return <h2>Loading...</h2>
    }

    return (
        <>
            <section className="content">
                <form onSubmit={handleSubmit(onUpdateMaterial)}>
                    <div className="container-fluid">
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={`/warehouse/material/${materialId}`} className="btn btn-default">
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
                                                <h4 className="mb-4">Thông Tin Vật Liệu</h4>
                                                <div className="form-group">
                                                    <label>Mã Việt Liệu</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="hang-san-pham"
                                                        defaultValue={materialData?.code}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Tên Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="model"
                                                        defaultValue={materialData?.nameModel}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Loại Linh Kiện</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="so-IME"
                                                        defaultValue={materialData?.components.name}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Tên Vendor</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={materialData?.vendor.name}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Số Lượng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        {...register("quantity")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.quantity?.message}
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </>
    );
}

export default WareUpdateMaterial;