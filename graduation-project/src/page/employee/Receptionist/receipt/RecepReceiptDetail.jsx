import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useCreateReceiptMutation, useFindReceiptByIdQuery } from "../../../../app/apis/receptionist/productApi";
import { useSelector } from "react-redux";
import DatePicker from "react-datepicker";
import { Controller } from "react-hook-form";
import hookRecepUpdateReceipt from "../../../hookForm/hook/hookReceptionist/hookRecepUpdateReceipt";



function RecepReceiptDetail() {
    const { receiptId } = useParams();
    const navigate = useNavigate();
    const [currentDate, setCurrentDate] = useState("");
    const invoiceRef = useRef(null);

    const { control, handleSubmit, errors, onUpdateReceipt } = hookRecepUpdateReceipt(receiptId);

    useEffect(() => {
        const getCurrentDate = () => {
            const date = new Date();
            const day = String(date.getDate()).padStart(2, "0");
            const month = String(date.getMonth() + 1).padStart(2, "0");
            const year = date.getFullYear();
            return `${day}/${month}/${year}`;
        };
        setCurrentDate(getCurrentDate());
    }, []);

    const { data: receiptData, isLoading: receiptLoading } = useFindReceiptByIdQuery(receiptId);

    if (receiptLoading) {
        return <h2>Loading...</h2>;
    }


    const receiptDate = new Date(receiptData?.createDate);
    const formattedReceiptDate = `${receiptDate.getDate()}/${receiptDate.getMonth() + 1}/${receiptDate.getFullYear()}`;



    return (
        <>
            <form onSubmit={handleSubmit(onUpdateReceipt)}>
                <div className="invoice-card" ref={invoiceRef}>
                    <div className="invoice-title">
                        <div id="main-title">
                            <h4>RECEIPT</h4>
                        </div>
                        <h6>{receiptData?.id}</h6>
                        <span id="date">{currentDate}</span>
                    </div>

                    <div className="invoice-details">
                        <table className="invoice-table">
                            <thead>
                                <tr>
                                    <td>INMATION</td>
                                    <td>DETAIL</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="row-data row-boder">
                                    <td>Creator ID</td>
                                    <td>{receiptData?.employeeCreate.employeeCode}</td>
                                </tr>
                                <tr className="row-data row-botton-boder">
                                    <td>Creator Name</td>
                                    <td>{receiptData?.employeeCreate.employeeName}</td>
                                </tr>
                                <tr className="row-data">
                                    <td>Customer Name</td>
                                    <td>{receiptData?.product.customer.fullName}</td>
                                </tr>
                                <tr className="row-data">
                                    <td>Phone</td>
                                    <td>{receiptData?.product.customer.phoneNumber}</td>
                                </tr>
                                <tr className="row-data row-botton-boder">
                                    <td>Email</td>
                                    <td>{receiptData?.product.customer.email}</td>
                                </tr>
                                <tr className="row-data">
                                    <td>Model Name</td>
                                    <td>{receiptData?.product.nameModel}</td>
                                </tr>
                                <tr className="row-data">
                                    <td>IME</td>
                                    <td>{receiptData?.product.ime}</td>
                                </tr>
                                <tr>
                                    <td>Ngày Nhận</td>
                                    <td>{new Date(receiptData?.createDate).toLocaleDateString()}</td>
                                </tr>
                                <tr>
                                    <td>Ngày hẹn Trả</td>
                                    <td>
                                        {receiptData?.status ? (
                                        <Controller
                                            control={control}
                                            name="payDate"
                                            defaultValue={new Date(receiptData?.payDate)}
                                            render={({ field: { onChange, value } }) => (
                                                <>
                                                    <DatePicker
                                                        selected={value ? new Date(value) : null}
                                                        onChange={onChange}
                                                        dateFormat="dd/MM/yyyy"
                                                        showTimeSelect
                                                        timeFormat="HH:mm"
                                                    />
                                                    {errors.payDate && (
                                                        <p className="text-danger fst-italic mt-2">{errors.payDate.message}</p>
                                                    )}
                                                </>
                                            )}
                                        />
                                        ) : (
                                            <p>{new Date(receiptData?.payDate).toLocaleDateString()}</p>
                                        )}
                                    </td>
                                </tr>
                                <tr className="row-data">
                                    <td>Quantity</td>
                                    <td>{receiptData?.quantity}</td>
                                </tr>
                                <tr className="calc-row">
                                    <td>Total</td>
                                    <td>{receiptData?.quantity}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div className="invoice-footer mt-3">
                        <Link to={"/receptionist/receipt"} className="btn btn-secondary" id="later">
                            BACK
                        </Link>
                        <button type="submit" className="btn btn-primary">
                            Cập Nhật
                        </button>
                    </div>
                </div>
            </form>
        </>
    );
}

export default RecepReceiptDetail;
