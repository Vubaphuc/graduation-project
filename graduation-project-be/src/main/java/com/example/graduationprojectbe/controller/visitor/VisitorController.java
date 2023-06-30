package com.example.graduationprojectbe.controller.visitor;

import com.example.graduationprojectbe.service.visitor.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    // tìm kiếm sản phẩm theo ime sản phẩm và số điện thoai khách hàng
    @GetMapping("search")
    public ResponseEntity<?> searchHistoryProductByImeProductOrPhoneNumber(@RequestParam String ime,
                                                                           @RequestParam String phoneNumber) {
        return ResponseEntity.ok(visitorService.searchHistoryProductByImeProductOrPhoneNumber(ime, phoneNumber));
    }

    // tìm kiếm bảo hành theo mã bảo hành
    @GetMapping("guarantee")
    public ResponseEntity<?> searchGuaranteeByGuaranteeCode(@RequestParam String ime,
                                                            @RequestParam String phoneNumber) {
        return ResponseEntity.ok(visitorService.searchGuaranteeByGuaranteeCode(ime, phoneNumber));
    }

}
