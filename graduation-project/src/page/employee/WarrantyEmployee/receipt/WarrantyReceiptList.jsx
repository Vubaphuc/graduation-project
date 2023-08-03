import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import { useLazyFindReceiptAllQuery, useLazyFindReceiptStatusTrueAllQuery } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";

function WarrantyReceiptList () {
    const [term, setTerm] = useState("");
    const [status, setStatus] = useState("NO");

    const [getReceipt, { data: receiptData, isLoading: productLoading }] =
        useLazyFindReceiptAllQuery();
    const [getReceiptNo, { data: receiptNoData }] = useLazyFindReceiptStatusTrueAllQuery();

    useEffect(() => {
        getReceipt({
            page: 1,
            pageSize: 10,
            term: term
        })
    }, [term])

    useEffect(() => {
        getReceiptNo({
            page: 1,
            pageSize: 10,
            term: term
        })
    }, [term])

    if (productLoading) {
        return <h2>Loading....</h2>;
    }

    const handlePageClick = (page) => {
        getReceipt({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    };

    const handleNoPageClick = (page) => {
        getReceiptNo({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    };


    console.log(receiptData)

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="tra-cuu ms-3 mt-3">
                        <label htmlFor="statusSelect" className="mb-2">
                            Chọn Loại Hình Sản Phẩm:
                        </label>
                        <select
                            id="statusSelect"
                            className="form-control"
                            value={status}
                            onChange={(e) => setStatus(e.target.value)}
                        >
                            <option value="NO">Chưa Trả Khách</option>
                            <option value="ALL">Tất Cả</option>
                        </select>
                    </div>
                    <div className="search-container">
                        <input
                            className="input-search mb-4"
                            type="text"
                            placeholder="Tìm kiếm kiếm sản phẩm..."
                            value={term}
                            onChange={(e) => setTerm(e.target.value)}
                        />
                    </div>
                    {status === "ALL" && (
                        <div className="search-results mt-3">
                            {receiptData && receiptData.data.length > 0 ? (
                                <div className="row">
                                    <div className="col-12">
                                        <div className="card">
                                            <div className="card-body">
                                                <table className="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Tên Khách hàng</th>
                                                            <th>Email</th>
                                                            <th>Số Điện Thoại</th>
                                                            <th>Hãng Sản Phẩm</th>
                                                            <th>Số IME</th>
                                                            <th>Số Lượng</th>
                                                            <th>Mã Nhân Viên Nhận</th>
                                                            <th>Tên Nhân Viên Nhận</th>
                                                            <th>Ngày Nhận</th>
                                                            <th>Ngày hẹn trả</th>
                                                            <th>Trạng thái</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        {receiptData.data.map((receipt) => (
                                                            <tr key={receipt.id}>
                                                                <td>
                                                                    <Link
                                                                        to={`/warranty/receipt-detail/${receipt.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {receipt?.id}
                                                                    </Link>
                                                                </td>
                                                                <td>
                                                                    <Link
                                                                        to={`/warranty/receipt-detail/${receipt.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {receipt?.productGuarantee.customer.fullName}
                                                                    </Link>
                                                                </td>
                                                                <td>{receipt.productGuarantee.customer.email}</td>
                                                                <td>{receipt.productGuarantee.customer.phoneNumber}</td>
                                                                <td>{receipt.productGuarantee.nameModel}</td>
                                                                <td>{receipt.productGuarantee.ime}</td>
                                                                <td>{receipt.quantity}</td>
                                                                <th>{receipt.employeeCreate.employeeCode}</th>
                                                                <th>{receipt.employeeCreate.employeeName}</th>
                                                                <th>{new Date(receipt.createDate).toLocaleDateString()}</th>
                                                                <th>{new Date(receipt.payDate).toLocaleDateString()}</th>
                                                                <th>{receipt.status ? "Chưa Trả Khách" : "Đã Trả"}</th>
                                                            </tr>
                                                        ))}
                                                    </tbody>
                                                </table>
                                                <div
                                                    className="d-flex justify-content-center mt-3"
                                                    id="pagination"
                                                >
                                                    <ReactPaginate
                                                        nextLabel="next >"
                                                        onPageChange={handlePageClick}
                                                        pageRangeDisplayed={3}
                                                        marginPagesDisplayed={2}
                                                        pageCount={receiptData?.totalPages}
                                                        previousLabel="< previous"
                                                        pageClassName="page-item"
                                                        pageLinkClassName="page-link"
                                                        previousClassName="page-item"
                                                        previousLinkClassName="page-link"
                                                        nextClassName="page-item"
                                                        nextLinkClassName="page-link"
                                                        breakLabel="..."
                                                        breakClassName="page-item"
                                                        breakLinkClassName="page-link"
                                                        containerClassName="pagination"
                                                        activeClassName="active"
                                                        renderOnZeroPageCount={null}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ) : (
                                <p>Không có sản phẩm nào !!!</p>
                            )}
                        </div>
                    )}
                     {status === "NO" && (
                        <div className="search-results mt-3">
                            {receiptNoData && receiptNoData.data.length > 0 ? (
                                <div className="row">
                                    <div className="col-12">
                                        <div className="card">
                                            <div className="card-body">
                                                <table className="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Tên Khách hàng</th>
                                                            <th>Email</th>
                                                            <th>Số Điện Thoại</th>
                                                            <th>Hãng Sản Phẩm</th>
                                                            <th>Số IME</th>
                                                            <th>Số Lượng</th>
                                                            <th>Mã Nhân Viên Nhận</th>
                                                            <th>Tên Nhân Viên Nhận</th>
                                                            <th>Ngày Nhận</th>
                                                            <th>Ngày hẹn trả</th>
                                                            <th>Trạng thái</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        {receiptNoData.data.map((receipt) => (
                                                            <tr key={receipt.id}>
                                                                <td>
                                                                    <Link
                                                                        to={`/warranty/receipt-detail/${receipt.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {receipt?.id}
                                                                    </Link>
                                                                </td>
                                                                <td>
                                                                    <Link
                                                                        to={`/warranty/receipt-detail/${receipt.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {receipt?.productGuarantee.customer.fullName}
                                                                    </Link>
                                                                </td>
                                                                <td>{receipt.productGuarantee.customer.email}</td>
                                                                <td>{receipt.productGuarantee.customer.phoneNumber}</td>
                                                                <td>{receipt.productGuarantee.nameModel}</td>
                                                                <td>{receipt.productGuarantee.ime}</td>
                                                                <td>{receipt.quantity}</td>
                                                                <th>{receipt.employeeCreate.employeeCode}</th>
                                                                <th>{receipt.employeeCreate.employeeName}</th>
                                                                <th>{new Date(receipt.createDate).toLocaleDateString()}</th>
                                                                <th>{new Date(receipt.payDate).toLocaleDateString()}</th>
                                                                <th>{receipt.status ? "Chưa Trả Khách" : "Đã Trả"}</th>
                                                            </tr>
                                                        ))}
                                                    </tbody>
                                                </table>
                                                <div
                                                    className="d-flex justify-content-center mt-3"
                                                    id="pagination"
                                                >
                                                    <ReactPaginate
                                                        nextLabel="next >"
                                                        onPageChange={handleNoPageClick}
                                                        pageRangeDisplayed={3}
                                                        marginPagesDisplayed={2}
                                                        pageCount={receiptNoData?.totalPages}
                                                        previousLabel="< previous"
                                                        pageClassName="page-item"
                                                        pageLinkClassName="page-link"
                                                        previousClassName="page-item"
                                                        previousLinkClassName="page-link"
                                                        nextClassName="page-item"
                                                        nextLinkClassName="page-link"
                                                        breakLabel="..."
                                                        breakClassName="page-item"
                                                        breakLinkClassName="page-link"
                                                        containerClassName="pagination"
                                                        activeClassName="active"
                                                        renderOnZeroPageCount={null}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ) : (
                                <p>Không có sản phẩm nào !!!</p>
                            )}
                        </div>
                    )}
                </div>
            </section>
        </>
    );
}

export default WarrantyReceiptList;