import { getStatusLabel } from "./enum";
import { exportToCSV } from "./excelUtils";


export const exportToExcel = (data) => {

    if (data === null || data.length === 0) {

        const csvDataNull = [{
            "ID": "",
            "Name Model": "",
            "Phone Company": "",
            "IME": "",
            "Defect Name": "",
            "Status": "",
            "Price": "",
            "Customer Name": "",
            "Input Date": "",
            "Recipient Code": "",
            "Recipient Name": "",
            "Transfer Date": "",
            "Engineer Code": "",
            "Engineer Name": "",
            "Repair Location": "",
            "Component Name": "",
            "Note": "",
            "Output Date": "",
            "ProductPayer Code": "",
            "ProductPayer Name": "",
            "Finish Date": ""
        }]

        exportToCSV(csvDataNull, "Product.xlsx");
    } else {


        const csvData = data.map((product) => ({
            "ID": product.id ? product.id : "",
            "Name Model": product.nameModel ? product.nameModel : "",
            "Phone Company": product.phoneCompany ? product.phoneCompany : "",
            "IME": product.ime ? product.ime : "",
            "Defect Name": product.defectName ? product.defectName : "",
            "Status": getStatusLabel(product.status),
            "Price": product.price ? product.price : "",
            "Customer Name": product.customer ? product.customer.fullName : "",
            "Input Date": product.inputDate ? new Date(product.inputDate).toLocaleDateString() : "",
            "Recipient Code": product.receptionists ? product.receptionists.employeeCode : "",
            "Recipient Name": product.receptionists ? product.receptionists.employeeName : "",
            "Transfer Date": product.transferDate ? new Date(product.transferDate).toLocaleDateString() : "",
            "Engineer Code": product.engineer ? product.engineer.employeeCode : "",
            "Engineer Name": product.engineer ? product.engineer.employeeName : "",
            "Repair Location": product.location ? product.location : "",
            "Component Name": product.components ? product.components.name : "",
            "Note": product.note ? product.note : "",
            "Output Date": product.outputDate ? new Date(product.outputDate).toLocaleDateString() : "",
            "ProductPayer Code": product.productPayer ? product.productPayer.employeeCode : "",
            "ProductPayer Name": product.productPayer ? product.productPayer.employeeName : "",
            "Finish Date": product.finishDate ? new Date(product.finishDate).toLocaleDateString() : ""
        }));

        exportToCSV(csvData, "Product.xlsx");

    }

};


export const exportProductGuaranteeToExcel = (data) => {

    const csvData = data.map((product) => ({
        "ID": product.id ? product.id : "",
        "Name Model": product.nameModel ? product.nameModel : "",
        "Phone Company": product.phoneCompany ? product.phoneCompany : "",
        "IME": product.ime ? product.ime : "",
        "Defect Name": product.defectName ? product.defectName : "",
        "Status": getStatusLabel(product.status),
        "Price": product.price ? product.price : "",
        "Customer Name": product.customer ? product.customer.fullName : "",
        "Input Date": product.inputDate ? new Date(product.inputDate).toLocaleDateString() : "",
        "Warranty Code": product.warranty ? product.warranty.employeeCode : "",
        "Warranty Name": product.warranty ? product.warranty.employeeName : "",
        "Transfer Date": product.transferDate ? new Date(product.transferDate).toLocaleDateString() : "",
        "Engineer Code": product.engineer ? product.engineer.employeeCode : "",
        "Engineer Name": product.engineer ? product.engineer.employeeName : "",
        "Repair Location": product.location ? product.location : "",
        "Component Name": product.components ? product.components.name : "",
        "Note": product.note ? product.note : "",
        "Output Date": product.outputDate ? new Date(product.outputDate).toLocaleDateString() : "",
        "ProductPayer Code": product.productPayer ? product.productPayer.employeeCode : "",
        "ProductPayer Name": product.productPayer ? product.productPayer.employeeName : "",
        "Finish Date": product.finishDate ? new Date(product.finishDate).toLocaleDateString() : ""
    }));

    exportToCSV(csvData, "Product.xlsx");

};