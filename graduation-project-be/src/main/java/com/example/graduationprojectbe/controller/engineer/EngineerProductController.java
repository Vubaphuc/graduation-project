package com.example.graduationprojectbe.controller.engineer;

import com.example.graduationprojectbe.request.InformationRepairRequest;
import com.example.graduationprojectbe.service.engineer.EngineerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("engineer/api/v1")
public class EngineerProductController {
    @Autowired
    private EngineerProductService engineerProductService;


    // lấy danh sách sản phẩm đang sửa theo tên user
    @GetMapping("products")
    public ResponseEntity<?> getListProductNewByUser(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(engineerProductService.getListProductNewByUser(page,pageSize,term));
    }


    // lấy ra sản phẩm theo id
    @GetMapping("product/{id}")
    public ResponseEntity<?> getProductNewById(@PathVariable Integer id) {
        return ResponseEntity.ok(engineerProductService.getProductNewById(id));
    }

    // cập nhật thông tin sửa chữa
    @PutMapping("update-product/{id}")
    public ResponseEntity<?> updateInformationProductNewById(@RequestBody InformationRepairRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(engineerProductService.updateInformationProductNewById(request, id));
    }

    // lấy danh sách các loại linh kiện
    @GetMapping("components")
    public ResponseEntity<?> getListComponentPhone(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(engineerProductService.getListComponentPhone(page,pageSize));
    }


    // danh sách sản phẩm bảo hành theo user
    @GetMapping("product-guarantee")
    public ResponseEntity<?> findProductGuaranteeByUserAll(@RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int pageSize,
                                                           @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(engineerProductService.findProductGuaranteeByUserAll(page,pageSize,term));
    }


    // cập nhật thông tin sửa chữa sản phẩm bảo hành
    @PutMapping("update-product-guarantee/{id}")
    public ResponseEntity<?> updateInformationProductGuaranteeById(@RequestBody InformationRepairRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(engineerProductService.updateInformationProductGuaranteeById(request, id));
    }


    // lấy sản phẩm bảo hành theo id
    @GetMapping("product-guarantee/{id}")
    public ResponseEntity<?> findProductGuaranteeByID(@PathVariable Integer id) {
        return ResponseEntity.ok(engineerProductService.findProductGuaranteeByID(id));
    }


}
