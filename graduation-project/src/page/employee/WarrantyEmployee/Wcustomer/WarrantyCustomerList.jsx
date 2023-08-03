import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { useLazyFindProductByCustomersQuery } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";

function WarrantyCustomerList() {
    const [term, setTerm] = useState("");

    const [getCustomer, { data: customerData, isLoading: productLoading }] = useLazyFindProductByCustomersQuery();

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
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {
                                                        customerData.data.map((customer) => (
                                                            <tr key={customer.id}>
                                                                <td>
                                                                    {customer?.id}
                                                                </td>
                                                                <td>
                                                                    {customer?.fullName}
                                                                </td>
                                                                <td>{customer?.phoneNumber}</td>
                                                                <td>{customer?.email}</td>
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

export default WarrantyCustomerList;