import React from "react";
import { Link } from "react-router-dom";
import hookComponentCreate from "../../../hookForm/hook/hookWarehouse/hookComponentCreate";

function WareComponentCreate() {

    const { register, handleSubmit, errors, onComponentCreate } = hookComponentCreate();
    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onComponentCreate)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={"/warehouse/components"} className="btn btn-default">
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
                                                <h4 className="mb-2">Thông Tin Linh Kiện</h4>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Tên Linh Kiện</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="full-name"
                                                        {...register("name")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.name?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">
                                                        Thời Gian Bảo Hành
                                                    </label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="full-name"
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

export default WareComponentCreate;