package com.example.graduationprojectbe.controller.warrantyemployee;

import com.example.graduationprojectbe.request.create.CreateBillAndGuaranteeRequest;
import com.example.graduationprojectbe.request.other.InformationEngineerRequest;
import com.example.graduationprojectbe.request.other.RegisterProductGuaranteeRequest;
import com.example.graduationprojectbe.request.update.UpdateReceiptRequest;
import com.example.graduationprojectbe.service.warrantyemployee.WarrantyProductService;
import com.itextpdf.text.DocumentException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("warranty-employee/api/v1")
public class WarrantyProductController {
    @Autowired
    private WarrantyProductService warrantyProductService;


    // lấy danh sách sản phẩm new đã trả khàng
    @GetMapping("product-delivered")
    public ResponseEntity<?> findProductDeliveredAll(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int pageSize,
                                                       @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductDeliveredAll(page,pageSize,term));
    }

    // lấy sản phẩm new đã trả khách hàng theo id
    @GetMapping("product-delivered/{id}")
    public ResponseEntity<?> findProductDeliveredByID(@PathVariable Integer id) {
        return ResponseEntity.ok(warrantyProductService.findProductDeliveredByID(id));
    }

    // tạo mới sản phẩm bảo hành
    @PostMapping("product-guarantee/create")
    public ResponseEntity<?> createProductGuarantee (@Valid @RequestBody RegisterProductGuaranteeRequest request) {
        return ResponseEntity.ok(warrantyProductService.createProductGuarantee(request));
    }

    // lấy danh sách sản phẩm bảo hành chờ sửa
    @GetMapping("product-guarantee")
    public ResponseEntity<?> findProductGuaranteeWaitingForRepairAll(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductGuaranteeWaitingForRepairAll(page,pageSize,term));
    }

    // lấy sản phẩm bảo hành theo id
    @GetMapping("product-guarantee/{id}")
    public ResponseEntity<?> findProductGuaranteeByID(@PathVariable Integer id) {
        return ResponseEntity.ok(warrantyProductService.findProductGuaranteeByID(id));
    }

    // đăng ký nhân viên sủa chữa
    @PutMapping("register-engineer/{id}")
    public ResponseEntity<?> registerEngineerInformationByProductGuarantee(@Valid @RequestBody InformationEngineerRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(warrantyProductService.registerEngineerInformationByProductGuarantee(request, id));
    }

    // danh sách sản phẩm bảo hành đã sửa xong
    @GetMapping("product-guarantee-repaired")
    public ResponseEntity<?> findProductGuaranteeRepairedAll(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductGuaranteeRepairedAll(page, pageSize, term));
    }

    // đăng ký bảo hành cho sản phẩm bảo hành
    @PostMapping("guarantee-create")
    public ResponseEntity<?> createNewGuarantee (@Valid @RequestBody CreateBillAndGuaranteeRequest request) {
        return ResponseEntity.ok(warrantyProductService.createNewWarranty(request));
    }

    // tạo hóa đơn cho sản phẩm bảo hành
    @PostMapping("create-bill")
    public ResponseEntity<?> createBill(@Valid @RequestBody CreateBillAndGuaranteeRequest request) {
        return ResponseEntity.ok(warrantyProductService.createBill(request));
    }


    // lấy danh sách sản phẩm chờ trả khách
    @GetMapping("product-guarantee-return")
    public ResponseEntity<?> findProductGuaranteeWaitingForReturnAll(@RequestParam(defaultValue = "1") int page,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductGuaranteeWaitingForReturnAll(page, pageSize, term));
    }

    @GetMapping("product-guarantee-under-repair")
    public ResponseEntity<?> findProductGuaranteeUnderRepairAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductGuaranteeUnderRepairAll(page, pageSize,term));
    }

    // danh sách khách hàng có tổng số sản phẩm
    @GetMapping("customers")
    public ResponseEntity<?> findProductByCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductByCustomers(page,pageSize,term));
    }

    // tạo biên lai thu nhận sản phẩm
    @PostMapping("receipt/{id}")
    public ResponseEntity<?> createReceipt (@PathVariable Integer id) throws DocumentException, IOException {
        return ResponseEntity.ok(warrantyProductService.createReceipt(id));
    }

    @GetMapping("receipts")
    public ResponseEntity<?> findReceiptAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findReceiptAll(page, pageSize,term));
    }
    @GetMapping("receipts-no")
    public ResponseEntity<?> findReceiptStatusTrueAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findReceiptStatusTrueAll(page, pageSize,term));
    }

    @GetMapping("no-create-receipts")
    public ResponseEntity<?> findProductNoCreateReceiptAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warrantyProductService.findProductNoCreateReceiptAll(page, pageSize,term));
    }

    @GetMapping("receipt/{id}")
    public ResponseEntity<?> findReceiptById (@PathVariable Integer id) {
        return ResponseEntity.ok(warrantyProductService.findReceiptById(id));
    }

    @PutMapping("receipt/{id}")
    public ResponseEntity<?> updateReceiptById (@Valid @RequestBody UpdateReceiptRequest request , @PathVariable Integer id) {
        return ResponseEntity.ok(warrantyProductService.updateReceiptById(request,id));
    }
}
