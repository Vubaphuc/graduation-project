import { Route, Routes } from 'react-router-dom';
import './App.css'
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import NotFound from './page/notFound/NotFound';
import LoginPage from './page/login/LoginPage';
import Visitor from './page/visitor/Visitor';
import ForgotPassword from './page/login/ForgotPassword';
import Layout from './components/Layout';
import AuthorizeRoutes from './components/AuthorizeRoutes';
import WarehouseEmployeePage from './page/employee/WarehouseEmployee/WarehouseEmployeePage';
import WareComponentCreate from './page/employee/WarehouseEmployee/components/WareComponentCreate';
import WareComponentList from './page/employee/WarehouseEmployee/components/WareComponentList';
import WareComponentDetail from './page/employee/WarehouseEmployee/components/WareComponentDetail';
import WareVendorCreate from './page/employee/WarehouseEmployee/vendor/WareVendorCreate';
import WareVendorDetail from './page/employee/WarehouseEmployee/vendor/WareVendorDetail';
import WareVendorList from './page/employee/WarehouseEmployee/vendor/WareVendorList';
import WareMaterialCreate from './page/employee/WarehouseEmployee/material/WareMaterialCreate';
import WareMaterialDetail from './page/employee/WarehouseEmployee/material/WareMaterialDetail';
import WareUpdateMaterial from './page/employee/WarehouseEmployee/material/WareUpdateMaterial';
import WareOrderMaterialList from './page/employee/WarehouseEmployee/orderMaterial/WareOrderMaterialList';
import WareOerderMaterialDetail from './page/employee/WarehouseEmployee/orderMaterial/WareOerderMaterialDetail';
import WareSearchOrder from './page/employee/WarehouseEmployee/search/WareSearchOrder';
import WareSearchMaterial from './page/employee/WarehouseEmployee/search/WareSearchMaterial';
import ChangePassword from './page/employee/ChangePassword';
import PersonalInformation from './page/employee/PersonalInformation';
import RecepCustomerCreate from './page/employee/Receptionist/customerRecep/RecepCustomerCreate';
import RecepCustomerDetail from './page/employee/Receptionist/customerRecep/RecepCustomerDetail';
import RecepCustomerList from './page/employee/Receptionist/customerRecep/RecepCustomerList';
import RecepCustomerUpdate from './page/employee/Receptionist/customerRecep/RecepCustomerUpdate';
import RecepProductList from './page/employee/Receptionist/productRecep/RecepProductList';
import ReceptionistPage from './page/employee/Receptionist/ReceptionistPage';
import RecepTransferProductList from './page/employee/Receptionist/transferProduct/RecepTransferProductList';
import RecepTransferProductDetail from './page/employee/Receptionist/transferProduct/RecepTransferProductDetail';
import RecepProductUnderRepairList from './page/employee/Receptionist/transferProduct/RecepProductUnderRepairList';
import RecepChangeEngineerProduct from './page/employee/Receptionist/transferProduct/RecepChangeEngineerProduct';
import RecepProductCreate from './page/employee/Receptionist/productRecep/RecepProductCreate';
import RecepProductPendingList from './page/employee/Receptionist/productRecep/RecepProductPendingList';
import RecepProductPendingDetail from './page/employee/Receptionist/productRecep/RecepProductPendingDetail';
import RecepGuaranteeCreate from './page/employee/Receptionist/billAndGuarantee/RecepGuaranteeCreate';
import RecepBillCreate from './page/employee/Receptionist/billAndGuarantee/RecepBillCreate';
import WarranttyEmployeePage from './page/employee/WarrantyEmployee/WarranttyEmployeePage';
import WarrantyProductDeliveredList from './page/employee/WarrantyEmployee/Wproduct/WarrantyProductDELIVEREDList';
import EngineerPage from './page/employee/engineer/EngineerPage';
import EngiInformationProdcutRepair from './page/employee/engineer/engineerProduct/EngiInformationProdcutRepair';
import EngiOrderMaterialList from './page/employee/engineer/engineerOrderMaterial/EngiOrderMaterialList';
import EngiOrderMaterialCreate from './page/employee/engineer/engineerOrderMaterial/EngiOrderMaterialCreate';
import EngiOrderMaterialDetail from './page/employee/engineer/engineerOrderMaterial/EngiOrderMaterialDetail';
import EngiMaterialList from './page/employee/engineer/engineerMaterial/EngiMaterialList';
import WarrantyRegisterProductGuarantee from './page/employee/WarrantyEmployee/Wproduct/WarrantyRegisterProductGuarantee';
import WarrantyProductGuaranteeWaitingForRepairList from './page/employee/WarrantyEmployee/Wproduct/WarrantyProductGuaranteeWaitingForRepairList';
import WarrantyChangeEngineerProductGuarantee from './page/employee/WarrantyEmployee/Wproduct/WarrantyChangeEngineerProductGuarantee';
import EngiInformationProductGuaranteeRepair from './page/employee/engineer/engineerProduct/EngiInformationProductGuaranteeRepair';
import WarrantyProductGuaranteeRepairedList from './page/employee/WarrantyEmployee/Wproduct/WarrantyProductGuaranteeRepairedList';
import WarrantyBillCreate from './page/employee/WarrantyEmployee/WarrantyBillAndGuarantee/WarrantyBillCreate';
import WarrantyGuaranteeCreate from './page/employee/WarrantyEmployee/WarrantyBillAndGuarantee/WarrantyGuaranteeCreate';
import AdminPage from './page/admin/AdminPage';
import MaterialPage from './page/admin/statistics/MaterialPage';
import EmployeeManageCreate from './page/admin/employeeManage/EmployeManageCreate';
import EmployeeManageList from './page/admin/employeeManage/EmployeeManageList';
import EmployeeManageDetail from './page/admin/employeeManage/EmployeeManageDetail';
import MaterialManageList from './page/admin/manageMaterial/MaterialManageList';
import ProductPage from './page/admin/statistics/ProductPage';
import ProductGuaranteePage from './page/admin/statistics/ProductGuaranteePage';
import Dashboard from './page/admin/statistics/Dashboard';
import ExportPDF from './page/employee/Receptionist/billAndGuarantee/ExportPDF';
import ExportBillGuaranteePDF from './page/employee/WarrantyEmployee/WarrantyBillAndGuarantee/ExportBillGuaranteePDF';
import WarrantyProductUnderRepairList from './page/employee/WarrantyEmployee/Wproduct/WarrantyProductUnderRepairList';
import WarrantyCustomerList from './page/employee/WarrantyEmployee/Wcustomer/WarrantyCustomerList';
import RecepReceiptCreate from './page/employee/Receptionist/receipt/RecepReceiptCreate';
import RecepReceiptList from './page/employee/Receptionist/receipt/RecepReceiptList';
import RecepReceiptDetail from './page/employee/Receptionist/receipt/RecepReceiptDetail';
import WarrantyReceiptCreate from './page/employee/WarrantyEmployee/receipt/WarrantyReceiptCreate';
import WarrantyReceiptDetail from './page/employee/WarrantyEmployee/receipt/WarrantyReceiptDetail';
import WarrantyReceiptList from './page/employee/WarrantyEmployee/receipt/WarrantyReceiptList';
import WarrantyProductNoCreateReceiptList from './page/employee/WarrantyEmployee/receipt/WarrantyProductNoCreateReceiptList';
import RecepProductNoCreateReceiptList from './page/employee/Receptionist/receipt/RecepProductNoCreateReceiptList';
import SearchPage from './page/employee/SearchPage';
import RoomPage from './page/chat/RoomPage';
import ChatRoomPage from './page/chat/ChatRoomPage';
import RoomCreate from './page/chat/RoomCreate';
import ModalProvider from './page/chat/Context/ModalProvider';
import InviteMemberModal from './page/chat/Modals/InviteMemberModal';
import LayoutChat from './components/LayoutChat';
import AddRoomModal from './page/chat/Modals/AddRoomModal';

function App() {


  return (
    <>
      <ModalProvider>
        <Routes>

          <Route path='receptionist' element={<Layout />}>
            <Route element={<AuthorizeRoutes requireRoles={["NHANVIENLETAN"]} />}>
              <Route index element={<ReceptionistPage />} />
              <Route path='customer/create' element={<RecepCustomerCreate />} />
              <Route path='customer/:customerId' element={<RecepCustomerDetail />} />
              <Route path="customers" element={<RecepCustomerList />} />
              <Route path="update-customer/:customerId" element={<RecepCustomerUpdate />} />
              <Route path="products" element={<RecepProductList />} />
              <Route path="product/create/:customerId" element={<RecepProductCreate />} />
              <Route path="product-pending" element={<RecepProductPendingList />} />
              <Route path="product-pending/:productId" element={<RecepProductPendingDetail />} />
              <Route path="transfer-product" element={<RecepTransferProductList />} />
              <Route path="transfer-product/:productId" element={<RecepTransferProductDetail />} />
              <Route path="under-repair" element={<RecepProductUnderRepairList />} />
              <Route path="change-engineer/:productId" element={<RecepChangeEngineerProduct />} />
              <Route path="guarantee-create/:productId" element={<RecepGuaranteeCreate />} />
              <Route path="bill-create/:productId" element={<RecepBillCreate />} />
              <Route path="pdf/:billId" element={<ExportPDF />} />
              <Route path="receipts" element={<RecepReceiptList />} />
              <Route path="receipt-detail/:receiptId" element={<RecepReceiptDetail />} />
              <Route path="receipt/:productId" element={<RecepReceiptCreate />} />
              <Route path="no-receipts" element={<RecepProductNoCreateReceiptList />} />
            </Route>
          </Route>

          <Route path='engineer' element={<Layout />}>
            <Route element={<AuthorizeRoutes requireRoles={["NHANVIENSUACHUA"]} />}>
              <Route index element={<EngineerPage />} />
              <Route path="product/:productId" element={<EngiInformationProdcutRepair />} />
              <Route path="product-guarantee/:productId" element={<EngiInformationProductGuaranteeRepair />} />
              <Route path="orders" element={<EngiOrderMaterialList />} />
              <Route path="order/create/:materialId" element={<EngiOrderMaterialCreate />} />
              <Route path="order/:orderId" element={<EngiOrderMaterialDetail />} />
              <Route path="materials" element={<EngiMaterialList />} />
            </Route>
          </Route>

          <Route path='warehouse' element={<Layout />}>
            <Route element={<AuthorizeRoutes requireRoles={["NHANVIENKHO"]} />}>
              <Route index element={<WarehouseEmployeePage />} />
              <Route path="component/create" element={<WareComponentCreate />} />
              <Route path="components" element={<WareComponentList />} />
              <Route path="component/:componentId" element={<WareComponentDetail />} />
              <Route path="vendor/create" element={<WareVendorCreate />} />
              <Route path="vendor/:vendorId" element={<WareVendorDetail />} />
              <Route path="vendors" element={<WareVendorList />} />
              <Route path="material/create" element={<WareMaterialCreate />} />
              <Route path="material/:materialId" element={<WareMaterialDetail />} />
              <Route path="update-material/:materialId" element={<WareUpdateMaterial />} />
              <Route path="orderMaterials" element={<WareOrderMaterialList />} />
              <Route path="orderMaterial/:orderId" element={<WareOerderMaterialDetail />} />
              <Route path="search/order" element={<WareSearchOrder />} />
              <Route path="search/material" element={<WareSearchMaterial />} />
            </Route>
          </Route>

          <Route path='warranty' element={<Layout />}>
            <Route element={<AuthorizeRoutes requireRoles={["NHANVIENBAOHANH"]} />}>
              <Route index element={<WarranttyEmployeePage />} />
              <Route path='product-delivered' element={<WarrantyProductDeliveredList />} />
              <Route path='create-guarantee/:productId' element={<WarrantyRegisterProductGuarantee />} />
              <Route path='product-guarantee' element={<WarrantyProductGuaranteeWaitingForRepairList />} />
              <Route path='product-guarantee/:productId' element={<WarrantyChangeEngineerProductGuarantee />} />
              <Route path='product-guarantee-repaired' element={<WarrantyProductGuaranteeRepairedList />} />
              <Route path='bill-create/:productId' element={<WarrantyBillCreate />} />
              <Route path='guarantee-create/:productId' element={<WarrantyGuaranteeCreate />} />
              <Route path="pdf/:billId" element={<ExportBillGuaranteePDF />} />
              <Route path="under-repair" element={<WarrantyProductUnderRepairList />} />
              <Route path="customers" element={<WarrantyCustomerList />} />
              <Route path="no-receipts" element={<WarrantyProductNoCreateReceiptList />} />
              <Route path="receipts" element={<WarrantyReceiptList />} />
              <Route path="receipt-detail/:receiptId" element={<WarrantyReceiptDetail />} />
              <Route path="receipt/:productId" element={<WarrantyReceiptCreate />} />
            </Route>
          </Route>



          <Route path='admin' element={<Layout />}>
            <Route element={<AuthorizeRoutes requireRoles={["ADMIN"]} />}>
              <Route index element={<AdminPage />} />
              <Route path="material-manage" element={<MaterialPage />} />
              <Route path="product-manage" element={<ProductPage />} />
              <Route path="product-guarantee-manage" element={<ProductGuaranteePage />} />
              <Route path="dashboard" element={<Dashboard />} />
              <Route path="employee/create" element={<EmployeeManageCreate />} />
              <Route path="employees" element={<EmployeeManageList />} />
              <Route path="employee/:employeeId" element={<EmployeeManageDetail />} />
              <Route path="materials" element={<MaterialManageList />} />
            </Route>
          </Route>


          <Route path='employee' element={<Layout />}>
            <Route element={<AuthorizeRoutes requireRoles={[
              "NHANVIENSUACHUA",
              "NHANVIENLETAN",
              "ADMIN",
              "NHANVIENKHO",
              "NHANVIENBAOHANH",
            ]} />}>
              <Route path="change-password" element={<ChangePassword />} />
              <Route path="personal-information" element={<PersonalInformation />} />
              <Route path="search" element={<SearchPage />} />

              <Route path="room" element={<RoomPage />} />
              <Route path="room/create" element={<RoomCreate />} />
              <Route path="room/:roomId" element={<ChatRoomPage />} />

            </Route>
          </Route>



          <Route path='chat' element={<LayoutChat />}>
            <Route element={<AuthorizeRoutes requireRoles={[
              "NHANVIENSUACHUA",
              "NHANVIENLETAN",
              "ADMIN",
              "NHANVIENKHO",
              "NHANVIENBAOHANH",
            ]} />}>          
              <Route path="room" element={<RoomPage />} />
              <Route path="room/create" element={<RoomCreate />} />
              <Route path="room/:roomId" element={<ChatRoomPage />} />
            </Route>
          </Route>




          <Route path="/login" element={<LoginPage />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/" element={<Visitor />} />
          <Route path="*" element={<NotFound />} />
        </Routes>

        <InviteMemberModal />
        <AddRoomModal />

        <ToastContainer
          position="top-right"
          autoClose={1000}
          hideProgressBar
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="colored"
        />
      </ModalProvider>
    </>
  )
}

export default App
