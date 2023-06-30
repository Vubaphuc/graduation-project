package com.example.graduationprojectbe.controller.warehouseemployee;

import com.example.graduationprojectbe.request.ApproveOrderMaterialRequest;
import com.example.graduationprojectbe.service.warehouseemployee.ApproverOrderMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("warehouse-employee/api/v2")
public class ApproverOrderMaterialController {
    @Autowired
    private ApproverOrderMaterialService approverOrderMaterialService;


    @GetMapping("order-pending")
    public ResponseEntity<?> getListOrderMaterialStatusFalse(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(approverOrderMaterialService.getListOrderMaterialStatusFalse(page,pageSize));
    }

    @GetMapping("order-ok")
    public ResponseEntity<?> getListOrderMaterialStatusTrue(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(approverOrderMaterialService.getListOrderMaterialStatusTrue(page,pageSize));
    }

    @GetMapping("order-material/{id}")
    public ResponseEntity<?> getOrderMaterialById (@PathVariable Integer id) {
        return ResponseEntity.ok(approverOrderMaterialService.getOrderMaterialById(id));
    }

    @PutMapping("app-order/{id}")
    public ResponseEntity<?> approveOrderMaterial (@RequestBody ApproveOrderMaterialRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(approverOrderMaterialService.approveOrderMaterial(request,id));
    }

    @GetMapping("search-term")
    public ResponseEntity<?> searchOrderMaterialByTerm(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,@RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(approverOrderMaterialService.searchOrderMaterialByTerm(page,pageSize,term));
    }
}
