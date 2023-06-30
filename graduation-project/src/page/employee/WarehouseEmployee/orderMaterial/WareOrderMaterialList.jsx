import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { useLazyGetListOrderMaterialStatusFalseQuery, useLazyGetListOrderMaterialStatusTrueQuery } from "../../../../app/apis/warehouseEmployee/approverOrderMaterialApi";

function WareOrderMaterialList() {
    const [status, setStatus] = useState("PENDING");
    const [count, setCount] = useState(0);
    const [term, setTerm] = useState("");

    const [getOrderMaterialPending, { data: OrderMaterialDataOK, isLoading: PDLoading }] = useLazyGetListOrderMaterialStatusFalseQuery();
    const [getOrderMaterialOK, {  data: OrderMaterialDataPD, isLoading: OKLoading }] = useLazyGetListOrderMaterialStatusTrueQuery();

    useEffect(() => {
        getOrderMaterialPending({
            page: 1,
            pageSize: 10,
        })
    }, [term])

    useEffect(() => {
        getOrderMaterialOK({
            page: 1,
            pageSize: 10,
        })
    }, [term])
    
    if (OKLoading || PDLoading) {
        return <h2>Loading....</h2>;
      }


      const handlePageClickOK = (page) => {
        getOrderMaterialOK({
            page: page.selected + 1,
            pageSize: 10,
        })
      };
    
      const handlePageClickPD = (page) => {
        getOrderMaterialPending({
            page: page.selected + 1,
            pageSize: 10,
        })
      };

      console.log(OrderMaterialDataOK)

    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="btn-lua-chon">
                        <label htmlFor="statusSelect" className="mb-2 mt-3">
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
                </div>
                <div className="container-fluid">
                    {status === "PENDING" && (
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="search-results mt-3">
                                            {OrderMaterialDataOK &&
                                                OrderMaterialDataOK?.data.length > 0 ? (
                                                <div>
                                                    <table className="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>Mã Oder</th>
                                                                <th>Mã Vật Liệu</th>
                                                                <th>Tên Model</th>
                                                                <th>Loại Linh Kiện</th>
                                                                <th>Số Lượng</th>
                                                                <th>Mã Nhân Viên Oder</th>
                                                                <th>Tên Nhân Viên Oder</th>
                                                                <th>Trạng Thái</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            {OrderMaterialDataOK.data.map((order) => (
                                                                <tr key={order.id}>
                                                                    <td>{order.id}</td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/warehouse/orderMaterial/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order.orderCode}
                                                                        </Link>
                                                                    </td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/warehouse/orderMaterial/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order.material.code}
                                                                        </Link>
                                                                    </td>
                                                                    <td>{order.material.nameModel}</td>
                                                                    <td>{order.material.components.name}</td>
                                                                    <td>{order.quantity}</td>
                                                                    <td>{order.orderer.employeeCode}</td>
                                                                    <td>{order.orderer.employeeName}</td>
                                                                    <td>
                                                                        {order.status
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
                                                            onPageChange={handlePageClickOK}
                                                            pageRangeDisplayed={3}
                                                            marginPagesDisplayed={2}
                                                            pageCount={OrderMaterialDataOK?.totalPages}
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
                    {status === "OK" && (
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="search-results mt-3">
                                            {OrderMaterialDataPD &&
                                                OrderMaterialDataPD?.data.length > 0 ? (
                                                <div>
                                                    <table className="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>Mã Oder</th>
                                                                <th>Mã Vật Liệu</th>
                                                                <th>Tên Model</th>
                                                                <th>Loại Linh Kiện</th>
                                                                <th>Số Lượng</th>
                                                                <th>Mã Nhân Viên Oder</th>
                                                                <th>Tên Nhân Viên Oder</th>
                                                                <th>Trạng Thái</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            {OrderMaterialDataPD.data.map((order) => (
                                                                <tr key={order.id}>
                                                                    <td>{order.id}</td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/warehouse/orderMaterial/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order?.orderCode}
                                                                        </Link>
                                                                    </td>
                                                                    <td>
                                                                        <Link
                                                                            to={`/warehouse/orderMaterial/${order.id}`}
                                                                            className="text-decoration-none"
                                                                        >
                                                                            {order.material.code}
                                                                        </Link>
                                                                    </td>
                                                                    <td>{order.material.nameModel}</td>
                                                                    <td>{order.material.components.name}</td>
                                                                    <td>{order.quantity}</td>
                                                                    <td>{order.orderer.employeeCode}</td>
                                                                    <td>{order.orderer.employeeName}</td>
                                                                    <td>
                                                                        {order.status
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
                                                            onPageChange={handlePageClickPD}
                                                            pageRangeDisplayed={3}
                                                            marginPagesDisplayed={2}
                                                            pageCount={OrderMaterialDataPD?.totalPages}
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

export default WareOrderMaterialList;