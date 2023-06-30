import React, { useEffect } from "react";
import ReactPaginate from "react-paginate";
import { Link, useParams } from "react-router-dom";
import { useLazyGetListVendorByIdQuery } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";

function WareVendorDetail() {
    const { vendorId } = useParams();

    const [getVendors, { data: materialData, isLoading: materialLoading }] = useLazyGetListVendorByIdQuery();

    useEffect(() => {
        getVendors({
            page: 1,
            pageSize: 10,
            vendorId: vendorId
        })
    }, [])

    if (materialLoading) {
        return <h2>Loading....</h2>;
    }


    const handlePageClick = (page) => {
        getVendors({
            page: page.selected + 1,
            pageSize: 10,
            vendorId: vendorId
        })
    };

    console.log(materialData)
    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="row py-2">
                        <div className="col-12">
                            <Link
                                to={"/warehouse/vendors"}
                                className="btn btn-default"
                            >
                                Quay Lại
                            </Link>
                            <Link
                                to={`/warehouse/vendor/${vendorId}`}
                                className="btn btn-info"
                            >
                                <i className="fas fa-redo"></i> Refresh
                            </Link>
                        </div>
                    </div>
                    <div className="search-results mt-3">
                        {materialData && materialData.data.length > 0 ? (
                            <div className="row">
                                <div className="col-12">
                                    <div className="card">
                                        <div className="card-body">
                                            <table className="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Mã Vật Liệu</th>
                                                        <th>Tên Model</th>
                                                        <th>Loại Linh Kiện</th>
                                                        <th>Số Lượng</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {materialData?.data.map((material) => (
                                                        <tr key={material?.id}>
                                                            <td>
                                                                <Link
                                                                    to={`/warehouse/material/${material?.id}`}
                                                                    className="text-decoration-none"
                                                                >
                                                                    {material?.code}
                                                                </Link>
                                                            </td>
                                                            <td>{material?.nameModel}</td>
                                                            <td>{material?.components.name}</td>
                                                            <td>{material?.remainingQuantity}</td>
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
                                    </div>
                                </div>
                            </div>
                        ) : (
                            <p>Không có vật liệu nào !!!</p>
                        )}
                    </div>
                </div>
            </section>
        </>
    );
}

export default WareVendorDetail;