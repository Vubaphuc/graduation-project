package com.example.graduationprojectbe.controller.warehouseemployee;

import com.example.graduationprojectbe.request.create.CreateComponentsRequest;
import com.example.graduationprojectbe.request.create.CreateMaterialRequest;
import com.example.graduationprojectbe.request.create.CreateVendorRequest;
import com.example.graduationprojectbe.request.update.UpdateComponentRequest;
import com.example.graduationprojectbe.request.update.UpdateMaterialRequest;
import com.example.graduationprojectbe.service.warehouseemployee.WarehouseEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("warehouse-employee/api/v1")
public class WarehouseEmployeeController {
    @Autowired
    private WarehouseEmployeeService warehouseEmployeeService;


    @GetMapping("components")
    public ResponseEntity<?> getListComponents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(warehouseEmployeeService.getListComponents(page,pageSize));
    }
    @GetMapping("component/{id}")
    public ResponseEntity<?> getComponentsById(@PathVariable Integer id) {
        return ResponseEntity.ok(warehouseEmployeeService.getComponentsById(id));
    }

    @PostMapping("components/create")
    public ResponseEntity<?> createComponents (@Valid @RequestBody CreateComponentsRequest request) {
        return ResponseEntity.ok(warehouseEmployeeService.createComponents(request));
    }

    @PutMapping("uppdate-component/{id}")
    public ResponseEntity<?> updateComponentById (@Valid @RequestBody UpdateComponentRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(warehouseEmployeeService.updateComponentById(request, id));
    }

    @PostMapping("material/create")
    public ResponseEntity<?> createMaterial (@Valid @RequestBody CreateMaterialRequest request) {
        return ResponseEntity.ok(warehouseEmployeeService.createMaterial(request));
    }
    // cập nhật số lượng vật liệu
    @PutMapping("material/{id}")
    public ResponseEntity<?> updateMaterialById (@Valid @RequestBody UpdateMaterialRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(warehouseEmployeeService.updateMaterialById(request,id));
    }


    @GetMapping("materilies")
    public ResponseEntity<?> getListMaterialAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(warehouseEmployeeService.getListMaterialAll(page,pageSize));
    }

    @GetMapping("history")
    public ResponseEntity<?> searchHistoryMaterial(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(warehouseEmployeeService.searchHistoryMaterial(page,pageSize,term));
    }


    @GetMapping("vendories")
    public ResponseEntity<?> getListVendorAll (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(warehouseEmployeeService.getListVendorAll(page,pageSize));
    }

    // Tạo mới 1 vender
    @PostMapping("vendor/create")
    public ResponseEntity<?> createVendor (@Valid @RequestBody CreateVendorRequest request) {
        return ResponseEntity.ok(warehouseEmployeeService.createVendor(request));
    }

    // lấy 1 vender theo id
    @GetMapping("vendor/{id}")
    public ResponseEntity<?> getVendorById (@PathVariable Integer id) {
        return ResponseEntity.ok(warehouseEmployeeService.getVendorById(id));
    }

    // lấy 1 vender theo tên
    @GetMapping("search-vendor/{name}")
    public ResponseEntity<?> getVendorByName (@PathVariable String name) {
        return ResponseEntity.ok(warehouseEmployeeService.getVendorByName(name));
    }

    // sửa tên vender
    @PutMapping("update-vendor/{id}")
    public ResponseEntity<?> updateNameVendor (@Valid @RequestBody CreateVendorRequest request ,@PathVariable Integer id) {
        return ResponseEntity.ok(warehouseEmployeeService.updateNameVendor(request,id));
    }

    @GetMapping("warehouse")
    public ResponseEntity<?> getListVendorTotalMaterial(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(warehouseEmployeeService.getListVendorTotalMaterial(page,pageSize));
    }

    @GetMapping("vendor/detail")
    public ResponseEntity<?> getListVendorById(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,@RequestParam int vendorId) {
        return ResponseEntity.ok(warehouseEmployeeService.getListVendorById(page,pageSize,vendorId));
    }

    @GetMapping("material/{id}")
    public ResponseEntity<?> getMaterialById (@PathVariable Integer id) {
        return ResponseEntity.ok(warehouseEmployeeService.getMaterialById(id));
    }
}
