import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { useLazySearchProductByCustomerQuery } from "../../../../app/apis/receptionist/customerApi";
import { Link } from "react-router-dom";

function RecepCustomerList() {
    const [term, setTerm] = useState("");
    const [status, setStatus] = useState("OK");

    const [getCustomer, { data: customerData, isLoading: productLoading }] = useLazySearchProductByCustomerQuery();

    useEffect(() => {
        getCustomer({
            page: 1,
            pageSize: 10,
            term: term
        })
    }, [term])

    if (productLoading) {
        return <h2>Loading...</h2>
    }


    const handlePageClick = (page) => {
        getCustomer({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    }

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="search-container">
                        <input
                            className="input-search mb-4"
                            type="text"
                            placeholder="Tìm kiếm khách hàng..."
                            value={term}
                            onChange={(e) => setTerm(e.target.value)}
                        />
                    </div>
                    <div className="search-results mt-3">
                        {customerData && customerData.data.length > 0 ? (
                            <div className="row">
                                <div className="col-12">
                                    <div className="card">
                                        <div className="card-body">
                                            <table className="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Mã Khách Hàng</th>
                                                        <th>Họ và Tên</th>
                                                        <th>Số Điện Thoại</th>
                                                        <th>Email</th>
                                                        <th>Số lượng sản phẩm</th>
                                                        <th>Lựa Chọn</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {
                                                        customerData.data.map((dataPending) => (
                                                            <tr key={dataPending.id}>
                                                                <td>
                                                                    <Link
                                                                        to={`/receptionist/customer/${dataPending.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {dataPending?.id}
                                                                    </Link>
                                                                </td>
                                                                <td>
                                                                    <Link
                                                                        to={`/receptionist/customer/${dataPending.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {dataPending?.fullName}
                                                                    </Link>
                                                                </td>
                                                                <td>{dataPending?.phone}</td>
                                                                <td>{dataPending?.email}</td>
                                                                <td>{dataPending?.sumProduct}</td>
                                                                <td>
                                                                    <Link
                                                                        to={`/receptionist/product/create/${dataPending?.id}`}
                                                                        className="btn btn-info px-2"
                                                                    >
                                                                        Thêm Sản Phẩm
                                                                    </Link>
                                                                </td>
                                                                <td><Link to={`/receptionist/update-customer/${dataPending.id}`} className="btn btn-sm btn-primary" href="">Detail</Link>
                                                                </td>
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
                                                    pageCount={customerData?.totalPages}
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
                            <p>Không có khách hàng nào !!!</p>
                        )}
                    </div>
                </div>
            </section>
        </>
    );
}

export default RecepCustomerList;