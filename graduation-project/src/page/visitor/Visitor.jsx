import React, { useEffect, useState } from "react";
import { useLazySearchGuaranteeByGuaranteeCodeQuery, useLazySearchHistoryProductByImeProductOrPhoneNumberQuery } from "../../app/apis/visitor/visitorApi";
import { Link } from "react-router-dom";

function Visitor() {
    const [imeP, setImeP] = useState("");
    const [phoneNumberP, setPhoneNumberP] = useState("");
    const [imeG, setImeG] = useState("");
    const [phoneNumberG, setPhoneNumberG] = useState("");
    const [status, setStatus] = useState("PRODUCT");


    const [getProduct, { data: prodcutData, isLoading: productLoading }] = useLazySearchHistoryProductByImeProductOrPhoneNumberQuery();
    const [getGuarantee, { data: guaranteeData, isLoading: guaranteeLoading }] = useLazySearchGuaranteeByGuaranteeCodeQuery();

    useEffect(() => {
        getProduct({ 
            ime: imeP, 
            phoneNumber: phoneNumberP 
        })
    }, [])

    useEffect(() => {
        getGuarantee({ 
            ime: imeG, 
            phoneNumber: phoneNumberG 
        })
    }, [])

    if (productLoading) {
        return <h2>Loading....</h2>
    }

    const handleClickSearchProduct = () => {
        getProduct({ 
            ime: imeP, 
            phoneNumber: phoneNumberP 
        });
    }

    const handleClickSearchGuarantee = () => {
        getGuarantee({ 
            ime: imeG, 
            phoneNumber: phoneNumberG 
        })
    }


    

    return (
        <>
            <div className="khach-wrapper-container">
                <nav className="d-flex justify-content-end align-items-center px-3">
                    <div className="navbar-nav align-items-center ms-auto">
                        <div className="nav-item">
                            <Link
                                to={"/login"}
                                className="hd-login"
                            >
                                <img
                                    className="rounded-circle me-lg-2 av-auth"
                                    src="https://banner2.cleanpng.com/20190525/rfb/kisspng-button-computer-icons-login-image-illustration-index-of-images-5ce952d5a81805.2893926115587949656885.jpg" alt=""
                                />
                                <span className="d-none d-lg-inline-flex">Login</span>
                            </Link>
                        </div>
                    </div>
                </nav>
            </div>

            <section className="container clearfix nav-bh">
                <div className="tra-cuu">
                    <label htmlFor="statusSelect" className="mb-2">
                        Chọn Loại Hình Tra Cứu:
                    </label>
                    <select
                        id="statusSelect"
                        className="form-control"
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        <option value="GUARANTEE">Bảo Hành</option>
                        <option value="PRODUCT">Sản Phẩm</option>
                    </select>
                </div>
                {status === "PRODUCT" && (
                    <div className="search-warranty">
                        <div className="search-warranty-content">
                            <h1 className="search-warranty-content__title">Tra cứu Sản Phẩm</h1>
                            <div className="tra-cuu-bh">
                                <div className="v2-error"></div>
                            </div>
                            <div className="search-warranty-content__main v2-search">
                                <form>
                                    <input type="hidden" name="_token" />
                                    <div className="v2-search-item">
                                        <input
                                            type="text"
                                            className="v2-search-item__phone"
                                            placeholder="Nhập số điện thoại..."
                                            onChange={(e) => setPhoneNumberP(e.target.value)}
                                        />
                                    </div>
                                    <div className="v2-search-item">
                                        <input
                                            type="text"
                                            className="v2-search-item__imei"
                                            placeholder="Nhập IMEI..."
                                            onChange={(e) => setImeP(e.target.value)}
                                        />
                                    </div>

                                    <div className="v2-search-button">
                                        <button
                                            type="button"
                                            className="v2-search-submit v2-button-default v2-color-primary"
                                            onClick={handleClickSearchProduct}
                                        >
                                            Tìm kiếm
                                        </button>
                                    </div>
                                </form>
                            </div>
                            {prodcutData && prodcutData.length > 0 ? (
                                <div className="v2-banner-search">
                                    <div className="row">
                                        <div className="col-12">
                                            <div className="card">
                                                <div className="card-body">
                                                    <table className="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>Họ Và Tên Khách Hàng</th>
                                                                <th>Số Điện Thoại</th>
                                                                <th>Email</th>
                                                                <th>Địa Chỉ</th>
                                                                <th>ID Sản Phẩm</th>
                                                                <th>Model</th>
                                                                <th>Hãng Sản Xuất</th>
                                                                <th>Số Ime</th>
                                                                <th>Giá Tiền</th>
                                                                <th>Tình Trạng</th>
                                                                <th>Số Bảo Hành</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            {prodcutData.map((product, index) => (
                                                                <tr key={index}>
                                                                    <td>{product.customer.fullName}</td>
                                                                    <td>{product.customer.phoneNumber}</td>
                                                                    <td>{product.customer.email}</td>
                                                                    <td>{product.customer.address}</td>
                                                                    <td>{product.id}</td>
                                                                    <td>{product.nameModel}</td>
                                                                    <td>{product.phoneCompany}</td>
                                                                    <td>{product.ime}</td>
                                                                    <td>{product.price}</td>
                                                                    <td className={product.status ? "completed" : "not-completed"}>
                                                                        {product.status ? "Đã Sửa Chữa Xong" : "Chưa Sửa Chữa Xong"}</td>
                                                                    <td>
                                                                        {product.guarantees.map((guarantee) => guarantee.guaranteeCode).join(", ")}
                                                                    </td>
                                                                </tr>
                                                            ))}
                                                        </tbody>
                                                    </table>
                                                    <div
                                                        className="d-flex justify-content-center mt-3"
                                                        id="pagination"
                                                    ></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ) : (
                                <div className="logo-tra-cuu-bh">
                                    <img src="https://mobilecity.vn/public/assets/img/tra-cuu-bao-hanh.jpg" alt="" />
                                </div>
                            )}
                        </div>
                    </div>
                )}
                {status === "GUARANTEE" && (
                    <div className="search-warranty">
                        <div className="search-warranty-content">
                            <h1 className="search-warranty-content__title">Tra cứu Bảo Hành</h1>
                            <div className="tra-cuu-bh">
                                <div className="v2-error"></div>
                            </div>
                            <div className="search-warranty-content__main v2-search">
                                <form>
                                    <input type="hidden" name="_token" />
                                    <div className="v2-search-item">
                                        <input
                                            type="text"
                                            name="imei"
                                            className="v2-search-item__imei"
                                            placeholder="Nhập số điện thoại..."
                                            onChange={(e) => setPhoneNumberG(e.target.value)}
                                        />
                                    </div>
                                    <div className="v2-search-item">
                                        <input
                                            type="text"
                                            name="imei"
                                            className="v2-search-item__imei"
                                            placeholder="Nhập số ime..."
                                            onChange={(e) => setImeG(e.target.value)}
                                        />
                                    </div>
                                    <div className="v2-search-button">
                                        <button
                                            type="button"
                                            className="v2-search-submit v2-button-default v2-color-primary"
                                            onClick={handleClickSearchGuarantee}
                                        >
                                            Tìm kiếm
                                        </button>
                                    </div>
                                </form>
                            </div>
                            {guaranteeData && guaranteeData.length > 0 ? (
                                <div className="v2-banner-search">
                                    <div className="row">
                                        <div className="col-12">
                                            <div className="card">
                                                <div className="card-body">
                                                    <table className="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>ID Bảo Hành</th>
                                                                <th>Mã Bảo Hành</th>
                                                                <th>Model</th>
                                                                <th>Hãng Sản Xuất</th>
                                                                <th>Số Ime</th>
                                                                <th>Tên Lỗi</th>
                                                                <th>Vị trí Sửa</th>
                                                                <th>Ngày Tạo</th>
                                                                <th>Ngày Hết Hạn</th>
                                                                <th>Trạng thái</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                            <tr >
                                                                <td>{guaranteeData?.id}</td>
                                                                <td>{guaranteeData?.guaranteeCode}</td>
                                                                <td>{guaranteeData?.product?.nameModel}</td>
                                                                <td>{guaranteeData?.product?.phoneCompany}</td>
                                                                <td>{guaranteeData?.product?.ime}</td>
                                                                <td>{guaranteeData?.product?.defectName}</td>
                                                                <td>{guaranteeData?.product?.location}</td>
                                                                <td>{guaranteeData?.activationDate ? new Date(guaranteeData.activationDate).toLocaleDateString() : ""}</td>
                                                                <td>{guaranteeData?.expirationDate ? new Date(guaranteeData?.expirationDate).toLocaleDateString() : ""}</td>
                                                                <th>{guaranteeData?.status ? "Còn Hạn Bảo Hành" : "Hết Hạn Bảo Hành"}</th>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                    <div
                                                        className="d-flex justify-content-center mt-3"
                                                        id="pagination"
                                                    ></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ) : (
                                <div className="logo-tra-cuu-bh">
                                    <img src="https://mobilecity.vn/public/assets/img/tra-cuu-bao-hanh.jpg" alt="" />
                                </div>
                            )}
                        </div>
                    </div>
                )}
            </section>
            <footer>
                <div className="container">
                    <div className="footer-top">
                        <div className="footer-top-item">
                            <div className="title">
                                <div className="all-icon icon-location"></div>
                                Hệ thống cửa hàng
                            </div>
                            <div className="footer-top-content">
                                <div className="address-group">
                                    <div className="address-group-title">Hà Nội</div>
                                    <div className="address-group-main">
                                        <div className="address-group-item">
                                            <address>
                                                120 Thái Hà, Q. Đống Đa
                                                <a href="#" target="_blank" rel="nofollow">
                                                    Xem bản đồ
                                                </a>
                                            </address>
                                            <p>
                                                Điện thoại: <a href="#">0969.120.120</a>-
                                                <a href="#">037.437.9999</a>
                                            </p>
                                        </div>
                                        <div className="address-group-item">
                                            <address>
                                                398 Cầu Giấy, Q. Cầu Giấy
                                                <a href="#" target="_blank" rel="nofollow">
                                                    Xem bản đồ
                                                </a>
                                            </address>
                                            <p>
                                                Điện thoại: <a href="#">096.1111.398</a>-
                                                <a href="#">037.437.9999</a>
                                            </p>
                                        </div>
                                        <div className="address-group-item">
                                            <address>
                                                42 Phố Vọng, Hai Bà Trưng
                                                <a href="#" target="_blank" rel="nofollow">
                                                    Xem bản đồ
                                                </a>
                                            </address>
                                            <p>
                                                Điện thoại: <a href="#">0979.884242</a>-
                                                <a href="#">037.437.9999</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="footer-top-item">
                            <div className="title">
                                <div className="all-icon icon-policy"></div>
                                quy định - chính sách
                            </div>
                            <div className="footer-top-content">
                                <ul className="footer-list">
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Chính sách bảo hành
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Chính sách vận chuyển
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Chính sách đổi trả hàng
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Chính sách bảo mật thông tin
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Hướng dẫn thanh toán
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Hướng dẫn mua hàng Online
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Dịch vụ Ship COD Toàn quốc
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank" rel="nofollow">
                                            Chính sách đại lý linh, phụ kiện
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div className="footer-top-item">
                            <div className="title">
                                <div className="all-icon icon-email"></div>
                                Đăng ký nhận bảng tin
                            </div>
                            <form>
                                <div className="footer-top-content">
                                    <div className="subscribe-form">
                                        <input
                                            type="text"
                                            id="subscribe-email"
                                            placeholder="Nhập email của bạn..."
                                        />
                                        <button
                                            type="submit"
                                            id="subscribe-btn"
                                            className="btn_nhan"
                                        >
                                            Gửi
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div className="title">
                                <div className="all-icon icon-connect"></div>
                                Liên kết
                            </div>
                            <div className="footer-top-content">
                                <div className="connect-content">
                                    <div className="connect-content-item">
                                        <a target="_blank" href="#" rel="nofollow">
                                            <img
                                                src="https://cdn.mobilecity.vn/mobilecity-vn/images/2023/04/facebook-logo-25.png"
                                                alt="hình ảnh mạng xã hội"
                                            />
                                            <span>Facebook</span>
                                        </a>
                                    </div>
                                    <div className="connect-content-item">
                                        <a target="_blank" href="#" rel="nofollow">
                                            <img
                                                src="https://cdn.mobilecity.vn/mobilecity-vn/images/2023/04/youtube-logo-25.png"
                                                alt="hình ảnh mạng xã hội"
                                            />
                                            <span>Youtube</span>
                                        </a>
                                    </div>
                                    <div className="connect-content-item">
                                        <a target="_blank" href="#" rel="nofollow">
                                            <img
                                                src="https://cdn.mobilecity.vn/mobilecity-vn/images/2023/04/zalo-logo-25.png"
                                                alt="hình ảnh mạng xã hội"
                                            />
                                            <span>OA Zalo</span>
                                        </a>
                                    </div>
                                    <div className="connect-content-item">
                                        <a target="_blank" href="#" rel="nofollow">
                                            <img
                                                src="https://cdn.mobilecity.vn/mobilecity-vn/images/2023/04/instagram-logo-25.png"
                                                alt="hình ảnh mạng xã hội"
                                            />
                                            <span>Instagram</span>
                                        </a>
                                    </div>
                                    <div className="connect-content-item">
                                        <a target="_blank" href="#" rel="nofollow">
                                            <img
                                                src="https://cdn.mobilecity.vn/mobilecity-vn/images/2023/04/tiktok-logo-25.png"
                                                alt="hình ảnh mạng xã hội"
                                            />
                                            <span>Tiktok</span>
                                        </a>
                                    </div>
                                    <div className="connect-content-item">
                                        <a target="_blank" href="#" rel="nofollow">
                                            <img
                                                src="https://cdn.mobilecity.vn/mobilecity-vn/images/2023/04/twitter-logo-25.png"
                                                alt="hình ảnh mạng xã hội"
                                            />
                                            <span>Twitter</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="footer-top-item">
                            <div className="footer-top-content">
                                <div className="icon-footer">
                                    <div className="all-icon icon-norton"></div>
                                    <div className="all-icon icon-notification"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </>
    );
}

export default Visitor;