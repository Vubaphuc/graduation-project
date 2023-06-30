package com.example.graduationprojectbe.controller.admin;

import com.example.graduationprojectbe.service.admin.MaterialManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequestMapping("admin/api/v2")
@Slf4j
public class MaterialManageController {
    @Autowired
    private MaterialManageService materialManageService;


    // danh sách order
    @GetMapping("order-materials")
    public ResponseEntity<?> findOrderMaterialsAll (@RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(required = false) LocalDateTime startDate,
                                                    @RequestParam(required = false) LocalDateTime endDate) {

        return ResponseEntity.ok(materialManageService.findOrderMaterialsAll(page,pageSize,startDate,endDate));
    }

    // tổng các loại vật liệu và tiền
    @GetMapping("total-material")
    public ResponseEntity<?> totalPriceAndQuantityMaterial() {
        return ResponseEntity.ok(materialManageService.totalPriceAndQuantityMaterial());
    }


    @GetMapping("materials")
    public ResponseEntity<?> findMaterialsAll (@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(required = false) LocalDateTime startDate,
                                               @RequestParam(required = false) LocalDateTime endDate,
                                               @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(materialManageService.findMaterialsAll(page,pageSize,startDate,endDate,term));
    }

    // danh sách tổng số lượng export material theo từng mã vật liệu
    @GetMapping("total-materials")
    public ResponseEntity<?> findListTotalQuantityExportMaterialByMaterialCode(@RequestParam(defaultValue = "1") Integer page,
                                                                               @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(materialManageService.findListTotalQuantityExportMaterialByMaterialCode(page,pageSize));
    }

    @GetMapping("order-materials-limit")
    public ResponseEntity<?> findOrderMaterialLimit() {
        return ResponseEntity.ok(materialManageService.findOrderMaterialLimit());
    }

    // top 10 vật liệu còn hàng nhiều nhất
    @GetMapping("materials-remaining-quantity")
    public ResponseEntity<?> findMaterialRemainingQuantityLimit() {
        return ResponseEntity.ok(materialManageService.findMaterialRemainingQuantityLimit());
    }

}
