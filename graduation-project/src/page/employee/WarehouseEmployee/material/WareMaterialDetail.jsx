import React from "react";
import { Link, useParams } from "react-router-dom";
import { useGetMaterialByIdQuery } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";

function WareMaterialDetail() {
    const { materialId } = useParams();

    const { data: materialData, isLoading: materialLoading } = useGetMaterialByIdQuery(materialId);

    if (materialLoading) {
        return <h2>Loading...</h2>
    }

    console.log(materialData)

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="row py-2">
                        <div className="col-6">
                            <Link to={"/warehouse"} className="btn btn-default">
                                <i className="fas fa-chevron-left"></i> Quay lại
                            </Link>
                            <Link to={`/warehouse/update-material/${materialId}`} className="btn btn-info px-4">
                                Cập Nhật
                            </Link>
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
                                                    defaultValue={materialData?.remainingQuantity}
                                                    disabled
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

export default WareMaterialDetail;