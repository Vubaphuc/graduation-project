import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import authReducer from "./slice/authSlice"
import { authApi } from "./apis/login/authApi";
import { visitorApi } from "./apis/visitor/visitorApi";
import { employeeApi } from "./apis/employee/employeeApi";
import { approverOrderMaterialApi } from "./apis/warehouseEmployee/approverOrderMaterialApi";
import { warehouseEmployeeApi } from "./apis/warehouseEmployee/warehouseEmployeeApi";
import { customerApi } from "./apis/receptionist/customerApi";
import { productApi } from "./apis/receptionist/productApi";
import { warrantyProductApi } from "./apis/warrantyEmoloyee/warrantyProductApi";
import { engineerOrderMaterialApi } from "./apis/engineer/engineerOrderMaterialApi";
import { engineerProductApi } from "./apis/engineer/engineerProductApi";
import { employeeManageApi } from "./apis/admin/employeeManageApi";
import { materialManageApi } from "./apis/admin/materialManageApi";
import { productManageApi } from "./apis/admin/productManageApi";
import { productGuaranteeManageApi } from "./apis/admin/productGuaranteeManageApi";
import { chatApi } from "./apis/employee/chatApi";


const store = configureStore ({
    reducer: {
        auth: authReducer,
        [authApi.reducerPath]: authApi.reducer,
        [visitorApi.reducerPath]: visitorApi.reducer,
        [employeeApi.reducerPath]: employeeApi.reducer,
        [approverOrderMaterialApi.reducerPath]: approverOrderMaterialApi.reducer,
        [warehouseEmployeeApi.reducerPath]: warehouseEmployeeApi.reducer,
        [customerApi.reducerPath]: customerApi.reducer,
        [productApi.reducerPath]: productApi.reducer,
        [warrantyProductApi.reducerPath]: warrantyProductApi.reducer,
        [engineerOrderMaterialApi.reducerPath]: engineerOrderMaterialApi.reducer,
        [engineerProductApi.reducerPath]: engineerProductApi.reducer,
        [employeeManageApi.reducerPath]: employeeManageApi.reducer,
        [materialManageApi.reducerPath]: materialManageApi.reducer,
        [productManageApi.reducerPath]: productManageApi.reducer,
        [productGuaranteeManageApi.reducerPath]: productGuaranteeManageApi.reducer,
        [chatApi.reducerPath]: chatApi.reducer
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(
        authApi.middleware,
        visitorApi.middleware,
        employeeApi.middleware,
        approverOrderMaterialApi.middleware,
        warehouseEmployeeApi.middleware,
        customerApi.middleware,
        productApi.middleware,
        warrantyProductApi.middleware,
        engineerOrderMaterialApi.middleware,
        engineerProductApi.middleware,
        employeeManageApi.middleware,
        materialManageApi.middleware,
        productManageApi.middleware,
        productGuaranteeManageApi.middleware,
        chatApi.middleware
    ),

});

export default store;