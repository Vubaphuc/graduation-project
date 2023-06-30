import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import moment from "moment";
import { getStatusLabel } from "../../formHTML/enum";
import { useLazyFindProductGuaranteeDeliveredAllQuery, useLazyFindProductGuaranteeUnderRepairAllQuery, useLazyFindProductGuaranteeWaitingForRepairAllQuery, useLazyFindProductGuaranteeWaitingForReturnAllQuery } from "../../../app/apis/admin/productGuaranteeManageApi";
import { useLazyFindProductGuaranteeRepairedAllQuery } from "../../../app/apis/admin/productGuaranteeManageApi";
import { exportProductGuaranteeToExcel } from "../../formHTML/exportToExcel";


function ProductGuaranteePage() {
    const [startDate1, setStartDate1] = useState(null);
    const [endDate1, setEndDate1] = useState(null);
    const [startDate2, setStartDate2] = useState(null);
    const [endDate2, setEndDate2] = useState(null);
    const [startDate3, setStartDate3] = useState(null);
    const [endDate3, setEndDate3] = useState(null);
    const [startDate4, setStartDate4] = useState(null);
    const [endDate4, setEndDate4] = useState(null);
    const [startDate5, setStartDate5] = useState(null);
    const [endDate5, setEndDate5] = useState(null);

    const [getProductForRepair, { data: forRepairData, isLoading }] = useLazyFindProductGuaranteeWaitingForRepairAllQuery();
    const [getProductUnderRepair, { data: underRepairData }] = useLazyFindProductGuaranteeUnderRepairAllQuery();
    const [getProductRepaired, { data: repairedData }] = useLazyFindProductGuaranteeRepairedAllQuery();
    const [getProductForReturn, { data: forReturnData }] = useLazyFindProductGuaranteeWaitingForReturnAllQuery();
    const [getProductDelivered, { data: deliveredData }] = useLazyFindProductGuaranteeDeliveredAllQuery();

    useEffect(() => {
        const formattedStartDate1 = startDate1 ? moment(startDate1).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate1 = endDate1 ? moment(endDate1).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductForRepair({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate1,
            endDate: formattedEndDate1
        })
    }, [])

    useEffect(() => {
        const formattedStartDate2 = startDate2 ? moment(startDate2).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate2 = endDate2 ? moment(endDate2).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductUnderRepair({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate2,
            endDate: formattedEndDate2
        })
    }, [])

    useEffect(() => {
        const formattedStartDate3 = startDate3 ? moment(startDate3).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate3 = endDate3 ? moment(endDate3).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductRepaired({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate3,
            endDate: formattedEndDate3
        })
    }, [])

    useEffect(() => {
        const formattedStartDate4 = startDate4 ? moment(startDate4).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate4 = endDate4 ? moment(endDate4).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductForReturn({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate4,
            endDate: formattedEndDate4
        })
    }, [])

    useEffect(() => {
        const formattedStartDate5 = startDate5 ? moment(startDate5).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate5 = endDate5 ? moment(endDate5).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductDelivered({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate5,
            endDate: formattedEndDate5
        })
    }, [])


    if (isLoading) {
        return <h2>Loading...</h2>
    }

    const handleClickSearchWaitingForRepair = () => {
        const formattedStartDate1 = startDate1 ? moment(startDate1).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate1 = endDate1 ? moment(endDate1).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductForRepair({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate1,
            endDate: formattedEndDate1
        })
    }
    const handleClickSearchUnderRepair = () => {
        const formattedStartDate2 = startDate2 ? moment(startDate2).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate2 = endDate2 ? moment(endDate2).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductUnderRepair({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate2,
            endDate: formattedEndDate2
        })
    }
    const handleClickSearchRepaired = () => {
        const formattedStartDate3 = startDate3 ? moment(startDate3).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate3 = endDate3 ? moment(endDate3).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductRepaired({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate3,
            endDate: formattedEndDate3
        })
    }
    const handleClickSearchWaitingForReturn = () => {
        const formattedStartDate4 = startDate4 ? moment(startDate4).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate4 = endDate4 ? moment(endDate4).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductForReturn({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate4,
            endDate: formattedEndDate4
        })
    }
    const handleClickSearchDelivered = () => {
        const formattedStartDate5 = startDate5 ? moment(startDate5).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate5 = endDate5 ? moment(endDate5).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductDelivered({
            page: 1,
            pageSize: 5,
            startDate: formattedStartDate5,
            endDate: formattedEndDate5
        })
    }


    const handlePageClickWaitingForRepair = (page) => {
        const formattedStartDate1 = startDate1 ? moment(startDate1).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate1 = endDate1 ? moment(endDate1).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductForRepair({
            page: page.selected + 1,
            pageSize: 5,
            startDate: formattedStartDate1,
            endDate: formattedEndDate1
        })
    }
    const handlePageClickUnderRepair = (page) => {
        const formattedStartDate2 = startDate2 ? moment(startDate2).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate2 = endDate2 ? moment(endDate2).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductUnderRepair({
            page: page.selected + 1,
            pageSize: 5,
            startDate: formattedStartDate2,
            endDate: formattedEndDate2
        })
    }
    const handlePageClickRepaired = (page) => {
        const formattedStartDate3 = startDate3 ? moment(startDate3).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate3 = endDate3 ? moment(endDate3).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductRepaired({
            page: page.selected + 1,
            pageSize: 5,
            startDate: formattedStartDate3,
            endDate: formattedEndDate3
        })
    }
    const handlePageClickWaitingForReturn = (page) => {
        const formattedStartDate4 = startDate4 ? moment(startDate4).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate4 = endDate4 ? moment(endDate4).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductForReturn({
            page: page.selected + 1,
            pageSize: 5,
            startDate: formattedStartDate4,
            endDate: formattedEndDate4
        })
    }
    const handlePageClickDelivered = (page) => {
        const formattedStartDate5 = startDate5 ? moment(startDate5).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate5 = endDate5 ? moment(endDate5).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProductDelivered({
            page: page.selected + 1,
            pageSize: 5,
            startDate: formattedStartDate5,
            endDate: formattedEndDate5
        })
    }


    const exportProductGuaranteeWaitingForRepairToExcel = () => {
        exportProductGuaranteeToExcel(forRepairData.data)
    }

    const exportProductGuaranteeUnderRepairToExcel = () => {
        exportProductGuaranteeToExcel(underRepairData.data)
    }

    const exportProductGuaranteeRepairedToExcel = () => {
        exportProductGuaranteeToExcel(repairedData.data)
    }

    const exportProductGuaranteeWaitingForReturnToExcel = () => {
        exportProductGuaranteeToExcel(forReturnData.data)
    }

    const exportProductGuaranteeDeliveredToExcel = () => {
        exportProductGuaranteeToExcel(deliveredData.data)
    }

    return (
        <>
            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Sản Phẩm Chờ Sửa Chữa</h6>
                    </div>
                    <div className="d-flex justify-content-between mb-3">
                        <div>
                            <DatePicker
                                selected={startDate1}
                                onChange={(date) => setStartDate1(date)}
                                selectsStart
                                startDate={startDate1}
                                endDate={endDate1}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <span className="mx-2">-</span>
                            <DatePicker
                                selected={endDate1}
                                onChange={(date) => setEndDate1(date)}
                                selectsEnd
                                startDate={startDate1}
                                endDate={endDate1}
                                placeholderText="Đến ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <button className="btn btn-info ms-3" variant="warning" onClick={handleClickSearchWaitingForRepair}>Tìm Kiếm</button>
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportProductGuaranteeWaitingForRepairToExcel}>Export</button>
                        </div>
                    </div>
                    {forRepairData && forRepairData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Date</th>
                                        <th scope="col">IME</th>
                                        <th scope="col">Employee Code</th>
                                        <th scope="col">Employee Name</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    {forRepairData.data.map((product, index) => (
                                        <tr key={index}>
                                            <td>{new Date(product.inputDate).toLocaleDateString()}</td>
                                            <td>{product.ime}</td>
                                            <td>{product.warranty.employeeCode}</td>
                                            <td>{product.warranty.employeeName}</td>
                                            <td>{getStatusLabel(product.status)}</td>
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
                                    onPageChange={handlePageClickWaitingForRepair}
                                    pageRangeDisplayed={3}
                                    marginPagesDisplayed={2}
                                    pageCount={forRepairData?.totalPages}
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
                        <p>Không có sản phẩm nào!!!</p>
                    )}
                </div>
            </div>
            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Sản Phẩm Đang Sửa Chữa</h6>
                    </div>
                    <div className="d-flex justify-content-between mb-3">
                        <div>
                            <DatePicker
                                selected={startDate2}
                                onChange={(date) => setStartDate2(date)}
                                selectsStart
                                startDate={startDate2}
                                endDate={endDate2}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <span className="mx-2">-</span>
                            <DatePicker
                                selected={endDate2}
                                onChange={(date) => setEndDate2(date)}
                                selectsEnd
                                startDate={startDate2}
                                endDate={endDate2}
                                placeholderText="Đến ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <button className="btn btn-info ms-3" variant="warning" onClick={handleClickSearchUnderRepair}>Tìm Kiếm</button>
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportProductGuaranteeUnderRepairToExcel}>Export</button>
                        </div>
                    </div>
                    {underRepairData && underRepairData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Date</th>
                                        <th scope="col">IME</th>
                                        <th scope="col">Employee Code</th>
                                        <th scope="col">Employee Name</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    {underRepairData.data.map((product, index) => (
                                        <tr key={index}>
                                            <td>{new Date(product.transferDate).toLocaleDateString()}</td>
                                            <td>{product.ime}</td>
                                            <td>{product.engineer.employeeCode}</td>
                                            <td>{product.engineer.employeeName}</td>
                                            <td>{getStatusLabel(product.status)}</td>
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
                                    onPageChange={handlePageClickUnderRepair}
                                    pageRangeDisplayed={3}
                                    marginPagesDisplayed={2}
                                    pageCount={underRepairData?.totalPages}
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
                        <p>Không có sản phẩm nào!!!</p>
                    )}
                </div>
            </div>
            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Sản Phẩm Đã Sửa Xong</h6>
                    </div>
                    <div className="d-flex justify-content-between mb-3">
                        <div>
                            <DatePicker
                                selected={startDate3}
                                onChange={(date) => setStartDate3(date)}
                                selectsStart
                                startDate={startDate3}
                                endDate={endDate3}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <span className="mx-2">-</span>
                            <DatePicker
                                selected={endDate3}
                                onChange={(date) => setEndDate3(date)}
                                selectsEnd
                                startDate={startDate3}
                                endDate={endDate3}
                                placeholderText="Đến ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <button className="btn btn-info ms-3" variant="warning" onClick={handleClickSearchRepaired}>Tìm Kiếm</button>
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportProductGuaranteeRepairedToExcel}>Export</button>
                        </div>
                    </div>
                    {repairedData && repairedData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Date</th>
                                        <th scope="col">IME</th>
                                        <th scope="col">Employee Code</th>
                                        <th scope="col">Employee Name</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    {repairedData.data.map((product, index) => (
                                        <tr key={index}>
                                            <td>{new Date(product.outputDate).toLocaleDateString()}</td>
                                            <td>{product.ime}</td>
                                            <td>{product.engineer.employeeCode}</td>
                                            <td>{product.engineer.employeeName}</td>
                                            <td>{getStatusLabel(product.status)}</td>
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
                                    onPageChange={handlePageClickRepaired}
                                    pageRangeDisplayed={3}
                                    marginPagesDisplayed={2}
                                    pageCount={repairedData?.totalPages}
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
                        <p>Không có sản phẩm nào!!!</p>
                    )}
                </div>
            </div>
            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Sản Phẩm Chờ Trả Khách</h6>
                    </div>
                    <div className="d-flex justify-content-between mb-3">
                        <div>
                            <DatePicker
                                selected={startDate4}
                                onChange={(date) => setStartDate4(date)}
                                selectsStart
                                startDate={startDate4}
                                endDate={endDate4}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <span className="mx-2">-</span>
                            <DatePicker
                                selected={endDate4}
                                onChange={(date) => setEndDate4(date)}
                                selectsEnd
                                startDate={startDate4}
                                endDate={endDate4}
                                placeholderText="Đến ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <button className="btn btn-info ms-3" variant="warning" onClick={handleClickSearchWaitingForReturn}>Tìm Kiếm</button>
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportProductGuaranteeWaitingForReturnToExcel}>Export</button>
                        </div>
                    </div>
                    {forReturnData && forReturnData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Date</th>
                                        <th scope="col">IME</th>
                                        <th scope="col">Employee Code</th>
                                        <th scope="col">Employee Name</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    {forReturnData.data.map((product, index) => (
                                        <tr key={index}>
                                            <td>{new Date(product.createGuaranteeDate).toLocaleDateString()}</td>
                                            <td>{product.ime}</td>
                                            <td>{product.productPayer.employeeCode}</td>
                                            <td>{product.productPayer.employeeName}</td>
                                            <td>{getStatusLabel(product.status)}</td>
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
                                    onPageChange={handlePageClickWaitingForReturn}
                                    pageRangeDisplayed={3}
                                    marginPagesDisplayed={2}
                                    pageCount={forReturnData?.totalPages}
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
                        <p>Không có sản phẩm nào!!!</p>
                    )}
                </div>
            </div>
            <div className="pt-4 px-4">
                <div className="text-center rounded p-4 table-table">
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Sản Phẩm Đã Trả Khách</h6>
                    </div>
                    <div className="d-flex justify-content-between mb-3">
                        <div>
                            <DatePicker
                                selected={startDate5}
                                onChange={(date) => setStartDate5(date)}
                                selectsStart
                                startDate={startDate5}
                                endDate={endDate5}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <span className="mx-2">-</span>
                            <DatePicker
                                selected={endDate5}
                                onChange={(date) => setEndDate5(date)}
                                selectsEnd
                                startDate={startDate5}
                                endDate={endDate5}
                                placeholderText="Đến ngày"
                                dateFormat="dd-MM-yyyy"
                            />
                            <button className="btn btn-info ms-3" variant="warning" onClick={handleClickSearchDelivered}>Tìm Kiếm</button>
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportProductGuaranteeDeliveredToExcel}>Export</button>
                        </div>
                    </div>
                    {deliveredData && deliveredData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Date</th>
                                        <th scope="col">IME</th>
                                        <th scope="col">Employee Code</th>
                                        <th scope="col">Employee Name</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    {deliveredData.data.map((product, index) => (
                                        <tr key={index}>
                                            <td>{new Date(product.finishDate).toLocaleDateString()}</td>
                                            <td>{product.ime}</td>
                                            <td>{product.productPayer.employeeCode}</td>
                                            <td>{product.productPayer.employeeName}</td>
                                            <td>{getStatusLabel(product.status)}</td>
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
                                    onPageChange={handlePageClickDelivered}
                                    pageRangeDisplayed={3}
                                    marginPagesDisplayed={2}
                                    pageCount={deliveredData?.totalPages}
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
                        <p>Không có sản phẩm nào!!!</p>
                    )}
                </div>
            </div>
        </>
    );
}

export default ProductGuaranteePage;