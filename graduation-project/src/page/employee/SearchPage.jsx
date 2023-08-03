import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import DatePicker from "react-datepicker";
import moment from "moment";
import { useLazySearchHistoryProductQuery } from "../../app/apis/employee/employeeApi";
import { exportToExcel } from "../formHTML/exportToExcel";
import { useNavigate } from "react-router-dom";

function SearchPage() {
    const [term, setTerm] = useState("");
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const navigate = useNavigate();


    const [getProduct, { data: productData, isLoading: productLoading }] = useLazySearchHistoryProductQuery();

    useEffect(() => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProduct({
            page: 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
            term: term
        });
    }, [])

    if (productLoading) {
        return <h2>Loading...</h2>
    }

    const handlePageClick = (page) => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProduct({
            page: page.selected + 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
            term: term
        });
    }

    const handleClickSearch = () => {
        const formattedStartDate = startDate ? moment(startDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const formattedEndDate = endDate ? moment(endDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        getProduct({
            page: 1,
            pageSize: 10,
            startDate: formattedStartDate,
            endDate: formattedEndDate,
            term: term
        });
    }


    const exportProductToExcel = () => {
        const data = productData.data ? productData.data : [];
        exportToExcel(data)
    }

    const handleResfed = () => {
        setTerm("");
        setStartDate(null);
        setEndDate(null);
        getProduct({
            page: 1,
            pageSize: 10,
            startDate: "",
            endDate: "",
            term: ""
        });
    }

    return (
        <>
            <section className="content mt-5">
            <button className="btn btn-success ms-4 mb-2 px-4" onClick={handleResfed}>Resfer</button>
                <div className="text-center rounded p-4">
                    <div className="d-flex justify-content-between mb-3">
                        <div className="d-flex">
                            <DatePicker
                                selected={startDate}
                                onChange={(date) => setStartDate(date)}
                                selectsStart
                                startDate={startDate}
                                endDate={endDate}
                                placeholderText="Từ ngày"
                                dateFormat="dd-MM-yyyy"
                                className="px-4"
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
                                className="px-4"
                            />
                            <div>
                                <input
                                    type="text"
                                    className="ms-3 px-4"
                                    placeholder="Tìm kiếm theo IME..."
                                    value={term}
                                    onChange={(e) => setTerm(e.target.value)}
                                />
                            </div>
                            <button className="btn btn-info ms-4 px-4" variant="warning" onClick={handleClickSearch}>Tìm Kiếm</button>
                            
                        </div>
                        <div>
                            <button className="btn btn-warning px-4" variant="warning" onClick={exportProductToExcel}>Export</button>
                        </div>
                    </div>
                    <div className="container-fluid">
                        <div className="search-results mt-3">
                            {productData && productData.data.length > 0 ? (
                                <div className="row">
                                    <div className="card mb-3">
                                    </div>
                                    <div className="col-12">
                                        <div className="card">
                                            <div className="card-body">
                                                <table className="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Model Name</th>
                                                            <th>IME</th>
                                                            <th>Defect Name</th>
                                                            <th>Input Date</th>
                                                            <th>Employee Reception Code</th>
                                                            <th>Employee Reception Name</th>
                                                            <th>Location</th>
                                                            <th>Component Name</th>
                                                            <th>OutPut Date</th>
                                                            <th>Engineer Code</th>
                                                            <th>Engineer Name</th>
                                                            <th>Finish Date</th>
                                                            <th>Employee Pay Code</th>
                                                            <th>Employee Pay Name</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        {productData.data.map((product) => (
                                                            <tr key={product.id}>
                                                                <td>{product.nameModel}</td>
                                                                <td>{product.ime}</td>
                                                                <td>{product.defectName}</td>
                                                                <td>{new Date(product.inputDate).toLocaleDateString()}</td>
                                                                <td>{product.receptionists ? product.receptionists.employeeCode : ""}</td>
                                                                <td>{product.receptionists ? product.receptionists.employeeName : ""}</td>
                                                                <td>{product.location ? product.location : ""}</td>
                                                                <td>{product.components ? product.components.name : ""}</td>
                                                                <td>{product.inputDate ? new Date(product.outputDate).toLocaleDateString() : ""}</td>
                                                                <td>{product.engineer ? product.engineer.employeeCode : ""}</td>
                                                                <td>{product.engineer ? product.engineer.employeeName : ""}</td>
                                                                <td>{product.finishDate ? new Date(product.finishDate).toLocaleDateString() : ""}</td>
                                                                <td>{product.productPayer ? product.productPayer.employeeCode : ""}</td>
                                                                <td>{product.productPayer ? product.productPayer.employeeName : ""}</td>
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
                                                        pageCount={1}
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
                                <div className="card">
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default SearchPage;