import React, { useEffect } from "react";
import hookUpdateInformationProductRepair from "../../../hookForm/hook/hookEngineer/hookUpdateInformationProductRepair";
import { Link, useParams } from "react-router-dom";
import { useGetProductNewByIdQuery, useLazyGetListComponentPhoneQuery } from "../../../../app/apis/engineer/engineerProductApi";
import { getComponents, getStatus } from "../../../formHTML/options";
import { Controller } from "react-hook-form";
import Select from "react-select";

function EngiInformationProdcutRepair() {
    const { productId } = useParams();
    const {
        control,
        register,
        handleSubmit,
        errors,
        onUpdateInformationProductRepair,
    } = hookUpdateInformationProductRepair(productId);

    const { data: productData, isLoading: productLoading } =
        useGetProductNewByIdQuery(productId);

    const [getComponent, { data: componentData, isLoading: componentLoading }] =
        useLazyGetListComponentPhoneQuery();

    useEffect(() => {
        getComponent({
            page: 1,
            pageSize: 10
        })
    }, [])

    if (productLoading || componentLoading) {
        return <h2>Loading....</h2>;
    }

    const linhKienOptions = getComponents(componentData?.data);

    const defaultStatus = getStatus();


    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <form onSubmit={handleSubmit(onUpdateInformationProductRepair)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={"/engineer"} className="btn btn-default">
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
                                                <h4 className="mb-4">Thông Tin Sản Phẩm</h4>
                                                <div className="form-group">
                                                    <label>Hãng Điện Thoại</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="hang-san-pham"
                                                        defaultValue={productData?.phoneCompany}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Model</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="model"
                                                        defaultValue={productData?.nameModel}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Số IME</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="so-IME"
                                                        defaultValue={productData?.ime}
                                                        disabled
                                                    />
                                                </div>
                                                <div className="form-group">
                                                    <label>Mổ Tả Lỗi</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="ten-loi"
                                                        defaultValue={productData?.defectName}
                                                        disabled
                                                    />
                                                </div>
                                            </div>
                                            <div className="col-md-5">
                                                <h4 className="mb-4">Thông Tin Sửa Chữa</h4>
                                                <div className="form-group">
                                                    <label>Nguyên Nhân</label>
                                                    <input
                                                        type="text"
                                                        className="form-control mt-3"
                                                        id="nguyen-nhan-loi"
                                                        {...register("location")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.location?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Chú Thích</label>
                                                    <input
                                                        type="text"
                                                        className="form-control mt-3"
                                                        id="nguyen-nhan-loi"
                                                        {...register("note")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.note?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-3">Vị Trí Sửa</label>
                                                    <Controller
                                                        name="componentsId"
                                                        control={control}
                                                        defaultValue={linhKienOptions.value}
                                                        render={({ field }) => (
                                                            <div>
                                                                <Select
                                                                    {...field}
                                                                    placeholder="--Chọn Vị Trí Sửa--"
                                                                    options={linhKienOptions}
                                                                    value={defaultStatus.find(
                                                                        (c) => c.value === field.value
                                                                    )}
                                                                    onChange={(val) => field.onChange(val.value)}
                                                                />
                                                            </div>
                                                        )}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.componentsId?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label className="mb-3 mt-3">Trạng Thái</label>
                                                    <Controller
                                                        name="status"
                                                        control={control}
                                                        defaultValue={defaultStatus[0].value}
                                                        render={({ field }) => (
                                                            <div>
                                                                <Select
                                                                    {...field}
                                                                    placeholder="--Chọn Trạng Thái--"
                                                                    options={defaultStatus}
                                                                    value={defaultStatus.find(
                                                                        (c) => c.value === field.value
                                                                    )}
                                                                    onChange={(val) => field.onChange(val.value)}
                                                                />
                                                            </div>
                                                        )}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.status?.message}
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

export default EngiInformationProdcutRepair;