import { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { useLazyFindEmployeesAllQuery } from "../../../app/apis/admin/employeeManageApi";
import { getRolesLabel } from "../../formHTML/enum";

function EmployeeManageList() {
    const [term, setTerm] = useState("");

    const [getEmployee, { data: employeeData, isLoading: employeeLoading }] = useLazyFindEmployeesAllQuery();

    useEffect(() => {
        getEmployee({
            page: 1,
            pageSize: 10,
            term: term
        })
    }, [term])

    if (employeeLoading) {
        return <h2>Loading...</h2>
    }

    const handlePageClick = (page) => {
        getEmployee({
            page: page.selected + 1,
            pageSize: 10,
            term: term
        })
    }
    return (
        <>
            <section className="content">
                <div className="container-fluid">
                    <div className="search-container">
                        <input
                            className="input-search mb-4"
                            type="text"
                            placeholder="Tìm kiếm khách hàng..."
                            value={term}
                            onChange={(e) => setTerm(e.target.value)}
                        />
                    </div>
                    <div className="search-results mt-3">
                        {employeeData && employeeData.data.length > 0 ? (
                            <div className="row">
                                <div className="col-12">
                                    <div className="card">
                                        <div className="card-body">
                                            <table className="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>ID Nhân Viên</th>
                                                        <th>Mã Nhân Viên</th>
                                                        <th>Họ và Tên</th>
                                                        <th>Số Điện Thoại</th>
                                                        <th>Email</th>
                                                        <th>Địa Chỉ</th>
                                                        <th>Roles</th>
                                                        <th>Tình Trạng</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {
                                                        employeeData.data.map((employee) => (
                                                            <tr key={employee.id}>
                                                                <td>
                                                                    <Link
                                                                        to={`/admin/employee/${employee.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {employee?.id}
                                                                    </Link>
                                                                </td>
                                                                <td>
                                                                    <Link
                                                                        to={`/admin/employee/${employee.id}`}
                                                                        className="text-decoration-none"
                                                                    >
                                                                        {employee?.employeeCode}
                                                                    </Link>
                                                                </td>
                                                                <td>{employee.employeeName}</td>
                                                                <td>{employee?.phoneNumber}</td>
                                                                <td>{employee?.email}</td>
                                                                <td>{employee.address}</td>
                                                                <td> {employee.roles.map((role) => getRolesLabel(role.name)).join(", ")}</td>
                                                                <td>{employee.enabled ? "Đang Hoạt Động" : "Đã Khóa"}</td>
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
                                                    pageCount={employeeData?.totalPages}
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
                            <p>Không có nhân viên nào !!!</p>
                        )}
                    </div>
                </div>
            </section>
        </>
    )
}

export default EmployeeManageList;