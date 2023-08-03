package com.example.graduationprojectbe.controller.receptionist;

import com.example.graduationprojectbe.request.create.CreateBillAndGuaranteeRequest;
import com.example.graduationprojectbe.request.create.CreateProductRequest;
import com.example.graduationprojectbe.request.other.InformationEngineerRequest;
import com.example.graduationprojectbe.request.update.UpdateReceiptRequest;
import com.example.graduationprojectbe.service.receptionist.ProductService;
import com.itextpdf.text.DocumentException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("receptionist/api/v2")
public class ProductController {
    @Autowired
    private ProductService productService;

    // danh sách sản phẩm đã sửa chữa xong chờ trả khách
    @GetMapping("products-return-customer")
    public ResponseEntity<?> findProductWaitingReturnCustomerAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findProductWaitingReturnCustomerAll(page, pageSize,term));
    }

    // danh sách sản phẩm vừa sửa chữa xong chờ tạo bảo hành
    @GetMapping("product-repaired")
    public ResponseEntity<?> findProductRepairedAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findProductRepairedAll(page, pageSize,term));
    }

    // danh sách sản phẩm chờ chuyển người sửa chữa WAITING_FOR_REPAIR
    @GetMapping("product-waiting-repair")
    public ResponseEntity<?> findProductWaitingForRepairAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findProductWaitingForRepairAll(page, pageSize,term));
    }
    // lấy sản phẩm theo id
    @GetMapping("product/{id}")
    public ResponseEntity<?> findProductByID(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findProductByID(id));
    }

    // đăng ký nhân viên sủa chữa
    @PutMapping("register-engineer/{id}")
    public ResponseEntity<?> registerEngineerInformationByProduct(@Valid @RequestBody InformationEngineerRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(productService.registerEngineerInformationByProduct(request, id));
    }



    // danh sách sản phẩm đang sửa chữa (pending chỗ nhân viên sửa chữa)
    @GetMapping("product-under-repair")
    public ResponseEntity<?> findProductUnderRepairAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findProductUnderRepairAll(page, pageSize,term));
    }

    // tạo sản phẩm mới
    @PostMapping("create-product")
    public ResponseEntity<?> createProduct (@Valid @RequestBody CreateProductRequest requet) {
        return ResponseEntity.ok(productService.createProduct(requet));
    }

    // danh sách sản phẩm đang pending trong cửa hàng
    @GetMapping("product-pending")
    public ResponseEntity<?> findProductPendingAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findProductPendingAll(page, pageSize,term));
    }

    // xóa sản phẩm
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deletePorductBtID (@PathVariable Integer id) {
        return ResponseEntity.ok(productService.deletePorductBtID(id));
    }


    // đăng ký bảo hành cho sản phẩm
    @PostMapping("guarantee-create")
    public ResponseEntity<?> createNewGuarantee (@Valid @RequestBody CreateBillAndGuaranteeRequest request) {
        return ResponseEntity.ok(productService.createNewWarranty(request));
    }

    // tạo hóa đơn
    @PostMapping("create-bill")
    public ResponseEntity<?> createBill(@Valid @RequestBody CreateBillAndGuaranteeRequest request) throws DocumentException, IOException {
        return ResponseEntity.ok(productService.createBill(request));
    }

    // tạo biên lai thu nhận sản phẩm
    @PostMapping("receipt/{id}")
    public ResponseEntity<?> createReceipt (@PathVariable Integer id) throws DocumentException, IOException {
        return ResponseEntity.ok(productService.createReceipt(id));
    }

    @GetMapping("receipts")
    public ResponseEntity<?> findReceiptAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findReceiptAll(page, pageSize,term));
    }
    @GetMapping("receipts-no")
    public ResponseEntity<?> findReceiptStatusTrueAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findReceiptStatusTrueAll(page, pageSize,term));
    }

    @GetMapping("no-create-receipts")
    public ResponseEntity<?> findProductNoCreateReceiptAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(productService.findProductNoCreateReceiptAll(page, pageSize,term));
    }

    @GetMapping("receipt/{id}")
    public ResponseEntity<?> findReceiptById (@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findReceiptById(id));
    }

    @PutMapping("receipt/{id}")
    public ResponseEntity<?> updateReceiptById (@Valid @RequestBody UpdateReceiptRequest request , @PathVariable Integer id) {
        return ResponseEntity.ok(productService.updateReceiptById(request,id));
    }

}
