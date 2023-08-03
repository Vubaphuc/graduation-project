import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";
import { useCreateReceiptMutation, useFindProductGuaranteeByIDQuery } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";



function WarrantyReceiptCreate() {
  const { productId } = useParams();
  const navigate = useNavigate();
  const [currentDate, setCurrentDate] = useState("");
  const invoiceRef = useRef(null);
  const { auth } = useSelector((state) => state.auth)

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

  const { data: productData, isLoading: productLoading } = useFindProductGuaranteeByIDQuery(productId);
  const [createReceipt] = useCreateReceiptMutation();

  if (productLoading) {
    return <h2>Loading...</h2>;
  }

  const handleClickCreateReceipt = () => {
    const data = { productId: productId };
    createReceipt(productId)
      .unwrap()
      .then((res) => {
        toast.success("Tạo Biên Lai Thành Công");
        setTimeout(() => {
            const url = `http://localhost:8080/employee/api/v1/receipt-guarantee-pdf/${res.data.id}`;
            window.location.href = url;
            navigate("/warranty/receipts")
        }, 1500);
      })
      .catch((err) => {
        toast.error(err.data.message);
      });
  };

  return (
    <>
      <div className="invoice-card" ref={invoiceRef}>
        <div className="invoice-title">
          <div id="main-title">
            <h4>RECEIPT</h4>
          </div>
          <h6>{productData?.id}</h6>
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
                <td>{auth.employeeCode}</td>
              </tr>
              <tr className="row-data row-botton-boder">
                <td>Creator Name</td>
                <td>{auth.employeeName}</td>
              </tr>
              <tr className="row-data">
                <td>Customer Name</td>
                <td>{productData?.customer.fullName}</td>
              </tr>
              <tr className="row-data">
                <td>Phone</td>
                <td>{productData?.customer.phoneNumber}</td>
              </tr>
              <tr className="row-data row-botton-boder">
                <td>Email</td>
                <td>{productData?.customer.email}</td>
              </tr>
              <tr className="row-data">
                <td>Model Name</td>
                <td>{productData?.nameModel}</td>
              </tr>
              <tr className="row-data">
                <td>IME</td>
                <td>{productData?.ime}</td>
              </tr>
              <tr className="row-data">
                <td>Quantity</td>
                <td>1</td>
              </tr>             
              <tr className="calc-row">
                <td>Total</td>
                <td>1</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div className="invoice-footer mt-3">
          <Link to={"/receptionist/receipt"} className="btn btn-secondary" id="later">
            BACK
          </Link>
          <button className="btn btn-primary" onClick={handleClickCreateReceipt}>
            Xác Nhận
          </button>
        </div>
      </div>

    </>
  );
}

export default WarrantyReceiptCreate;
