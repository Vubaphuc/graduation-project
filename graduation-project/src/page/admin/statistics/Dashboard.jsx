import React, { useEffect } from "react";
import { Bar, Doughnut, Pie } from "react-chartjs-2";
import ChartDataLabels from 'chartjs-plugin-datalabels';
import chartBar from "../../chartjs/chartBar";
import chartPie from "../../chartjs/chartPie";
import chartDoughnut from "../../chartjs/chartDoughnut";
import { useFindMaterialRemainingQuantityLimitQuery, useLazyFindOrderMaterialLimitQuery } from "../../../app/apis/admin/materialManageApi";
import { useFindCustomerByProductLimitQuery, useFindProductByNameModelLimitQuery, useFindTotalProductByEngineerQuery, useFindTotalProductOkAndPendingQuery } from "../../../app/apis/admin/productManageApi";

function Dashboard() {


    const [getOrderMaterialLimit, { data: orderMaterialLimitData, isLoading: loading }] = useLazyFindOrderMaterialLimitQuery();
    const { data: NameProductData } = useFindProductByNameModelLimitQuery();
    const { data: materialRemainingData } = useFindMaterialRemainingQuantityLimitQuery();
    const { data: customerData } = useFindCustomerByProductLimitQuery();
    const { data: totalProductData } = useFindTotalProductOkAndPendingQuery();
    const { data: totalProdcutEngi } = useFindTotalProductByEngineerQuery();

    useEffect(() => {
        getOrderMaterialLimit();
    }, [])

    if (loading) {
        return <h2>Loading...</h2>
    }


    const labelsD = orderMaterialLimitData?.map((order) => order.materialCode);
    const dataD = orderMaterialLimitData?.map((order) => order.totalQuantityExport);
    const labelProductName = NameProductData?.map((product) => product.nameModel)
    const dataProductName = NameProductData?.map((product) => product.sumProduct)
    const labelCustomer = customerData?.map((product) => product.fullName)
    const dataCustomer = customerData?.map((product) => product.sumProduct)
    const labelMaterialRemaining = materialRemainingData?.map((material) => material.code)
    const dataMaterialRemaining = materialRemainingData?.map((material) => material.remainingQuantity)


    const ok = totalProductData?.deliveredCount;
    const pending = totalProductData?.otherCount;

    const dataTotalProduct = [ok,pending];





    const { data: doughnutData } = chartDoughnut(labelsD, dataD);
    const { data: doughnutProductData } = chartDoughnut(labelProductName, dataProductName);
    const { data: doughnutCustomerData } = chartDoughnut(labelCustomer, dataCustomer);
    const { data: doughnutMaterialRemainingData } = chartDoughnut(labelMaterialRemaining, dataMaterialRemaining);
    const { data: pieTotalProductData, options: pieTotalProductOption } = chartPie(dataTotalProduct)

    const exportProductAllToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-total-material";
        window.location.href = url;
    }

    const exportProductByNameModelAllToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-product-name";
        window.location.href = url;
    }

    const exportMaterialRemainingQuantityLimitToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-material-remaining-quantity-limit";
        window.location.href = url;
    }

    const exportCustomerByProductLimitToExcel = () => {
        const url = "http://localhost:8080/excel/api/v1/export-customer";
        window.location.href = url;
    }

    return (
        <>
            <div className="dt product-dt">
                <div className="dt-tp">
                    <div className="text-center p-4">
                        <div className="d-flex align-items-center justify-content-between mb-4">
                            <h6 className="mb-0">Tỷ lệ khách hàng </h6>
                        </div>
                        <Doughnut data={doughnutCustomerData} plugins={[ChartDataLabels]} />;
                    </div>
                </div>
                <div className="dt-tp">
                    <div className="text-center p-4">
                        <div className="d-flex align-items-center justify-content-between mb-4">
                            <h6 className="mb-0">Top khách hàng có sản phẩm nhiều nhất</h6>
                            <div>
                                <button className="btn btn-warning px-4" variant="warning" onClick={exportCustomerByProductLimitToExcel}>Export</button>
                            </div>
                        </div>
                        {customerData && customerData.length > 0 ? (
                            <div className="table-responsive">
                                <table className="table text-start align-middle table-bordered table-hover mb-0 mt-5">
                                    <thead>
                                        <tr className="text-white">
                                            <th scope="col">ID</th>
                                            <th scope="col">Customer Name</th>
                                            <th scope="col">Customer Phone</th>
                                            <th scope="col">Customer Email</th>
                                            <th scope="col">Quantity</th>
                                        </tr>
                                    </thead>
                                    <tbody >
                                        {customerData.map((customer, index) => (
                                            <tr key={index}>
                                                <td>{customer.id}</td>
                                                <td>{customer.fullName}</td>
                                                <td>{customer.phone}</td>
                                                <td>{customer.email}</td>
                                                <td>{customer.sumProduct}</td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>

                            </div>
                        ) : (
                            <p>Không có sản phẩm nào !!!</p>
                        )}
                    </div>
                </div>
            </div>
            <div>

                {/* product */}
                <div className="dt product-dt">
                    <div className="dt-tp">
                        <div className="text-center p-4">
                            <div className="d-flex align-items-center justify-content-between mb-4">
                                <h6 className="mb-0">Tỷ lệ từng model sản phẩm</h6>
                            </div>
                            <Doughnut data={doughnutProductData} plugins={[ChartDataLabels]} />;
                        </div>
                    </div>
                    <div className="dt-tp">
                        <div className="text-center p-4">
                            <div className="d-flex align-items-center justify-content-between mb-4">
                                <h6 className="mb-0">Top 10 sản phẩm sửa chữa nhiều nhất</h6>
                                <div>
                                    <button className="btn btn-warning px-4" variant="warning" onClick={exportProductByNameModelAllToExcel}>Export</button>
                                </div>
                            </div>
                            {NameProductData && NameProductData.length > 0 ? (
                                <div className="table-responsive">
                                    <table className="table text-start align-middle table-bordered table-hover mb-0 mt-5">
                                        <thead>
                                            <tr className="text-white">
                                                <th scope="col">Model Name</th>
                                                <th scope="col">Quantity</th>
                                            </tr>
                                        </thead>
                                        <tbody >
                                            {NameProductData.map((product, index) => (
                                                <tr key={index}>
                                                    <td>{product.nameModel}</td>
                                                    <td>{product.sumProduct}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>

                                </div>
                            ) : (
                                <p>Không có sản phẩm nào !!!</p>
                            )}
                        </div>
                    </div>
                </div>
            </div>
            {/* product */}
            <div>
                <div className="dt product-dt">
                    <div className="dt-tp">
                        <div className="text-center p-4">
                            <div className="d-flex align-items-center justify-content-between mb-4">
                                <h6 className="mb-0">Tỷ lệ từng model sản phẩm</h6>
                            </div>
                            <Pie data={pieTotalProductData} options={pieTotalProductOption} plugins={[ChartDataLabels]} />;
                        </div>
                    </div>
                    <div className="dt-tp">
                        <div className="text-center p-4">
                            <div className="d-flex align-items-center justify-content-between mb-4">
                                <h6 className="mb-0">Top nhân viên sửa chữa nhiều nhất</h6>
                                <div>
                                    <button className="btn btn-warning px-4" variant="warning" onClick={exportProductByNameModelAllToExcel}>Export</button>
                                </div>
                            </div>
                            {totalProdcutEngi && totalProdcutEngi.length > 0 ? (
                                <div className="table-responsive">
                                    <table className="table text-start align-middle table-bordered table-hover mb-0 mt-5">
                                        <thead>
                                            <tr className="text-white">
                                                <th scope="col">Employee Code</th>
                                                <th scope="col">Employee Name</th>
                                                <th scope="col">Quantity</th>
                                            </tr>
                                        </thead>
                                        <tbody >
                                            {totalProdcutEngi.map((product, index) => (
                                                <tr key={index}>
                                                    <td>{product.employeeCode}</td>
                                                    <td>{product.employeeName}</td>
                                                    <td>{product.totalProduct}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>

                                </div>
                            ) : (
                                <p>Không có sản phẩm nào !!!</p>
                            )}
                        </div>
                    </div>
                </div>
            </div>
            {/* material */}
            <div>
                <div className="dt product-dt">
                    <div className="dt-tp">
                        <div className="text-center p-4">
                            <div className="d-flex align-items-center justify-content-between mb-4">
                                <h6 className="mb-0">Tỷ lệ số lượng vật liệu order nhiều nhất</h6>
                            </div>
                            <Doughnut data={doughnutData} plugins={[ChartDataLabels]} />;
                        </div>
                    </div>
                    <div className="dt-tp">
                        <div className="pt-4 px-4">
                            <div className="text-center rounded p-4 table-table">
                                <div className="d-flex align-items-center justify-content-between mb-4">
                                    <h6 className="mb-0">Top 10 Vật liệu order nhiều nhất</h6>
                                    <div>
                                        <button className="btn btn-warning px-4" variant="warning" onClick={exportProductAllToExcel}>Export</button>
                                    </div>
                                </div>
                                {orderMaterialLimitData && orderMaterialLimitData.length > 0 ? (
                                    <div className="table-responsive">
                                        <table className="table text-start align-middle table-bordered table-hover mb-0 mt-5">
                                            <thead>
                                                <tr className="text-white">
                                                    <th scope="col">Material Code</th>
                                                    <th scope="col">Component Name</th>
                                                    <th scope="col">Export Quantity</th>
                                                </tr>
                                            </thead>
                                            <tbody >
                                                {orderMaterialLimitData.map((order, index) => (
                                                    <tr key={index}>
                                                        <td>{order.materialCode}</td>
                                                        <td>{order.componentName}</td>
                                                        <td>{order.totalQuantityExport}</td>

                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>

                                    </div>
                                ) : (
                                    <p>Không có sản phẩm nào !!!</p>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div className="dt product-dt">
                    <div className="dt-tp">
                        <div className="text-center p-4">
                            <div className="d-flex align-items-center justify-content-between mb-4">
                                <h6 className="mb-0">Số lượng Vật liệu còn hàng nhiều nhất</h6>
                            </div>
                            <Doughnut data={doughnutMaterialRemainingData} plugins={[ChartDataLabels]} />;
                        </div>
                    </div>
                    <div className="dt-tp">
                        <div className="pt-4 px-4">
                            <div className="text-center rounded p-4 table-table">
                                <div className="d-flex align-items-center justify-content-between mb-4">
                                    <h6 className="mb-0">Top Vật liệu còn hàng</h6>
                                    <div>
                                        <button className="btn btn-warning px-4" variant="warning" onClick={exportMaterialRemainingQuantityLimitToExcel}>Export</button>
                                    </div>
                                </div>
                                {materialRemainingData && materialRemainingData.length > 0 ? (
                                    <div className="table-responsive">
                                        <table className="table text-start align-middle table-bordered table-hover mb-0 mt-5">
                                            <thead>
                                                <tr className="text-white">
                                                    <th scope="col">Mã Vật Liệu</th>
                                                    <th scope="col">Số Lượng Import</th>
                                                    <th scope="col">Số Lượng Export</th>
                                                    <th scope="col">Số Lượng Còn Lại</th>
                                                    <th scope="col">Tên Model</th>
                                                    <th scope="col">Mã Nhân Viên Nhập</th>
                                                    <th scope="col">Tên Nhân Viên Nhập</th>
                                                </tr>
                                            </thead>
                                            <tbody >
                                                {materialRemainingData.map((material, index) => (
                                                    <tr key={index}>
                                                        <td>{material.code}</td>
                                                        <td>{material.importQuantity}</td>
                                                        <td>{material.exportQuantity}</td>
                                                        <td>{material.remainingQuantity}</td>
                                                        <td>{material.nameModel}</td>
                                                        <td>{material.warehouseEmployee.employeeCode}</td>
                                                        <td>{material.warehouseEmployee.employeeName}</td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>

                                    </div>
                                ) : (
                                    <p>Không có sản phẩm nào !!!</p>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Dashboard;