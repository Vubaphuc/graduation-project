import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useCreateBillMutation, useFindProductByIDQuery, useLazyExportBillToPdfQuery } from "../../../../app/apis/receptionist/productApi";
import { toast } from "react-toastify";
import { PDFDownloadLink, Document, Page, Text, View, StyleSheet } from "@react-pdf/renderer";
import html2pdf from "html2pdf.js";
import { useSelector } from "react-redux";



function RecepBillCreate() {
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

  const { data: productData, isLoading: productLoading } = useFindProductByIDQuery(productId);
  const [exportPDF] = useLazyExportBillToPdfQuery();
  const [createBill] = useCreateBillMutation();

  if (productLoading) {
    return <h2>Loading...</h2>;
  }

  const handleClickCreateBill = () => {
    const data = { productId: productId };
    createBill(data)
      .unwrap()
      .then((res) => {
        toast.success("Tạo Hóa Đơn Thành Công");
        setTimeout(() => {
          navigate(`/receptionist/pdf/${res.data.id}`);        
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
            <h4>INVOICE</h4>
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
              <tr className="row-data row-botton-boder">
                <td>Quantity</td>
                <td>1</td>
              </tr>
              <tr className="calc-row">
                <td>Total</td>
                <td>{productData?.price.toLocaleString("vi-VN") + " VND"}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div className="invoice-footer mt-3">
          <Link to={"/receptionist"} className="btn btn-secondary" id="later">
            BACK
          </Link>
          <button className="btn btn-primary" onClick={handleClickCreateBill}>
            PAY NOW
          </button>        
        </div>       
      </div>

    </>
  );
}

export default RecepBillCreate;
