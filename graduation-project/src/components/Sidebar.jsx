import React from "react";
import hookFetchQuery from "../page/hookForm/hook/hookAccount/hookFetchQuery";
import { Link } from "react-router-dom";

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
                            <h4 className="fs-4 text-white" >ADMIN</h4>
                        )}
                        {isShowMenu(roles, ["NHANVIENLETAN"]) && (
                            <h4 className="fs-4 text-white">Nhân Viên Lễ Tân</h4>
                        )}
                        {isShowMenu(roles, ["NHANVIENSUACHUA"]) && (
                            <h4 className="fs-4 text-white">Nhân viên sửa chữa</h4>
                        )}
                        {isShowMenu(roles, ["NHANVIENKHO"]) && (
                            <h4 className="fs-4 text-white">Nhân viên Kho</h4>
                        )}
                        {isShowMenu(roles, ["NHANVIENBAOHANH"]) && (
                            <h4 className="fs-4 text-white">Nhân viên Bảo Hành</h4>
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
                                <Link to={"/receptionist/search"} className="dropdown-item search"><i className="fa fa-tachometer-alt me-2"></i>Tìm Kiếm</Link>
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
                        </div>
                    )}
                    {isShowMenu(roles, ["NHANVIENBAOHANH"]) && (
                        <div className="navbar-nav w-100">
                            <div className="nav-item dropdown">
                                <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-th me-2"></i>Quản lý Sản Phẩm</a>
                                <div className="dropdown-menu bg-transparent border-0">
                                    <Link to={"/warranty/product-delivered"} className="dropdown-item">Danh sách Product Delivered</Link>
                                    <Link to={"/warranty/product-guarantee"} className="dropdown-item">Product Waiting For Repair</Link>
                                    <Link to={"/warranty/product-guarantee-repaired"} className="dropdown-item">Danh sách Product Repaired</Link>
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
                        </div>
                    )}
                </nav>
            </div>
        </>
    )
}

export default Sidebar;