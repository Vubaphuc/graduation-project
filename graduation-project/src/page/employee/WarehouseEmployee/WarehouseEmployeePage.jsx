import { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { useLazyGetListMaterialAllQuery } from "../../../app/apis/warehouseEmployee/warehouseEmployeeApi";


function WarehouseEmployeePage() {
    const [count, setCount] = useState(0);
    const [page, setPage] = useState(0);

    const [getMaterial, { data: materialData, isLoading: materialLoading  }] = useLazyGetListMaterialAllQuery();

    useEffect(() => {
        getMaterial({
            page: 1,
            pageSize: 10
        })
    },[])

    if (materialLoading) {
        return <h2>Loading...</h2>
    }

    console.log(materialData)

    const handlePageClick = (page) => {
        getMaterial({
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
                                    {materialData && materialData.data.length > 0 ? (
                                        <div className="card-body">
                                            <table className="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>STT</th>
                                                        <th>Mã Vật Liệu</th>
                                                        <th>Tên Model</th>
                                                        <th>Loại Linh Kiện</th>
                                                        <th>Số Lượng</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {materialData?.data.map((material, index) => (
                                                        <tr key={index}>
                                                            <td>{count + index + 1}</td>
                                                            <td>
                                                                <Link
                                                                    to={`/warehouse/material/${material.id}`}
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

export default WarehouseEmployeePage;