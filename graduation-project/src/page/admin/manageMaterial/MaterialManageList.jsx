import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import ReactPaginate from "react-paginate";
import { useLazyFindListTotalQuantityExportMaterialByMaterialCodeQuery, useLazyFindMaterialsAllQuery } from "../../../app/apis/admin/materialManageApi";
import { Link } from "react-router-dom";
import moment from "moment";

function MaterialManageList() {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [term, setTerm] = useState("");

    const [getMaterial, { data: materialData, isLoading: materialLoading }] = useLazyFindMaterialsAllQuery();
    const [getTotalMaterial, { data: totalMaterialData }] = useLazyFindListTotalQuantityExportMaterialByMaterialCodeQuery();

    useEffect(() => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        getMaterial({
            page: 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
            term: term
        });
    }, [term])

    useEffect(() => {
        getTotalMaterial({
            page: 1,
            pageSize: 5
        })
    }, [])

    if (materialLoading) {
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
            term: term
        });

    }

    const exportMaterialToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-material";
        window.location.href = url;
    }

    const exportMaterialByCodeToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-total-material";
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
            term: term
        });
    }

    const handlePageClickTotalMaterial = (page) => {
        getTotalMaterial({
            page: page.selected + 1,
            pageSize: 5
        })
    }



    return (
        <>
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
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportMaterialToExcel}>Export</button>
                        </div>
                    </div>
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <h6 className="mb-0">Danh Sách Order Vật Liệu</h6>
                    </div>
                    <div className="search-container">
                        <input
                            className="input-search mb-4"
                            type="text"
                            placeholder="Tìm kiếm theo mã vật liệu..."
                            value={term}
                            onChange={(e) => setTerm(e.target.value)}
                        />
                    </div>
                    {materialData && materialData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Ngày Create</th>
                                        <th scope="col">Mã Vật Liệu</th>
                                        <th scope="col">Số Lượng Import</th>
                                        <th scope="col">Số Lượng Export</th>
                                        <th scope="col">Số Lượng Còn Lại</th>
                                        <th scope="col">Tên Model</th>
                                        <th scope="col">Giá Tiền / 1c</th>
                                        <th scope="col">Mã Nhân Viên Nhập</th>
                                        <th scope="col">Tên Nhân Viên Nhập</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {materialData.data.map((material, index) => (
                                        <tr key={index}>
                                            <td>{new Date(material.createDate).toLocaleDateString()}</td>
                                            <td>{material.code}</td>
                                            <td>{material.importQuantity}</td>
                                            <td>{material.exportQuantity}</td>
                                            <td>{material.remainingQuantity}</td>
                                            <td>{material.nameModel}</td>
                                            <td>{material.price}</td>
                                            <td>{material.warehouseEmployee.employeeCode}</td>
                                            <td>{material.warehouseEmployee.employeeName}</td>
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
                                    pageCount={materialData?.totalPages}
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
                        <h6 className="mb-0">Danh Sách Export Vật liệu theo từng mã Vật liệu</h6>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportMaterialByCodeToExcel}>Export</button>
                        </div>
                    </div>
                    
                    {totalMaterialData && totalMaterialData.data.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr className="text-white">
                                        <th scope="col">Mã Vật Liệu</th>
                                        <th scope="col">Loại Linh Kiện</th>
                                        <th scope="col">Tổng số Lượng</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    {totalMaterialData.data.map((material, index) => (
                                        <tr key={index}>
                                            <td>{material.materialCode}</td>
                                            <td>{material.componentName}</td>
                                            <td>{material.totalQuantityExport}</td>
                                            <td>Đã Hoàn Thành</td>
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
                                    onPageChange={handlePageClickTotalMaterial}
                                    pageRangeDisplayed={3}
                                    marginPagesDisplayed={2}
                                    pageCount={totalMaterialData?.totalPages}
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

export default MaterialManageList;