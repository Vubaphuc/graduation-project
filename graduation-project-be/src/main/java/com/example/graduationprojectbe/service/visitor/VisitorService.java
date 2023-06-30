package com.example.graduationprojectbe.service.visitor;

import com.example.graduationprojectbe.dto.projection.CustomerSearchInfo;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.dto.projection.VisitorSearchInfo;
import com.example.graduationprojectbe.entity.Product;
import com.example.graduationprojectbe.entity.ProductGuarantee;
import com.example.graduationprojectbe.repository.ProductGuaranteeRepository;
import com.example.graduationprojectbe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductGuaranteeRepository productGuaranteeRepository;

    // tìm kiếm sản phẩm theo ime sản phẩm và số điện thoai khách hàng
    public List<CustomerSearchInfo> searchHistoryProductByImeProductOrPhoneNumber(String ime, String phoneNumber) {
        if (ime.isEmpty() && phoneNumber.isEmpty()) {
            return new ArrayList<>();
        }
        return productRepository.searchHistoryProductByImeProductOrPhoneNumber(ime, phoneNumber);
    }

    // tìm kiếm bảo hành theo mã bảo hành
    public List<VisitorSearchInfo> searchGuaranteeByGuaranteeCode(String ime, String phoneNumber) {
        if (ime.isEmpty() && phoneNumber.isEmpty()) {
            return new ArrayList<>();
        }
        return productGuaranteeRepository.searchGuaranteeByGuaranteeCode(ime, phoneNumber);
    }
}
