import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { useLazySearchHistoryMaterialQuery } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";
import { Link } from "react-router-dom";

function WareSearchMaterial() {
    const [term, setTerm] = useState("");

    const [getMaterial, { data: materialData, isLoading: materialLoading }] = useLazySearchHistoryMaterialQuery();

    useEffect(() => {
        getMaterial({
            page: 1,
            pageSize: 10,
            term: term
        })
    }, [term])

    if (materialLoading) {
        return <h2>Loading...</h2>
    }

    const handlePageClick = (page) => {
        getMaterial({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    }

    console.log(materialData)
    return (
        <>
            <div className="search-container">
                <input
                    className="input-search"
                    type="text"
                    placeholder="Tìm kiếm..."
                    value={term}
                    onChange={(e) => setTerm(e.target.value)}
                />
                <div className="search-results mt-3">
                    {materialData && materialData.data.length > 0 ? (
                        <div>
                            <table className="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Mã Vật Liệu</th>
                                        <th>Tên Model</th>
                                        <th>Loại Linh Kiện</th>
                                        <th>Vendor</th>
                                        <th>Số Lượng</th>
                                        <th>Mã Nhân Viên Nhập</th>
                                        <th>Tên Nhân Viên Nhập</th>
                                        <th>Ngày Nhập</th>
                                        <th>Ngày Cập Nhật</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {materialData?.data.map((material, index) => (
                                        <tr key={index}>
                                            <td>
                                                <Link to={`/warehouse/material/${material.id}`} className="text-decoration-none">
                                                    {material.code ? material.code : ""}
                                                </Link>
                                            </td>
                                            <td>{material.nameModel ? material.nameModel : ""}</td>
                                            <td>
                                                {material.components ? material.components.name : ""}
                                            </td>
                                            <td>
                                                {material.vendor ? material.vendor.name : ""}
                                            </td>
                                            <td>{material.remainingQuantity ? material.remainingQuantity : 0}</td>
                                            <td>
                                                {material.warehouseEmployee
                                                    ? material.warehouseEmployee.employeeCode
                                                    : ""}
                                            </td>
                                            <td>
                                                {material.warehouseEmployee
                                                    ? material.warehouseEmployee.employeeName
                                                    : ""}
                                            </td>
                                            <td>
                                                {material.createDate
                                                    ? new Date(material.createDate).toLocaleDateString()
                                                    : ""}
                                            </td>
                                            <td>
                                                {material.updateDate
                                                    ? new Date(
                                                        material.updateDate
                                                    ).toLocaleDateString()
                                                    : ""}
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
                        <p>Không có kết quả tìm kiếm nào !!!</p>
                    )}
                </div>
            </div>
        </>
    );
}

export default WareSearchMaterial;