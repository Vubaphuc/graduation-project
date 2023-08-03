package com.example.graduationprojectbe.controller.employee;

import com.example.graduationprojectbe.entity.Product;
import com.example.graduationprojectbe.service.employee.ExcelExporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("excel/api/v1")
public class ExcelExporterController {
    @Autowired
    private ExcelExporterService excelExporterService;

    @GetMapping("export-products")
    public ResponseEntity<?> exportProductsToExcel() {

        File excelFile = excelExporterService.exportProductToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-product-guarantees")
    public ResponseEntity<?> exportProductGuaranteeToExcel() {

        File excelFile = excelExporterService.exportProductGuaranteeToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-customer")
    public ResponseEntity<?> exportCustomerToExcel() {

        File excelFile = excelExporterService.exportCustomerToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-material")
    public ResponseEntity<?> exportMaterialToExcel() {

        File excelFile = excelExporterService.exportMaterialToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-order-material")
    public ResponseEntity<?> exportOrderMaterialToExcel() {

        File excelFile = excelExporterService.exportOrderMaterialToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-component")
    public ResponseEntity<?> exportComponentsToExcel() {

        File excelFile = excelExporterService.exportComponentsToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-vendor")
    public ResponseEntity<?> exportVendorsToExcel() {

        File excelFile = excelExporterService.exportVendorsToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
    //
    @GetMapping("export-total-material")
    public ResponseEntity<?> exportTotalMaterialByCodeToExcel() {

        File excelFile = excelExporterService.exportTotalMaterialByCodeToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    //
    @GetMapping("export-product-name")
    public ResponseEntity<?> exportProductByNameModelToExcel() {

        File excelFile = excelExporterService.exportProductByNameModelToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("export-material-remaining-quantity-limit")
    public ResponseEntity<?> exportMaterialRemainingQuantityLimitToExcel() {

        File excelFile = excelExporterService.exportMaterialRemainingQuantityLimitToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


    @GetMapping("export-material-update")
    public ResponseEntity<?> exportMaterialUpdateToExcel() {

        File excelFile = excelExporterService.exportMaterialUpdateToExcel();

        // Thiết lập các header cho HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excelFile.getName());

        // Tạo đối tượng interface Resource từ file Excel
        Resource resource = new FileSystemResource(excelFile);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
