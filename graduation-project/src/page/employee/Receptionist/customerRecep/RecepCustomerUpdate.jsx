import React from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import addressQuery from "../../../formHTML/address";
import { getAddress } from "../../../formHTML/options";
import Select from "react-select";
import { Controller } from "react-hook-form";
import hookRecepCustomerUpdate from "../../../hookForm/hook/hookReceptionist/hookRecepCustomerUpdate";
import { useDeleteCustomerByIdMutation, useGetCustomerByIdQuery } from "../../../../app/apis/receptionist/customerApi";
import { toast } from "react-toastify";

function RecepCustomerUpdate() {
    const { customerId } = useParams();
    const navigate = useNavigate();
    

    const { control, register, handleSubmit, errors, onUpdateCustomer } = hookRecepCustomerUpdate();

    const [deleteCustomer] = useDeleteCustomerByIdMutation();

    const { data: customerData, isLoading: customerLoading } = useGetCustomerByIdQuery(customerId);
    const { provinces } = addressQuery();

    if (customerLoading) {
        return <h2>Loading...</h2>
    }
    
    
    const addressOption = getAddress(provinces);
    const addressDefault = {
        value: customerData?.address,
        label: customerData?.address
    }

    const handleDeleteCustomerById = (id) => {
        event.preventDefault();
        deleteCustomer(id)
        .unwrap()
        .then(() => {
            toast.success("Xóa Thành công");
            setTimeout(() => {
                navigate("/receptionist/customers");
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
                    <form onSubmit={handleSubmit(onUpdateCustomer)}>
                        <div className="row py-2">
                            <div className="col-6">
                                <Link to={"/receptionist/customers"} className="btn btn-default">
                                    <i className="fas fa-chevron-left"></i> Quay lại
                                </Link>
                                <button type="submit" className="btn btn-info px-4">
                                    Lưu
                                </button>
                                <button 
                                    className="btn btn-danger px-4"
                                    onClick={() => handleDeleteCustomerById(customerData?.id)}
                                >
                                    Xóa
                                </button>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-sp-kh">
                                            <div className="col-md-5">
                                                <h4 className="mb-4">Thông Tin Khách Hàng</h4>
                                                <div className="form-group">
                                                    <label>ID Khách Hàng</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={customerData?.id}
                                                        {...register("id")}
                                                        readOnly
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.id?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Họ Và Tên</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={customerData?.fullName}
                                                        {...register("customerName")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.customerName?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Số Điện Thoại</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={customerData?.phoneNumber}
                                                        {...register("phoneNumber")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.phoneNumber?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Email</label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        defaultValue={customerData?.email}
                                                        {...register("customerEmail")}
                                                    />
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.customerEmail?.message}
                                                    </p>
                                                </div>
                                                <div className="form-group">
                                                    <label>Địa chỉ</label>
                                                    <Controller
                                                        name="customerAddress"
                                                        control={control}
                                                        defaultValue={addressDefault.value}
                                                        render={({ field }) => (
                                                            <div>
                                                                <Select
                                                                    {...field}
                                                                    placeholder="--chọn địa chỉ--"
                                                                    defaultValue={addressDefault}
                                                                    options={addressOption}
                                                                    value={addressOption.find(
                                                                        (c) => c.value === field.value
                                                                    )}
                                                                    onChange={(val) => field.onChange(val.value)}
                                                                />
                                                            </div>
                                                        )}
                                                    />
                                                    {errors.customerAddress && (
                                                        <p className="text-danger fst-italic mt-2">
                                                            {errors.customerAddress.message}
                                                        </p>
                                                    )}
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

export default RecepCustomerUpdate;