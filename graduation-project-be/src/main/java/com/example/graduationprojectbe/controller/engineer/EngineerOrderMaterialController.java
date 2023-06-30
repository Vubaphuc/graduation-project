package com.example.graduationprojectbe.controller.engineer;

import com.example.graduationprojectbe.request.CreateOrderMaterialRequest;
import com.example.graduationprojectbe.request.UpdateOrderMaterialRequest;
import com.example.graduationprojectbe.service.engineer.EngineerOrderMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("engineer/api/v2")
public class EngineerOrderMaterialController {
    @Autowired
    private EngineerOrderMaterialService engineerOrderMaterialService;


    // danh sách vật liệu
    @GetMapping("materialies")
    public ResponseEntity<?> getListMaterialByQuantity(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(engineerOrderMaterialService.getListMaterialByQuantity(page,pageSize));
    }

    // lấy vật liệu theo id
    @GetMapping("material/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Integer id) {
        return ResponseEntity.ok(engineerOrderMaterialService.getMaterialById(id));
    }

    // tạo order
    @PostMapping("order")
    public ResponseEntity<?> CreateOrderMaterial (@RequestBody CreateOrderMaterialRequest request) {
        return ResponseEntity.ok(engineerOrderMaterialService.CreateOrderMaterial(request));
    }

    // lấy order theo id
    @GetMapping("order/{id}")
    public ResponseEntity<?> getOrderMaterialById (@PathVariable Integer id) {
        return ResponseEntity.ok(engineerOrderMaterialService.getOrderMaterialById(id));
    }

    // lấy danh sách order đã phê duyệt
    @GetMapping("order/status-true")
    public ResponseEntity<?> getListOrderMaterialByStatusTrue(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(engineerOrderMaterialService.getListOrderMaterialByStatusTrue(page,pageSize));
    }

    // lấy danh sách order chuaw phê duyệt
    @GetMapping("order/status-false")
    public ResponseEntity<?> getListOrderMaterialByStatusFalse(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(engineerOrderMaterialService.getListOrderMaterialByStatusFalse(page,pageSize));
    }

    // cập nhật số lượng order vật liệu
    @PutMapping("order/{id}")
    public ResponseEntity<?> updateQuantityOrderMaterialById(@RequestBody UpdateOrderMaterialRequest request , @PathVariable Integer id) {
        return ResponseEntity.ok(engineerOrderMaterialService.updateQuantityOrderMaterialById(request, id));
    }


    // hủy order theo id (status === false)
    @DeleteMapping("order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(engineerOrderMaterialService.deleteOrderById(id));
    }
}
