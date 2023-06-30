import React from "react";
import hookVendorCreate from "../../../hookForm/hook/hookWarehouse/hookVendorCreate";
import { Link } from "react-router-dom";

function WareVendorCreate() {

    const { control, register, handleSubmit, errors, onVendorCreate } = hookVendorCreate();

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onVendorCreate)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={"/employee/warehouse/vendors"} className="btn btn-default">
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
                                                <h4 className="mb-2">Thông Tin Vender</h4>
                                                <div className="form-group">
                                                    <label className="mb-2 mt-2">Tên Vender Mới</label>
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

export default WareVendorCreate;