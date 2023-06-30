package com.example.graduationprojectbe.controller.admin;

import com.example.graduationprojectbe.service.admin.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("admin/api/v3")
public class ProductManageController {
    @Autowired
    private ProductManageService productManageService;

    @GetMapping("products-total")
    public ResponseEntity<?> findStatisticsTotalProductToday () {
        return ResponseEntity.ok(productManageService.findStatisticsTotalProductToday());
    }

    // lấy tổng sản phẩm theo từng nhân viên sửa chữa
    @GetMapping("products-engineer")
    public ResponseEntity<?> findTotalProductByEngineerAll() {
        return ResponseEntity.ok(productManageService.findTotalProductByEngineerAll());
    }

    // lấy tổng sản phẩm theo từng nhân viên sửa chữa ngày hôm trước
    @GetMapping("products-engineer-Yesterday")
    public ResponseEntity<?> findTotalProductByEngineerYesterdayAll() {
        return ResponseEntity.ok(productManageService.findTotalProductByEngineerYesterdayAll());
    }


    // lấy tất cả danh sách Sản Phẩm OK hôm nay
    @GetMapping("products-ok")
    public ResponseEntity<?> findProductOKAll(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(productManageService.findProductOKAll(page,pageSize));
    }

    // lấy tất cả danh sách Sản Phẩm Pending
    @GetMapping("products-pending")
    public ResponseEntity<?> findProductPendingAll(@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(productManageService.findProductPendingAll(page,pageSize));
    }


    // danh sách sản phẩm theo chờ sửa chữa
    @GetMapping("product-for-reapir")
    public ResponseEntity<?> findProductWaitingForRepairAll(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                                            @RequestParam(required = false) LocalDateTime startDate,
                                                            @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productManageService.findProductWaitingForRepairAll(page,pageSize,startDate,endDate));
    }

    // danh sách sản phẩm đang sửa chữa
    @GetMapping("product-under-reapir")
    public ResponseEntity<?> findProductUnderRepairAll(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false) LocalDateTime startDate,
                                                       @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productManageService.findProductUnderRepairAll(page,pageSize,startDate,endDate));
    }

    // danh sách sản phẩm đã sửa xong
    @GetMapping("product-repaired")
    public ResponseEntity<?> findProductRepairedAll(@RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(required = false) LocalDateTime startDate,
                                                    @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productManageService.findProductRepairedAll(page,pageSize,startDate,endDate));
    }

    // danh sách sanr phẩm chờ trả khách
    @GetMapping("product-for-return")
    public ResponseEntity<?> findProductWaitingForReturnAll(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                                            @RequestParam(required = false) LocalDateTime startDate,
                                                            @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productManageService.findProductWaitingForReturnAll(page,pageSize,startDate,endDate));
    }

    // danh sách sản phẩm đã trả khách
    @GetMapping("product-delivered")
    public ResponseEntity<?> findProductDeliveredAll(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                                     @RequestParam(required = false) LocalDateTime startDate,
                                                     @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productManageService.findProductDeliveredAll(page,pageSize,startDate,endDate));
    }

    // lấy toàn bộ danh sách sản phẩm
    @GetMapping("products")
    public ResponseEntity<?> findProductAll(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                                     @RequestParam(required = false) LocalDateTime startDate,
                                                     @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(productManageService.findProductAll(page,pageSize,startDate,endDate));
    }


    // top 10 mode sản phẩm hay sửa nhất;
    @GetMapping("product-limit")
    public ResponseEntity<?> findProductByNameModelLimit () {
        return ResponseEntity.ok(productManageService.findProductByNameModelLimit());
    }

    // top 10 khách hàng có sản phẩm nhiều nhât
    @GetMapping("customer-limit")
    public ResponseEntity<?> findCustomerByProductLimit () {
        return ResponseEntity.ok(productManageService.findCustomerByProductLimit());
    }

    @GetMapping("total-product")
    public ResponseEntity<?> findTotalProductOkAndPending () {
        return ResponseEntity.ok(productManageService.findTotalProductOkAndPending());
    }

    @GetMapping("total-product-engineer")
    public ResponseEntity<?> findTotalProductByEngineer () {
        return ResponseEntity.ok(productManageService.findTotalProductByEngineer());
    }
}
