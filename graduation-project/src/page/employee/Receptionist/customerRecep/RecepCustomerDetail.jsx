import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link, useParams } from "react-router-dom";
import { useLazyGetListProductByCustomerIdQuery } from "../../../../app/apis/receptionist/customerApi";
import { getStatusLabel } from "../../../formHTML/enum";

function RecepCustomerDetail () {
    const { customerId  } = useParams();
    const [count, setCount] = useState(0);

    const [getProduct, { data: productData, isLoading: productLoadinng }] = useLazyGetListProductByCustomerIdQuery();

    useEffect(() => {
        getProduct({
            id: customerId,
            page: 1,
            pageSize: 10
        })
    },[])

    if (productLoadinng) {
        return <h2>Loading...</h2>
    }

    const handlePageClick = (page) => {
        getProduct({
            id: customerId,
            page: page.selected + 1,
            pageSize: 10
        })
    }

    console.log(productData)
    return (
        <>
            <section className="content">
        <div className="container-fluid">
          <div className="row py-2">
            <div className="col-6">
              <Link to={"/receptionist/customers"} className="btn btn-default">
                <i className="fas fa-chevron-left"></i> Quay lại
              </Link>
            </div>
          </div>
          <div className="row">
            <div className="col-12">
              <div className="card">
                <div className="card-body">
                  <table className="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>STT</th>
                        <th>Model</th>
                        <th>Hãng Điện Thoại</th>
                        <th>Số IME</th>
                        <th>Tên Lỗi</th>
                        <th>Trạng Thái</th>
                      </tr>
                    </thead>
                    <tbody>
                      {productData &&
                        productData?.data.map((product, index) => (
                          <tr key={index}>
                            <td>{count + index + 1}</td>
                            <td>
                              <Link
                                to={`/receptionist/product/${product.productId}`}
                                className="text-decoration-none"
                              >
                                {product.nameModel}
                              </Link>
                            </td>
                            <td>
                              <Link
                                to={`/receptionist/product/${product.productId}`}
                                className="text-decoration-none"
                              >
                                {product.phoneCompany}
                              </Link>
                            </td>
                            <td>{product.ime}</td>
                            <td>{product.defectName}</td>
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
                      onPageChange={handlePageClick}
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
        </div>
      </section>
        </>
    );
}

export default RecepCustomerDetail;