import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { useLazyGetListComponentsQuery } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";

function WareComponentList() {

    const [getComponet, { data: componentData, isLoading: componentLoading }] = useLazyGetListComponentsQuery();

    useEffect(() => {
        getComponet({
            page: 1, 
            pageSize: 10
        })
    },[])

    if (componentLoading) {
        return <h2>Loading....</h2>;
    }

    const handlePageClick = (page) => {
        getComponet({
            page: page.selected + 1, 
            pageSize: 10
        })
      };
    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="row py-2"></div>
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="search-results mt-3">
                                    {componentData && componentData.data.length > 0 ? (
                                        <div className="card-body">
                                            <table className="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Mã linh kiện</th>
                                                        <th>Tên linh kiện</th>
                                                        <th>Thời hạn bảo hành</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {componentData?.data.map((component) => (
                                                        <tr key={component?.id}>
                                                            <td>
                                                                <Link
                                                                    to={`/warehouse/component/${component?.id}`}
                                                                    className="text-decoration-none"
                                                                >
                                                                    {component?.id}
                                                                </Link>
                                                            </td>
                                                            <td>
                                                                <Link
                                                                    to={`/warehouse/component/${component?.id}`}
                                                                    className="text-decoration-none"
                                                                >
                                                                    {component?.name}
                                                                </Link>
                                                            </td>
                                                            <td>{component?.warrantyPeriod}</td>
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
                                                    pageCount={componentData?.totalPages}
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
                                        <p>Không có Vật Liệu nào !!!</p>
                                    )}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default WareComponentList;