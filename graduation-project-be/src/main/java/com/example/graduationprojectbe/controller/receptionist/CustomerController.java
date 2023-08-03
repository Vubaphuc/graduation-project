package com.example.graduationprojectbe.controller.receptionist;

import com.example.graduationprojectbe.request.CreateCustomerRequest;
import com.example.graduationprojectbe.request.UpdateCustomerRequest;
import com.example.graduationprojectbe.service.receptionist.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("receptionist/api/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // tạo khách hàng mới
    @PostMapping("create-customer")
    public ResponseEntity<?> createCustomer (@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    // lấy ra khàng hàng theo id
    @GetMapping("customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // lấy ra list product theo id khách hàng -  3
    @GetMapping("products")
    public ResponseEntity<?> getListProductByCustomerId(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam Integer id) {
        return ResponseEntity.ok(customerService.getListProductByCustomerId(page,pageSize,id));
    }

    // tìm kiếm sản phẩm có status = false theo từng khách hàng - 2
    @GetMapping("search/product")
    public ResponseEntity<?> searchProductByCustomer(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(customerService.searchProductByCustomer(page,pageSize,term));
    }

    // cập nhật thông tin nhân viên
    @PutMapping("customer/{id}")
    public ResponseEntity<?> updateCustomerById(@Valid @RequestBody UpdateCustomerRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(customerService.updateCustomerById(request, id));
    }
    // xóa khách hàng theo id
    @DeleteMapping("customer/{id}")
    public ResponseEntity<?> deleteCustomerById( @PathVariable Integer id) {
        return ResponseEntity.ok(customerService.deleteCustomerById(id));
    }


}
