import React from "react";
import { Link, useParams } from "react-router-dom";
import { useGetMaterialByIdQuery } from "../../../../app/apis/engineer/engineerOrderMaterialApi";
import hookOrderMaterial from "../../../hookForm/hook/hookEngineer/hookOrderMaterial";

function EngiOrderMaterialCreate() {
    const { materialId } = useParams();

    const { register, handleSubmit, errors, onOrderMaterial } =
        hookOrderMaterial();

    const { data: materialData, isLoading: materialLoading } =
        useGetMaterialByIdQuery(materialId);

    if (materialLoading) {
        return <h2>Loading...</h2>;
    }
    console.log(materialData);

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onOrderMaterial)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link
                                    to={"/engineer/materials"}
                                    className="btn btn-default"
                                >
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button type="submit" className="btn btn-info px-4">
                                    Order
                                </button>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-sp-kh">
                                            <div className="col-md-5">
                                                <h4 className="mb-2">Thông Tin Vật Liệu</h4>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Mã Vật Liệu</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={materialData?.code}
                                                        {...register("materialCode")}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Tên Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={materialData?.nameModel}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Loại Linh Kiện</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={materialData?.components.name}
                                                        {...register("componentsName")}
                                                        readOnly
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">số lượng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
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
                    </form>
                </div>
            </section>
        </>
    );
}

export default EngiOrderMaterialCreate;