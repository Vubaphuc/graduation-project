package com.example.graduationprojectbe.controller.employee;

import com.example.graduationprojectbe.entity.Image;
import com.example.graduationprojectbe.request.ChangePasswordRequest;
import com.example.graduationprojectbe.request.ForgotPasswordRequest;
import com.example.graduationprojectbe.request.UpdatePersonalInformationRequest;
import com.example.graduationprojectbe.service.auth.EmailService;
import com.example.graduationprojectbe.service.employee.EmployeeService;
import com.itextpdf.text.DocumentException;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("employee/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //lấy thông tin nhân viên và admin
    @GetMapping("employee/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    // lấy danh sách nhân viên sửa chữa
    @GetMapping("engineer")
    public ResponseEntity<?> findEngineerAll() {
        return ResponseEntity.ok(employeeService.findEngineerAll());
    }
    // lấy danh sách nhân viên lễ tân
    @GetMapping("receptionist")
    public ResponseEntity<?> findReceptionistAll () {
        return ResponseEntity.ok(employeeService.findReceptionistAll());
    }
    // lấy danh sách nhân viên kho
    @GetMapping("warehouse-employee")
    public ResponseEntity<?> findWarehouseEmployeeAll () {
        return ResponseEntity.ok(employeeService.findWarehouseEmployeeAll());
    }
    // lấy danh sách nhân viên bảo hành
    @GetMapping("receive-pay")
    public ResponseEntity<?> findReceptionistAndWarrantyEmployeeAll() {
        return ResponseEntity.ok(employeeService.findReceptionistAndWarrantyEmployeeAll());
    }


    // quên mật khẩu
    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(employeeService.forgotPassword(request));
    }

    // thay đổi password
    @PutMapping("change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(employeeService.changePassword(request));
    }

    // cập nhật thông tin nhân viên
    @PutMapping("update-information")
    public ResponseEntity<?> updatePersonalInformation(@Valid @RequestBody UpdatePersonalInformationRequest request) {
        return ResponseEntity.ok(employeeService.updatePersonalInformation(request));
    }

    // thêm ảnh avatar
    @PostMapping("upload-avatar")
    public ResponseEntity<?> updateProfilePicture(@ModelAttribute("avatar") MultipartFile avatar) {
        return ResponseEntity.ok(employeeService.updateProfilePicture(avatar));
    }
    // lấy image theo id
    @GetMapping("avatar/{id}")
    public ResponseEntity<?> getAvatarById(@PathVariable Integer id) {
        Image image = employeeService.getAvatarById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());

    }

    @GetMapping("bill-pdf/{id}")
    public ResponseEntity<?> exportBillToPdf (@PathVariable Integer id) throws DocumentException, IOException {
            byte[] pdfBytes = employeeService.exportBillToPdf(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "bill.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @GetMapping("bill-guarantee-pdf/{id}")
    public ResponseEntity<?> exportBillGuaranteeToPdf (@PathVariable Integer id) throws DocumentException, IOException {

        byte[] pdfBytes = employeeService.exportBillGuaranteeToPdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "bill.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @GetMapping("receipt-pdf/{id}")
    public ResponseEntity<?> exportReceiptToPdf (@PathVariable Integer id) throws DocumentException, IOException {

        byte[] pdfBytes = employeeService.exportReceiptToPdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "receipt.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("receipt-guarantee-pdf/{id}")
    public ResponseEntity<?> exportReceiptGuaranteeToPdf (@PathVariable Integer id) throws DocumentException, IOException {

        byte[] pdfBytes = employeeService.exportReceiptGuaranteeToPdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "receipt.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<?> searchHistoryProduct (@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) LocalDateTime startDate,
                                                   @RequestParam(required = false) LocalDateTime endDate,
                                                   @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(employeeService.searchHistoryProduct(page,pageSize,startDate,endDate,term));
    }


    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(employeeService.getAllUsers());
    }

}
