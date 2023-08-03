import React from "react";
import hookFetchQuery from "../page/hookForm/hook/hookAccount/hookFetchQuery";
import { Link } from "react-router-dom";
import { Button } from "antd";

function Sidebar() {
    const { auth } = hookFetchQuery();

    const roles = auth.roles.map((role) => role.name);

    const isShowMenu = (authRoles, requireRoles) => {
        return authRoles.some((role) => requireRoles.includes(role));
    }
    return (
        <>
            <div className="sidebar">
                <nav className="navbar navbar-dark">
                    <div className="logo d-flex justify-content-center align-items-center">
                        {isShowMenu(roles, ["ADMIN"]) && (
                            <Link to={"/admin"} className="fs-4 text-white text-decoration-none" >ADMIN</Link>
                        )}
                        {isShowMenu(roles, ["NHANVIENLETAN"]) && (
                            <Link to={"/receptionist"} className="fs-4 text-white text-decoration-none">Nhân Viên Lễ Tân</Link>
                        )}
                        {isShowMenu(roles, ["NHANVIENSUACHUA"]) && (
                            <Link to={"/engineer"} className="fs-4 text-white text-decoration-none">Nhân viên sửa chữa</Link>
                        )}
                        {isShowMenu(roles, ["NHANVIENKHO"]) && (
                            <Link to={"/warehouse"} className="fs-4 text-white text-decoration-none">Nhân viên Kho</Link>
                        )}
                        {isShowMenu(roles, ["NHANVIENBAOHANH"]) && (
                            <Link to={"/warranty"} className="fs-4 text-white text-decoration-none">Nhân viên Bảo Hành</Link>
                        )}
                    </div>
                    {isShowMenu(roles, ["NHANVIENLETAN"]) && (
                        <div className="navbar-nav w-100">
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản lý Khách hàng</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/receptionist/customers"} className="dropdown-item">Danh sách khách hàng</Link>
                                    <Link to={"/receptionist/customer/create"} className="dropdown-item">Đăng ký Khách hàng mới</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản lý Sản Phẩm</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/receptionist/products"} className="dropdown-item">Danh Sách Product Repaired</Link>
                                    <Link to={"/receptionist/product-pending"} className="dropdown-item">Danh Sách Product Pending</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Transfer Delivery Product</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/receptionist/transfer-product"} className="dropdown-item">Danh Sách Waiting For Repair</Link>
                                    <Link to={"/receptionist/under-repair"} className="dropdown-item">Danh sách Under Repair</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản Lý Biên Lai</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/receptionist/receipts"} className="dropdown-item">Danh Sách Biên Lai</Link>
                                    <Link to={"/receptionist/no-receipts"} className="dropdown-item">List Product No Create Receipt</Link>
                                </div>
                            </div>
                            <Link to={"/employee/search"} className="nav-item nav-link active ms-3"><i className="fa-solid fa-magnifying-glass me-2"></i>Search</Link>
                            <div className="nav-item dropdown">
                                <a
                                    href="#"
                                    className="nav-link dropdown-toggle"
                                    data-bs-toggle="dropdown"
                                >
                                    <i className="far fa-file-alt me-2"></i>
                                    Tài khoản
                                </a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/employee/personal-information"} className="dropdown-item">Thông tin tài khoản</Link>
                                    <Link to={"/employee/change-password"} className="dropdown-item">Đổi Mật khẩu</Link>
                                </div>
                            </div>
                            <Link to={"/chat"} className="nav-item nav-link text-primary ms-3" ><i className="fa fa-chart-bar me-2"></i>CHAT</Link>
                        </div>
                    )}
                    {isShowMenu(roles, ["NHANVIENSUACHUA"]) && (
                        <div className="navbar-nav w-100">
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Sản Phẩm</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/engineer"} className="dropdown-item">Danh sách Sản Phẩm</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-laptop me-2"></i>Quản lý Oder</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/engineer/orders"} className="dropdown-item">Danh sách Oder</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-keyboard me-2"></i>Vật liệu</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/engineer/materials"} className="dropdown-item">Danh sách Vật Liệu</Link>
                                </div>
                            </div>
                            <Link to={"/employee/search"} className="nav-item nav-link active ms-3"><i className="fa-solid fa-magnifying-glass me-2"></i>Search</Link>
                            <div className="nav-item dropdown">
                                <a
                                    href="#"
                                    className="nav-link dropdown-toggle"
                                    data-bs-toggle="dropdown"
                                >
                                    <i className="far fa-file-alt me-2"></i>
                                    Thông tin tài khoản
                                </a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/employee/personal-information"} className="dropdown-item">Thông tin tài khoản</Link>
                                    <Link to={"/employee/change-password"} className="dropdown-item">Đổi Mật khẩu</Link>
                                </div>
                            </div>
                            <Link to={"/chat"} className="nav-item nav-link text-primary ms-3" ><i className="fa fa-chart-bar me-2"></i>CHAT</Link>
                        </div>
                    )}
                    {isShowMenu(roles, ["NHANVIENKHO"]) && (
                        <div className="navbar-nav w-100">
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản lý Vật liệu</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warehouse"} className="dropdown-item">Danh sách Vật liệu</Link>
                                    <Link to={"/warehouse/material/create"} className="dropdown-item">Đăng ký vật liệu</Link>
                                    <Link to={"/warehouse/search/material"} className="dropdown-item">Tìm kiếm vật liệu</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-laptop me-2"></i>Linh Kiện</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warehouse/components"} className="dropdown-item">Danh Sách Linh Kiện</Link>
                                    <Link to={"/warehouse/component/create"} className="dropdown-item">Đăng ký Linh Kiện Mới</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-keyboard me-2"></i>Quản lý Oder</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warehouse/orderMaterials"} className="dropdown-item">Danh sách oder vật liệu</Link>
                                    <Link to={"/warehouse/search/order"} className="dropdown-item">Tìm kiếm oder</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-keyboard me-2"></i>Quản lý Vender</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warehouse/vendors"} className="dropdown-item">Danh sách Vender</Link>
                                    <Link to={"/warehouse/vendor/create"} className="dropdown-item">Thêm Vender Mới</Link>
                                </div>
                            </div>
                            <Link to={"/employee/search"} className="nav-item nav-link active ms-3"><i className="fa-solid fa-magnifying-glass me-2"></i>Search</Link>
                            <div className="nav-item dropdown">
                                <a
                                    href="#"
                                    className="nav-link dropdown-toggle"
                                    data-bs-toggle="dropdown"
                                >
                                    <i className="far fa-file-alt me-2"></i>
                                    Tài khoản
                                </a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/employee/personal-information"} className="dropdown-item">Thông tin tài khoản</Link>
                                    <Link to={"/employee/change-password"} className="dropdown-item">Đổi Mật khẩu</Link>
                                </div>
                            </div>
                            <Link to={"/chat"} className="nav-item nav-link text-primary ms-3" ><i className="fa fa-chart-bar me-2"></i>CHAT</Link>
                        </div>
                    )}
                    {isShowMenu(roles, ["NHANVIENBAOHANH"]) && (
                        <div className="navbar-nav w-100">
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản lý Khách hàng</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warranty/customers"} className="dropdown-item">Danh sách khách hàng</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản lý Sản Phẩm</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warranty/product-delivered"} className="dropdown-item">Danh sách Product Delivered</Link>
                                    <Link to={"/warranty/product-guarantee-repaired"} className="dropdown-item">Danh sách Product Repaired</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Transfer Delivery Product</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warranty/product-guarantee"} className="dropdown-item">Danh Sách Waiting For Repair</Link>
                                    <Link to={"/warranty/under-repair"} className="dropdown-item">Danh sách Under Repair</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản Lý Biên Lai</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warranty/receipts"} className="dropdown-item">Danh Sách Biên Lai</Link>
                                    <Link to={"/warranty/no-receipts"} className="dropdown-item">List Product No Create Receipt</Link>
                                </div>
                            </div>
                            <Link to={"/employee/search"} className="nav-item nav-link active ms-3"><i className="fa-solid fa-magnifying-glass me-2"></i>Search</Link>
                            <div className="nav-item dropdown">
                                <a
                                    href="#"
                                    className="nav-link dropdown-toggle"
                                    data-bs-toggle="dropdown"
                                >
                                    <i className="far fa-file-alt me-2"></i>
                                    Tài khoản
                                </a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/employee/personal-information"} className="dropdown-item">Thông tin tài khoản</Link>
                                    <Link to={"/employee/change-password"} className="dropdown-item">Đổi Mật khẩu</Link>
                                </div>
                            </div>
                            <Link to={"/chat"} className="nav-item nav-link text-primary ms-3" ><i className="fa fa-chart-bar me-2"></i>CHAT</Link>
                        </div>
                    )}
                    {isShowMenu(roles, ["ADMIN"]) && (
                        <div className="navbar-nav w-100">
                            <Link to={"/admin/dashboard"} className="nav-item nav-link active"><i className="fa fa-tachometer-alt me-2"></i>Dashboard</Link>
                            <Link to={"/admin/product-manage"} className="nav-item nav-link product" ><i className="fa fa-chart-bar me-2"></i>Product</Link>
                            <Link to={"/admin/product-guarantee-manage"} className="nav-item nav-link color-while ms-3" ><i className="fa fa-chart-bar me-2"></i>Product Guarantee</Link>
                            <Link to={"/admin/material-manage"} className="nav-item nav-link material" ><i className="fa fa-chart-bar me-2"></i>Material</Link>
                            <Link to={"/admin/materials"} className="nav-item nav-link text-primary ms-3" ><i className="fa fa-chart-bar me-2"></i>Material Detail</Link>

                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-keyboard me-2"></i>Nhân Viên</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/admin/employees"} className="dropdown-item">Danh sách nhân viên</Link>
                                    <Link to={"/admin/employee/create"} className="dropdown-item">Đăng ký Nhân Viên mới</Link>
                                </div>
                            </div>
                            <div className="nav-item dropdown">
                                <a
                                    href="#"
                                    className="nav-link dropdown-toggle"
                                    data-bs-toggle="dropdown"
                                >
                                    <i className="far fa-file-alt me-2"></i>
                                    Tài khoản
                                </a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/employee/personal-information"} className="dropdown-item">Thông tin tài khoản</Link>
                                    <Link to={"/employee/change-password"} className="dropdown-item">Đổi Mật khẩu</Link>
                                </div>
                            </div>
                            <Link to={"/chat"} className="nav-item nav-link text-primary ms-3" ><i className="fa fa-chart-bar me-2"></i>CHAT</Link>
                        </div>
                    )}
                </nav>
            </div>
        </>
    )
}

export default Sidebar;