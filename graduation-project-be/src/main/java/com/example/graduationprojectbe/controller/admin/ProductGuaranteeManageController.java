package com.example.graduationprojectbe.controller.admin;

import com.example.graduationprojectbe.service.admin.ProductGuaranteeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("admin/api/v4")
public class ProductGuaranteeManageController {
    @Autowired
    private ProductGuaranteeManageService productGuaranteeManageService;


    // danh sách sản phẩm theo chờ sửa chữa
    @GetMapping("product-for-reapir")
    public ResponseEntity<?> findProductGuaranteeWaitingForRepairAll(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                                            @RequestParam(required = false) LocalDateTime startDate,
                                                            @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productGuaranteeManageService.findProductGuaranteeWaitingForRepairAll(page,pageSize,startDate,endDate));
    }

    // danh sách sản phẩm đang sửa chữa
    @GetMapping("product-under-reapir")
    public ResponseEntity<?> findProductGuaranteeUnderRepairAll(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false) LocalDateTime startDate,
                                                       @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productGuaranteeManageService.findProductGuaranteeUnderRepairAll(page,pageSize,startDate,endDate));
    }

    // danh sách sản phẩm đã sửa xong
    @GetMapping("product-repaired")
    public ResponseEntity<?> findProductGuaranteeRepairedAll(@RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(required = false) LocalDateTime startDate,
                                                    @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productGuaranteeManageService.findProductGuaranteeRepairedAll(page,pageSize,startDate,endDate));
    }

    // danh sách sanr phẩm chờ trả khách
    @GetMapping("product-for-return")
    public ResponseEntity<?> findProductGuaranteeWaitingForReturnAll(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                                            @RequestParam(required = false) LocalDateTime startDate,
                                                            @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productGuaranteeManageService.findProductGuaranteeWaitingForReturnAll(page,pageSize,startDate,endDate));
    }

    // danh sách sản phẩm đã trả khách
    @GetMapping("product-delivered")
    public ResponseEntity<?> findProductGuaranteeDeliveredAll(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                                     @RequestParam(required = false) LocalDateTime startDate,
                                                     @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productGuaranteeManageService.findProductGuaranteeDeliveredAll(page,pageSize,startDate,endDate));
    }
}
