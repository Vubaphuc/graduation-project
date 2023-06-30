import React from "react";
import { Link, useParams } from "react-router-dom";
import { useGetComponentsByIdQuery } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";
import hookUpdateComponent from "../../../hookForm/hook/hookWarehouse/hookUpdateComponent";

function WareComponentDetail() {
    const { componentId } = useParams();

    const { register, handleSubmit, errors, onUpdateComponent } = hookUpdateComponent();

    const { data: componentData, isLoading: componentLoading } = useGetComponentsByIdQuery(componentId);

    if (componentLoading) {
        return <h2>Loading...</h2>
    }


    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onUpdateComponent)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={"/warehouse/components"} className="btn btn-default">
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button type="submit" className="btn btn-info px-4">
                                    Cập Nhật
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
                                                    <label>Mã Linh Kiện</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="hang-san-pham"
                                                        defaultValue={componentData?.id}
                                                        {...register("id")}
                                                        readOnly
                                                    />
                                                     <p className="text-danger fst-italic mt-2">
                                                        {errors.id?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Loại Linh Kiện</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="model"
                                                        defaultValue={componentData?.name}
                                                        {...register("name")}
                                                    />
                                                     <p className="text-danger fst-italic mt-2">
                                                        {errors.name?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Thời hạn bảo hành</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="so-IME"
                                                        defaultValue={componentData?.warrantyPeriod}
                                                        {...register("warrantyPeriod")}
                                                    />
                                                     <p className="text-danger fst-italic mt-2">
                                                        {errors.warrantyPeriod?.message}
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

export default WareComponentDetail;