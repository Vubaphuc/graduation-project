import React, { useEffect, useState } from "react";
import { useLazyGetListOrderMaterialByStatusFalseQuery, useLazyGetListOrderMaterialByStatusTrueQuery } from "../../../../app/apis/engineer/engineerOrderMaterialApi";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";

function EngiOrderMaterialList() {
    const [status, setStatus] = useState("PENDING");

    const [getOrderTrue, { data: trueData, isLoading: trueLoading }] =
        useLazyGetListOrderMaterialByStatusTrueQuery();
    const [getOrderFalse, { data: fasleData, isLoading: falseLoaing }] =
        useLazyGetListOrderMaterialByStatusFalseQuery();

        useEffect(() => {
            getOrderTrue({
                page: 1,
                pageSize: 10
            })
        },[])

        useEffect(() => {
            getOrderFalse({
                page: 1,
                pageSize: 10
            })
        },[])

    if (trueLoading || falseLoaing) {
        return <h2>Loading....</h2>;
    }

    const handlePageClickTrue = (page) => {
        getOrderTrue({
            page: page.selected + 1,
            pageSize: 10
        })
    };
    const handlePageClickfalse = (page) => {
        getOrderFalse({
            page: page.selected + 1,
            pageSize: 10
        })
    };


    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    {status === "OK" && (
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="btn-lua-chon">
                                            <label htmlFor="statusSelect" className="mb-2">
                                                Trạng Thái:
                                            </label>
                                            <select
                                                id="statusSelect"
                                                className="form-control"
                                                value={status}
                                                onChange={(e) => setStatus(e.target.value)}
                                            >
                                                <option value="OK">OK</option>
                                                <option value="PENDING">PENDING</option>
                                            </select>
                                        </div>
                                        <div className="search-results mt-3">
                                            {trueData &&
                                                trueData?.data.length > 0 ? (
                                                <div>
                                                    <table className="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>Mã Oder</th>
                                                                <th>Mã Vật Liệu</th>
                                                                <th>Tên Model</th>
                                                                <th>Số Lượng</th>
                                                                <th>Mã Nhân Viên Oder</th>
                                                                <th>Tên Nhân Viên Oder</th>
                                                                <th>Trạng Thái</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            {trueData.data.map((order) => (
                                                                <tr key={order.id}>
                                                                    <td>{order.id}</td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/engineer/order/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order?.orderCode}
                                                                        </Link>
                                                                    </td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/engineer/order/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order?.material.code}
                                                                        </Link>
                                                                    </td>
                                                                    <td>{order?.material.nameModel}</td>
                                                                    <td>{order?.quantity}</td>
                                                                    <td>{order?.orderer.employeeCode}</td>
                                                                    <td>{order?.orderer.employeeName}</td>
                                                                    <td>
                                                                        {order?.status
                                                                            ? "OK"
                                                                            : "PENDING"}
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
                                                            onPageChange={handlePageClickTrue}
                                                            pageRangeDisplayed={3}
                                                            marginPagesDisplayed={2}
                                                            pageCount={trueData?.totalPages}
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
                                            ) : (
                                                <p>Không có Order Material OK nào !!!</p>
                                            )}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}
                    {status === "PENDING" && (
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="btn-lua-chon">
                                            <label htmlFor="statusSelect" className="mb-2">
                                                Trạng Thái:
                                            </label>
                                            <select
                                                id="statusSelect"
                                                className="form-control"
                                                value={status}
                                                onChange={(e) => setStatus(e.target.value)}
                                            >
                                                <option value="OK">OK</option>
                                                <option value="PENDING">PENDING</option>
                                            </select>
                                        </div>
                                        <div className="search-results mt-3">
                                            {fasleData &&
                                                fasleData?.data.length > 0 ? (
                                                <div>
                                                    <table className="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>Mã Oder</th>
                                                                <th>Mã Vật Liệu</th>
                                                                <th>Tên Model</th>
                                                                <th>Số Lượng</th>
                                                                <th>Mã Nhân Viên Oder</th>
                                                                <th>Tên Nhân Viên Oder</th>
                                                                <th>Trạng Thái</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            {fasleData.data.map((order) => (
                                                                <tr key={order.id}>
                                                                    <td>{order.id}</td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/engineer/order/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order.orderCode}
                                                                        </Link>
                                                                    </td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/engineer/order/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order.material.code}
                                                                        </Link>
                                                                    </td>
                                                                    <td>{order.material.nameModel}</td>
                                                                    <td>{order?.quantity}</td>
                                                                    <td>{order?.orderer.employeeCode}</td>
                                                                    <td>{order?.orderer.employeeName}</td>
                                                                    <td>
                                                                        {order?.isStatus === true
                                                                            ? "OK"
                                                                            : "PENDING"}
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
                                                            onPageChange={handlePageClickfalse}
                                                            pageRangeDisplayed={3}
                                                            marginPagesDisplayed={2}
                                                            pageCount={fasleData?.totalPages}
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
                                            ) : (
                                                <p>Không có Order Material Pending nào !!!</p>
                                            )}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}
                </div>
            </section>
        </>
    );
}

export default EngiOrderMaterialList;