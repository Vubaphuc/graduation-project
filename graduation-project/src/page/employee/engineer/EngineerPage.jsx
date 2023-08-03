import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { useLazyFindProductGuaranteeByUserAllQuery, useLazyGetListProductNewByUserQuery } from "../../../app/apis/engineer/engineerProductApi";
import { getRepairLabel, getStatusLabel } from "../../formHTML/enum";
import hookRecepProductCreate from "../../hookForm/hook/hookReceptionist/hookRecepProductCreate";

function EngineerPage() {
    const [term, setTerm] = useState("");
    const [status, setStatus] = useState("NEW");

    const { check, setCheck } = hookRecepProductCreate();

    const [getProduct, { data: productData, isLoading: productLoading }] =
        useLazyGetListProductNewByUserQuery();

    const [getProductGuarantee, { data: produtGuaranteeData, isLoading: productGuaranteeLoading }] =
        useLazyFindProductGuaranteeByUserAllQuery();

    useEffect(() => {
        getProduct({
            page: 1,
            pageSize: 10,
            term: term
        });
        const interval = setInterval(() => {
            getProduct({
                page: 1,
                pageSize: 10,
                term: term
            });
        }, 5000); // Gửi yêu cầu cập nhật mỗi 5 giây
        return () => {
            clearInterval(interval);
        };
    }, [term]);

    console.log(check)

    useEffect(() => {
        getProductGuarantee({
            page: 1,
            pageSize: 10,
            term: term
        })
    }, [term])

    if (productLoading || productGuaranteeLoading) {
        return <h2>Loading....</h2>;
    }


    const handlePageClickProduct = (page) => {
        getProduct({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    };

    const handlePageClickProductGuarantee = (page) => {
        getProductGuarantee({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    };


    return (
        <>
            <section className="content">
                <div className="tra-cuu ms-3 mt-3">
                    <label htmlFor="statusSelect" className="mb-2">
                        Chọn Loại Hình Sản Phẩm:
                    </label>
                    <select
                        id="statusSelect"
                        className="form-control"
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        <option value="NEW">Mới</option>
                        <option value="BAOHANH">Bảo Hành</option>
                    </select>
                </div>
                <div className="container-fluid">
                    <div className="search-container">
                        <input
                            className="input-search mb-4"
                            type="text"
                            placeholder="Tìm kiếm kiếm sản phẩm..."
                            value={term}
                            onChange={(e) => setTerm(e.target.value)}
                        />
                    </div>
                    {status === "NEW" && (
                        <div className="search-results mt-3">
                            {productData && productData.data.length > 0 ? (
                                <div className="row">
                                    <div className="col-12">
                                        <div className="card">
                                            <div className="card-body">
                                                <table className="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Model</th>
                                                            <th>Hãng Điện Thoại</th>
                                                            <th>Số IME</th>
                                                            <th>Tên Lỗi</th>
                                                            <th>Trạng Thái</th>
                                                            <th>Loại Sản Phẩm</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        {productData.data.map((product) => (
                                                            <tr key={product.id}>
                                                                <td>
                                                                    <Link
                                                                        to={`/engineer/product/${product.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {product?.nameModel}
                                                                    </Link>
                                                                </td>
                                                                <td>
                                                                    <Link
                                                                        to={`/engineer/product/${product.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {product?.phoneCompany}
                                                                    </Link>
                                                                </td>
                                                                <td>{product?.ime}</td>
                                                                <td>{product?.defectName}</td>
                                                                <td>
                                                                    {getStatusLabel(product.status)}
                                                                </td>
                                                                <td>{getRepairLabel(product.repair)}</td>
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
                                                        onPageChange={handlePageClickProduct}
                                                        pageRangeDisplayed={3}
                                                        marginPagesDisplayed={2}
                                                        pageCount={productData?.totalPages}
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
                                <p>Không có sản phẩm nào !!!</p>
                            )}
                        </div>
                    )}
                    {status === "BAOHANH" && (
                        <div className="search-results mt-3">
                            {produtGuaranteeData && produtGuaranteeData.data.length > 0 ? (
                                <div className="row">
                                    <div className="col-12">
                                        <div className="card">
                                            <div className="card-body">
                                                <table className="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Model</th>
                                                            <th>Hãng Điện Thoại</th>
                                                            <th>Số IME</th>
                                                            <th>Tên Lỗi</th>
                                                            <th>Trạng Thái</th>
                                                            <th>Loại Sản Phẩm</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        {produtGuaranteeData.data.map((product) => (
                                                            <tr key={product.id}>
                                                                <td>
                                                                    <Link
                                                                        to={`/engineer/product-guarantee/${product.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {product?.nameModel}
                                                                    </Link>
                                                                </td>
                                                                <td>
                                                                    <Link
                                                                        to={`/engineer/product-guarantee/${product.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {product?.phoneCompany}
                                                                    </Link>
                                                                </td>
                                                                <td>{product?.ime}</td>
                                                                <td>{product?.defectName}</td>
                                                                <td>
                                                                    {getStatusLabel(product.status)}
                                                                </td>
                                                                <td>{getRepairLabel(product.repair)}</td>
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
                                                        onPageChange={handlePageClickProductGuarantee}
                                                        pageRangeDisplayed={3}
                                                        marginPagesDisplayed={2}
                                                        pageCount={produtGuaranteeData?.totalPages}
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
                                <p>Không có sản phẩm nào !!!</p>
                            )}
                        </div>
                    )}
                </div>
            </section>
        </>
    );
}

export default EngineerPage;