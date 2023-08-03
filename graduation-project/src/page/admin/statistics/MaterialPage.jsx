import { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useLazyFindMaterialUpdateQuery, useLazyFindOrderMaterialsAllQuery, useTotalPriceAndQuantityMaterialQuery } from "../../../app/apis/admin/materialManageApi";
import moment from "moment";
import { Link } from "react-router-dom";

function MaterialPage() {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);

    const [getMaterial, { data: orderMaterialData, isLoading: orderMaterialLoading }] = useLazyFindOrderMaterialsAllQuery();
    const { data: totalData } = useTotalPriceAndQuantityMaterialQuery();
    const [getUpdateMaterial, { data: updateData }] = useLazyFindMaterialUpdateQuery();

    useEffect(() => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        getMaterial({
            page: 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
        });
    }, [])

    useEffect(() => {
        getUpdateMaterial({
            page: 1,
            pageSize: 5
        })
    }, [])

    if (orderMaterialLoading) {
        return <h2>Loading....</h2>
    }


    const handleClickSearch = () => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";

        getMaterial({
            page: 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
        });
    }

    const exportOrderMaterialToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-order-material";
        window.location.href = url;
    }

    const handlePageClick = (page) => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        getMaterial({
            page: page.selected + 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
        });
    }

    const exportMaterialUpdateToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-material-update";
        window.location.href = url;
    }


    return (
        <>

            <div className="tk">
                <Link to={"/admin/materials"} className="tk-ct d-flex align-items-center justify-content-between p-4 text-decoration-none">
                    <i className="fa fa-chart-line fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Import Quantity</p>
                        <h6 className="mb-0">{totalData?.totalImportQuantity}</h6>
                    </div>
                </Link>
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-line fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Import Price</p>
                        <h6 className="mb-0">{totalData?.totalImportPrice?.toLocaleString('vi-VN') + " VND"}</h6>
                    </div>
                </div>
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-bar fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Total Export Quantity</p>
                        <h6 className="mb-0">{totalData?.totalExportQuantity}</h6>
                    </div>
                </div>
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-bar fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Export Price</p>
                        <h6 className="mb-0">{totalData?.totalExportPrice?.toLocaleString('vi-VN') + " VND"}</h6>
                    </div>
                </div>
            </div>

            <div className="tk">
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-pie fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Month Export Quantity</p>
                        <h6 className="mb-0">{totalData?.totalMonthExportQuantity}</h6>
                    </div>
                </div>
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-area fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Month Export Price</p>
                        <h6 className="mb-0">{totalData?.totalMonthExportPrice?.toLocaleString('vi-VN') + " VND"}</h6>
                    </div>
                </div>
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-area fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Today Export Quantity</p>
                        <h6 className="mb-0">{totalData?.totalToDayExportQuantity}</h6>
                    </div>
                </div>
                <div className="tk-ct d-flex align-items-center justify-content-between p-4">
                    <i className="fa fa-chart-pie fa-3x tk-ct-icon"></i>
                    <div className="ms-3 tk-ct-text">
                        <p className="mb-2">Today Export Price</p>
                        <h6 className="mb-0">{totalData?.totalTodayExportPrice?.toLocaleString('vi-VN') + " VND"}</h6>
                    </div>
                </div>
            </div>

            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">
                    <div className="d-flex justify-content-between mb-3">
                        <div>
                            <DatePicker
                                selected={startDate}
                                onChange={(date) => setStartDate(date)}
                                selectsStart
                                startDate={startDate}
                                endDate={endDate}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <span className="mx-2">-</span>
                            <DatePicker
                                selected={endDate}
                                onChange={(date) => setEndDate(date)}
                                selectsEnd
                                startDate={startDate}
                                endDate={endDate}
                                placeholderText="Đến ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <button className="btn btn-info ms-3" variant="warning" onClick={handleClickSearch}>Tìm Kiếm</button>
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportOrderMaterialToExcel}>Export</button>
                        </div>
                    </div>
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Order Vật Liệu</h6>
                    </div>
                    {orderMaterialData && orderMaterialData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Ngày Order</th>
                                        <th scope="col">Mã Order</th>
                                        <th scope="col">Mã Vật Liệu</th>
                                        <th scope="col">Loại Linh Kiện</th>
                                        <th scope="col">Số Lượng Order</th>
                                        <th scope="col">Tình Trạng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {orderMaterialData.data.map((order, index) => (
                                        <tr key={index}>
                                            <td>{new Date(order.createDate).toLocaleDateString()}</td>
                                            <td>{order.orderCode}</td>
                                            <td>{order.material.code}</td>
                                            <td>{order.material.components.name}</td>
                                            <td>{order.quantity}</td>
                                            <td>{order.status ? "Approved" : "Pending"}</td>                               
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
                                    pageCount={orderMaterialData?.totalPages}
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
                        <p>Không có Thông tin nào!!!</p>
                    )}
                </div>
            </div>

            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">                 
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Vật Liệu update</h6>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportMaterialUpdateToExcel}>Export</button>
                        </div>
                    </div>
                    {updateData && updateData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Ngày Update</th>
                                        <th scope="col">Mã Vật Liệu</th>
                                        <th scope="col">Số Lượng update</th>
                                        <th scope="col">Mã Nhân Viên Update</th>
                                        <th scope="col">Tên Nhân Viên Update</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {updateData.data.map((material, index) => (
                                        <tr key={index}>
                                            <td>{new Date(material.updateDate).toLocaleDateString()}</td>
                                            <td>{material.material.code}</td>
                                            <td>{material.quantity}</td>
                                            <td>{material.employeeUpdate.employeeCode}</td>  
                                            <td>{material.employeeUpdate.employeeName}</td>                        
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
                                    pageCount={updateData?.totalPages}
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
                        <p>Không có Thông tin nào!!!</p>
                    )}
                </div>
            </div>
            <div className="footer">
            </div>
        </>
    )
}

export default MaterialPage;