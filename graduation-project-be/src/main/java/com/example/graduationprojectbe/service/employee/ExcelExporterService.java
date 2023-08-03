package com.example.graduationprojectbe.service.employee;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.dto.CustomerDto;
import com.example.graduationprojectbe.dto.dto.OrderMaterialDto;
import com.example.graduationprojectbe.dto.dto.ProductNameModelLimitDto;
import com.example.graduationprojectbe.dto.dto.VendorCountDto;
import com.example.graduationprojectbe.dto.projection.MaterialProjection;
import com.example.graduationprojectbe.dto.projection.UpdateMaterialInfo;
import com.example.graduationprojectbe.entity.*;
import com.example.graduationprojectbe.repository.*;
import jakarta.persistence.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelExporterService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductGuaranteeRepository productGuaranteeRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private OrderMaterialRepository orderMaterialRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ComponentsRepository componentsRepository;
    @Autowired
    private GuaranteeRepository guaranteeRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillGuaranteeRepository billGuaranteeRepository;
    @Autowired
    private UpdateMaterialRepository updateMaterialRepository;



    public File exportProductToExcel() {

        // export excel product

        List<Product> products = productRepository.findAll();
        String filePath = "Product.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Product Data");

            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name Model");
            headerRow.createCell(2).setCellValue("Phone Company");
            headerRow.createCell(3).setCellValue("IME");
            headerRow.createCell(4).setCellValue("Defect Name");
            headerRow.createCell(5).setCellValue("Status");
            headerRow.createCell(6).setCellValue("Price");
            headerRow.createCell(7).setCellValue("Customer Name");
            headerRow.createCell(8).setCellValue("Customer Phone Number");
            headerRow.createCell(9).setCellValue("Customer Email");
            headerRow.createCell(10).setCellValue("Input Date");
            headerRow.createCell(11).setCellValue("Recipient Code");
            headerRow.createCell(12).setCellValue("Recipient Name");
            headerRow.createCell(13).setCellValue("Transfer Date");
            headerRow.createCell(14).setCellValue("Engineer Code");
            headerRow.createCell(15).setCellValue("Engineer Name");
            headerRow.createCell(16).setCellValue("Repair Location");
            headerRow.createCell(17).setCellValue("Component Name");
            headerRow.createCell(18).setCellValue("Note");
            headerRow.createCell(19).setCellValue("Output Date");
            headerRow.createCell(20).setCellValue("ProductPayer Code");
            headerRow.createCell(21).setCellValue("ProductPayer Name");
            headerRow.createCell(22).setCellValue("Finish Date");


            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (Product product : products) {


                String inputDate = product.getInputDate() != null ? product.getInputDate().toLocalDate().format(format) : "";
                String transferDate = product.getTransferDate() != null ? product.getTransferDate().toLocalDate().format(format) : "";
                String outputDate = product.getOutputDate() != null ? product.getOutputDate().toLocalDate().format(format) : "";
                String finishDate = product.getFinishDate() != null ? product.getFinishDate().toLocalDate().format(format) : "";



                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getNameModel());
                row.createCell(2).setCellValue(product.getPhoneCompany());
                row.createCell(3).setCellValue(product.getIme());
                row.createCell(4).setCellValue(product.getDefectName());
                row.createCell(5).setCellValue(product.getStatus() != null ? product.getStatus().getMessage() : "");
                row.createCell(6).setCellValue(product.getPrice());
                row.createCell(7).setCellValue(product.getCustomer() != null ? product.getCustomer().getFullName() : "");
                row.createCell(8).setCellValue(product.getCustomer() != null ? product.getCustomer().getPhoneNumber() : "");
                row.createCell(9).setCellValue(product.getCustomer() != null ? product.getCustomer().getEmail() : "");
                row.createCell(10).setCellValue(inputDate);
                row.createCell(11).setCellValue(product.getReceptionists() != null ? product.getReceptionists().getEmployeeCode() : "");
                row.createCell(12).setCellValue(product.getReceptionists() != null ? product.getReceptionists().getEmployeeName() : "");
                row.createCell(13).setCellValue(transferDate);
                row.createCell(14).setCellValue(product.getEngineer() != null ? product.getEngineer().getEmployeeCode() : "");
                row.createCell(15).setCellValue(product.getEngineer() != null ? product.getEngineer().getEmployeeName() : "");
                row.createCell(16).setCellValue(product.getLocation() != null ? product.getLocation() : "");
                row.createCell(17).setCellValue(product.getComponents() != null ? product.getComponents().getName() : "");
                row.createCell(18).setCellValue(product.getNote() != null ? product.getNote() : "");
                row.createCell(19).setCellValue(outputDate);
                row.createCell(20).setCellValue(product.getProductPayer() != null ? product.getProductPayer().getEmployeeCode() : "");
                row.createCell(21).setCellValue(product.getProductPayer() != null ? product.getProductPayer().getEmployeeName() : "");
                row.createCell(22).setCellValue(finishDate);
            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 23; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }



    // export excel product guarantee
    public File exportProductGuaranteeToExcel() {

        List<Integer> ids = new ArrayList<>();

        List<ProductGuarantee> productGuarantees = productGuaranteeRepository.findAll();
        String filePath = "ProductGuarantee.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Product Guarantee Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name Model");
            headerRow.createCell(2).setCellValue("Phone Company");
            headerRow.createCell(3).setCellValue("IME");
            headerRow.createCell(4).setCellValue("Defect Name");
            headerRow.createCell(5).setCellValue("Status");
            headerRow.createCell(6).setCellValue("Price");
            headerRow.createCell(7).setCellValue("Customer Name");
            headerRow.createCell(8).setCellValue("Customer Phone Number");
            headerRow.createCell(9).setCellValue("Customer Email");
            headerRow.createCell(10).setCellValue("Input Date");
            headerRow.createCell(11).setCellValue("Recipient Code");
            headerRow.createCell(12).setCellValue("Recipient Name");
            headerRow.createCell(13).setCellValue("Transfer Date");
            headerRow.createCell(14).setCellValue("Engineer Code");
            headerRow.createCell(15).setCellValue("Engineer Name");
            headerRow.createCell(16).setCellValue("Repair Location");
            headerRow.createCell(17).setCellValue("Component Name");
            headerRow.createCell(18).setCellValue("Note");
            headerRow.createCell(19).setCellValue("Output Date");
            headerRow.createCell(20).setCellValue("ProductPayer Code");
            headerRow.createCell(21).setCellValue("ProductPayer Name");
            headerRow.createCell(22).setCellValue("Finish Date");


            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (ProductGuarantee productGuarantee : productGuarantees) {


                String inputDate = productGuarantee.getInputDate() != null ? productGuarantee.getInputDate().toLocalDate().format(format) : "";
                String transferDate = productGuarantee.getTransferDate() != null ? productGuarantee.getTransferDate().toLocalDate().format(format) : "";
                String outputDate = productGuarantee.getOutputDate() != null ? productGuarantee.getOutputDate().toLocalDate().format(format) : "";
                String finishDate = productGuarantee.getFinishDate() != null ? productGuarantee.getFinishDate().toLocalDate().format(format) : "";



                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(productGuarantee.getId());
                row.createCell(1).setCellValue(productGuarantee.getNameModel());
                row.createCell(2).setCellValue(productGuarantee.getPhoneCompany());
                row.createCell(3).setCellValue(productGuarantee.getIme());
                row.createCell(4).setCellValue(productGuarantee.getDefectName());
                row.createCell(5).setCellValue(productGuarantee.getStatus() != null ? productGuarantee.getStatus().getMessage() : "");
                row.createCell(6).setCellValue(productGuarantee.getPrice());
                row.createCell(7).setCellValue(productGuarantee.getCustomer() != null ? productGuarantee.getCustomer().getFullName() : "");
                row.createCell(8).setCellValue(productGuarantee.getCustomer() != null ? productGuarantee.getCustomer().getPhoneNumber() : "");
                row.createCell(9).setCellValue(productGuarantee.getCustomer() != null ? productGuarantee.getCustomer().getEmail() : "");
                row.createCell(10).setCellValue(inputDate);
                row.createCell(11).setCellValue(productGuarantee.getWarranty() != null ? productGuarantee.getWarranty().getEmployeeCode() : "");
                row.createCell(12).setCellValue(productGuarantee.getWarranty() != null ? productGuarantee.getWarranty().getEmployeeName() : "");
                row.createCell(13).setCellValue(transferDate);
                row.createCell(14).setCellValue(productGuarantee.getEngineer() != null ? productGuarantee.getEngineer().getEmployeeCode() : "");
                row.createCell(15).setCellValue(productGuarantee.getEngineer() != null ? productGuarantee.getEngineer().getEmployeeName() : "");
                row.createCell(16).setCellValue(productGuarantee.getLocation() != null ? productGuarantee.getLocation() : "");
                row.createCell(17).setCellValue(productGuarantee.getComponents() != null ? productGuarantee.getComponents().getName() : "");
                row.createCell(18).setCellValue(productGuarantee.getNote() != null ? productGuarantee.getNote() : "");
                row.createCell(19).setCellValue(outputDate);
                row.createCell(20).setCellValue(productGuarantee.getProductPayer() != null ? productGuarantee.getProductPayer().getEmployeeCode() : "");
                row.createCell(21).setCellValue(productGuarantee.getProductPayer() != null ? productGuarantee.getProductPayer().getEmployeeName() : "");
                row.createCell(22).setCellValue(finishDate);
            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 23; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }


    // export excel Customer
    public File exportCustomerToExcel() {
        List<CustomerDto> customers = customerRepository.productByCustomerAll();
        String filePath = "Customer.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Customer Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Customer Name");
            headerRow.createCell(2).setCellValue("Customer Phone Number");
            headerRow.createCell(3).setCellValue("Customer Email");
            headerRow.createCell(4).setCellValue("Customer Address");
            headerRow.createCell(5).setCellValue("Product Quantity");



            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (CustomerDto customer : customers) {

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getFullName());
                row.createCell(2).setCellValue(customer.getPhone());
                row.createCell(3).setCellValue(customer.getEmail());
                row.createCell(4).setCellValue(customer.getAddress());
                row.createCell(5).setCellValue(customer.getSumProduct());

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    // export excel Material
    public File exportMaterialToExcel() {
        List<Material> materials = materialRepository.findAll();
        String filePath = "Material.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Material Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Material Code");
            headerRow.createCell(2).setCellValue("Name Model");
            headerRow.createCell(3).setCellValue("Import Quantity");
            headerRow.createCell(4).setCellValue("Export Quantity");
            headerRow.createCell(5).setCellValue("Remaining Quantity");
            headerRow.createCell(6).setCellValue("Price / EA");
            headerRow.createCell(7).setCellValue("Component Name");
            headerRow.createCell(8).setCellValue("Vendor Name");
            headerRow.createCell(9).setCellValue("Warehouse Employee Code");
            headerRow.createCell(10).setCellValue("Warehouse Employee Name");
            headerRow.createCell(11).setCellValue("Create Date");



            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (Material material : materials) {

                String createDate = material.getCreateDate() != null ? material.getCreateDate().toLocalDate().format(format) : "";

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(material.getId());
                row.createCell(1).setCellValue(material.getCode());
                row.createCell(2).setCellValue(material.getNameModel());
                row.createCell(3).setCellValue(material.getImportQuantity());
                row.createCell(4).setCellValue(material.getExportQuantity());
                row.createCell(5).setCellValue(material.getRemainingQuantity());
                row.createCell(6).setCellValue(material.getPrice());
                row.createCell(7).setCellValue(material.getComponents() != null ? material.getComponents().getName() : "");
                row.createCell(8).setCellValue(material.getVendor() != null ? material.getVendor().getName() : "");
                row.createCell(9).setCellValue(material.getWarehouseEmployee() != null ? material.getWarehouseEmployee().getEmployeeCode() : "");
                row.createCell(10).setCellValue(material.getWarehouseEmployee() != null ? material.getWarehouseEmployee().getEmployeeName() : "");
                row.createCell(11).setCellValue(createDate);

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 12; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }


    // export excel Order Material
    public File exportOrderMaterialToExcel() {
        List<OrderMaterial> orderMaterials = orderMaterialRepository.findAll();
        String filePath = "Order.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Order Data");

            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Order Code");
            headerRow.createCell(2).setCellValue("Material Code");
            headerRow.createCell(3).setCellValue("Model Name");
            headerRow.createCell(4).setCellValue("Component Name");
            headerRow.createCell(5).setCellValue("Quantity");
            headerRow.createCell(6).setCellValue("Create Date");
            headerRow.createCell(7).setCellValue("orderer Code");
            headerRow.createCell(8).setCellValue("orderer Name");
            headerRow.createCell(9).setCellValue("Approval Date");
            headerRow.createCell(10).setCellValue("Approver Code");
            headerRow.createCell(11).setCellValue("Approver Name");
            headerRow.createCell(12).setCellValue("Status");


            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (OrderMaterial orderMaterial : orderMaterials) {

                String createDate = orderMaterial.getCreateDate() != null ? orderMaterial.getCreateDate().toLocalDate().format(format) : "";
                String approvalDate = orderMaterial.getApprovalDate() != null ? orderMaterial.getApprovalDate().toLocalDate().format(format) : "";

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(orderMaterial.getId());
                row.createCell(1).setCellValue(orderMaterial.getOrderCode());
                row.createCell(2).setCellValue(orderMaterial.getMaterial() != null ? orderMaterial.getMaterial().getCode() : "");
                row.createCell(3).setCellValue(orderMaterial.getMaterial() != null ? orderMaterial.getMaterial().getNameModel() : "");
                row.createCell(4).setCellValue(orderMaterial.getMaterial() != null ? orderMaterial.getMaterial().getComponents().getName() : "");
                row.createCell(5).setCellValue(orderMaterial.getQuantity());
                row.createCell(6).setCellValue(createDate);
                row.createCell(7).setCellValue(orderMaterial.getOrderer() != null ? orderMaterial.getOrderer().getEmployeeCode() : "");
                row.createCell(8).setCellValue(orderMaterial.getOrderer() != null ? orderMaterial.getOrderer().getEmployeeName() : "");
                row.createCell(9).setCellValue(approvalDate);
                row.createCell(10).setCellValue(orderMaterial.getApprover() != null ? orderMaterial.getApprover().getEmployeeCode() : "");
                row.createCell(11).setCellValue(orderMaterial.getApprover() != null ? orderMaterial.getApprover().getEmployeeName() : "");
                row.createCell(12).setCellValue(orderMaterial.isStatus() == true ? "OK" : "PENDING");

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 13; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    // export excel Components
    public File exportComponentsToExcel() {
        List<Components> components = componentsRepository.findAll();
        String filePath = "Component.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Component Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Component Name");
            headerRow.createCell(2).setCellValue("Warranty Period");
            headerRow.createCell(3).setCellValue("Create Date");
            headerRow.createCell(4).setCellValue("Warehouse Employee Code");
            headerRow.createCell(5).setCellValue("Warehouse Employee Name");


            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (Components component : components) {

                String createDate = component.getCreateDate() != null ? component.getCreateDate().toLocalDate().format(format) : "";

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(component.getId());
                row.createCell(1).setCellValue(component.getName());
                row.createCell(2).setCellValue(component.getWarrantyPeriod());
                row.createCell(3).setCellValue(createDate);
                row.createCell(4).setCellValue(component.getWarehouseEmployee() != null ? component.getWarehouseEmployee().getEmployeeCode() : "");
                row.createCell(5).setCellValue(component.getWarehouseEmployee() != null ? component.getWarehouseEmployee().getEmployeeName() : "");

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    // export excel Vendor
    public File exportVendorsToExcel() {
        List<VendorCountDto> vendors = vendorRepository.getVendorByMaterial();
        String filePath = "Vendor.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Vendor Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Vendor Name");
            headerRow.createCell(2).setCellValue("Material Quantity");


            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (VendorCountDto vendor : vendors) {
                
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(vendor.getId());
                row.createCell(1).setCellValue(vendor.getName());
                row.createCell(2).setCellValue(vendor.getQuantityMaterial());

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    public File exportTotalMaterialByCodeToExcel() {

        List<OrderMaterialDto> orderMaterials = orderMaterialRepository.findTotalQuantityExportMaterialByMaterialCode();
        String filePath = "Order.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Order Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Material Code");
            headerRow.createCell(1).setCellValue("Component Name");
            headerRow.createCell(2).setCellValue("Quantity");


            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (OrderMaterialDto order : orderMaterials) {

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getMaterialCode());
                row.createCell(1).setCellValue(order.getComponentName());
                row.createCell(2).setCellValue(order.getTotalQuantityExport());

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    public File exportProductByNameModelToExcel() {
        List<ProductNameModelLimitDto> products = productRepository.findProductByNameModelLimit();
        String filePath = "Product.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Product Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Model Name");
            headerRow.createCell(1).setCellValue("Quantity");


            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (ProductNameModelLimitDto product : products) {

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(product.getNameModel());
                row.createCell(1).setCellValue(product.getSumProduct());

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 2; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    public File exportMaterialRemainingQuantityLimitToExcel() {
        List<MaterialProjection> materials = materialRepository.findMaterialRemainingQuantityLimit();
        String filePath = "Material.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Material Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Material Code");
            headerRow.createCell(2).setCellValue("Name Model");
            headerRow.createCell(3).setCellValue("Import Quantity");
            headerRow.createCell(4).setCellValue("Export Quantity");
            headerRow.createCell(5).setCellValue("Remaining Quantity");
            headerRow.createCell(6).setCellValue("Price / EA");
            headerRow.createCell(7).setCellValue("Component Name");
            headerRow.createCell(8).setCellValue("Vendor Name");
            headerRow.createCell(9).setCellValue("Warehouse Employee Code");
            headerRow.createCell(10).setCellValue("Warehouse Employee Name");
            headerRow.createCell(11).setCellValue("Create Date");



            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (MaterialProjection material : materials) {

                String createDate = material.getCreateDate() != null ? material.getCreateDate().toLocalDate().format(format) : "";

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(material.getId());
                row.createCell(1).setCellValue(material.getCode());
                row.createCell(2).setCellValue(material.getNameModel());
                row.createCell(3).setCellValue(material.getImportQuantity());
                row.createCell(4).setCellValue(material.getExportQuantity());
                row.createCell(5).setCellValue(material.getRemainingQuantity());
                row.createCell(6).setCellValue(material.getPrice());
                row.createCell(7).setCellValue(material.getComponents() != null ? material.getComponents().getName() : "");
                row.createCell(8).setCellValue(material.getVendor() != null ? material.getVendor().getName() : "");
                row.createCell(9).setCellValue(material.getWarehouseEmployee() != null ? material.getWarehouseEmployee().getEmployeeCode() : "");
                row.createCell(10).setCellValue(material.getWarehouseEmployee() != null ? material.getWarehouseEmployee().getEmployeeName() : "");
                row.createCell(11).setCellValue(createDate);

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 12; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }

    public File exportMaterialUpdateToExcel() {
        List<UpdateMaterial> materials = updateMaterialRepository.findAll();
        String filePath = "Material.xlsx";

        File excelFile = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Material Data");


            // Tạo header cho file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Update Date");
            headerRow.createCell(1).setCellValue("Material Code");
            headerRow.createCell(2).setCellValue("Quantity");
            headerRow.createCell(3).setCellValue("Employee Update Code");
            headerRow.createCell(4).setCellValue("Employee Update Name");



            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Đổ dữ liệu từ danh sách sản phẩm vào file Excel
            int rowNum = 1;
            for (UpdateMaterial material : materials) {

                String updateDate = material.getUpdateDate() != null ? material.getUpdateDate().toLocalDate().format(format) : "";

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(updateDate);
                row.createCell(1).setCellValue(material.getMaterial().getCode());
                row.createCell(2).setCellValue(material.getQuantity());
                row.createCell(3).setCellValue(material.getEmployeeUpdate().getEmployeeCode());
                row.createCell(4).setCellValue(material.getEmployeeUpdate().getEmployeeName());

            }

            // Tự động điều chỉnh cột cho phù hợp
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {
                workbook.write(outputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelFile;
    }
}
