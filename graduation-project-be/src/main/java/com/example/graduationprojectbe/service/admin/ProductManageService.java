package com.example.graduationprojectbe.service.admin;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.dto.*;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.CustomerRepository;
import com.example.graduationprojectbe.repository.ProductRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManageService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;



    // lấy tổng sản phẩm ngày hôm nay
    public ProductSummaryDto findStatisticsTotalProductToday() {
        return productRepository.findStatisticsTotalProductToday(Status.DELIVERED);
    }

    // lấy tổng sản phẩm theo từng nhân viên sửa chữa
    public List<TotalProductDto> findTotalProductByEngineerAll() {

        LocalDate today = LocalDate.now();

        return userRepository.findEmployeeEngineerAll()
                .stream()
                .map(user -> {
                    long totalOK = productRepository.countTotalProductOKByEmployeeCode(user.getEmployeeCode(), today);
                    long totalPending = productRepository.countTotalProductPendingByEmployeeCode(user.getEmployeeCode(), Status.UNDER_REPAIR);
                    return DataMapper.toTotalProductDto(user,totalOK,totalPending);
                }).collect(Collectors.toList());
    }

    public List<TotalProductDto> findTotalProductByEngineerYesterdayAll() {

        LocalDate previousDate = LocalDate.now().minusDays(1);

        return userRepository.findEmployeeEngineerAll()
                .stream()
                .map(user -> {
                    long totalOK = productRepository.countTotalProductOKYesterdayByEmployeeCode(user.getEmployeeCode(), previousDate);
                    long totalPending = productRepository.countTotalProductPendingYesterdayByEmployeeCode(user.getEmployeeCode(),Status.UNDER_REPAIR);
                    return DataMapper.toTotalProductDto(user,totalOK,totalPending);
                }).collect(Collectors.toList());
    }

    // lấy tất cả danh sách Sản Phẩm OK hôm nay
    public PageDto findProductOKAll(Integer page, Integer pageSize) {

        Page<ProductProjection> products = productRepository.findProductOKAll_ADMIN(PageRequest.of(page - 1, pageSize), Status.REPAIRED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // lấy tất cả danh sách Sản Phẩm Pending
    public PageDto findProductPendingAll(Integer page, Integer pageSize) {

        Page<ProductProjection> products = productRepository.findProductPendingAll_ADMIN(PageRequest.of(page - 1, pageSize), Status.UNDER_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // lấy tất cả danh sách Sản Phẩm chờ trả khách
    public PageDto findProductWaitingForReturnAll(Integer page, Integer pageSize) {

        Page<ProductProjection> products = productRepository.findProductWaitingForReturnAll(PageRequest.of(page - 1, pageSize), Status.WAITING_FOR_RETURN);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }


    // danh sách sản phẩm theo chờ sửa chữa
    public PageDto findProductsWaitingForRepairAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate == null && endDate == null) {

            Page<ProductProjection> products = productRepository.findProductAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.WAITING_FOR_REPAIR);

            return new PageDto(
                    products.getNumber() + 1,
                    products.getSize(),
                    products.getTotalPages(),
                    (int) Math.ceil(products.getTotalElements()),
                    products.getContent()
            );
        }

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new PageDto(0, 0, 0, 0, new ArrayList<>());
        }


        Page<ProductProjection> products = productRepository.findProductWaitingForRepairAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.WAITING_FOR_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sản phẩm đang sửa chữa
    public PageDto findProductUnderRepairAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductProjection> products = productRepository.findProductAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.UNDER_REPAIR);

            return new PageDto(
                    products.getNumber() + 1,
                    products.getSize(),
                    products.getTotalPages(),
                    (int) Math.ceil(products.getTotalElements()),
                    products.getContent()
            );
        }

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new PageDto(0, 0, 0, 0, new ArrayList<>());
        }

        Page<ProductProjection> products = productRepository.findProductUnderRepairAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.UNDER_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sản phẩm đã sửa xong
    public PageDto findProductRepairedAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductProjection> products = productRepository.findProductAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.REPAIRED);

            return new PageDto(
                    products.getNumber() + 1,
                    products.getSize(),
                    products.getTotalPages(),
                    (int) Math.ceil(products.getTotalElements()),
                    products.getContent()
            );
        }

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new PageDto(0, 0, 0, 0, new ArrayList<>());
        }

        Page<ProductProjection> products = productRepository.findProductRepairedAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.REPAIRED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sanr phẩm chờ trả khách
    public PageDto findProductWaitingForReturnAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductProjection> products = productRepository.findProductAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.WAITING_FOR_RETURN);

            return new PageDto(
                    products.getNumber() + 1,
                    products.getSize(),
                    products.getTotalPages(),
                    (int) Math.ceil(products.getTotalElements()),
                    products.getContent()
            );
        }

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new PageDto(0, 0, 0, 0, new ArrayList<>());
        }

        Page<ProductProjection> products = productRepository.findProductWaitingForReturnAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.WAITING_FOR_RETURN);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }


    // danh sách sản phẩm đã trả khách
    public PageDto findProductDeliveredAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductProjection> products = productRepository.findProductAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.DELIVERED);

            return new PageDto(
                    products.getNumber() + 1,
                    products.getSize(),
                    products.getTotalPages(),
                    (int) Math.ceil(products.getTotalElements()),
                    products.getContent()
            );
        }

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new PageDto(0, 0, 0, 0, new ArrayList<>());
        }


        Page<ProductProjection> products = productRepository.findProductDeliveredAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.DELIVERED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // lấy toàn bo danh sach san pham
    public PageDto findProductAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate == null && endDate == null) {

            Page<ProductProjection> products = productRepository.findProductAll(PageRequest.of(page - 1, pageSize));

            return new PageDto(
                    products.getNumber() + 1,
                    products.getSize(),
                    products.getTotalPages(),
                    (int) Math.ceil(products.getTotalElements()),
                    products.getContent()
            );
        }

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return new PageDto(0, 0, 0, 0, new ArrayList<>());
        }


        Page<ProductProjection> products = productRepository.findProductAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX));

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }


    // top 10 mode sản phẩm hay sửa nhất;
    public List<ProductNameModelLimitDto> findProductByNameModelLimit() {
        List<ProductNameModelLimitDto> products = productRepository.findProductByNameModelLimit();
        return products.subList(0,Math.min(products.size(),10));
    }

    public List<CustomerDto> findCustomerByProductLimit() {
        List<CustomerDto> customers = customerRepository.productByCustomerAll();
        return customers.subList(0,Math.min(customers.size(),10));
    }

    public ProductTotalOkAndPendingDto findTotalProductOkAndPending() {
        return productRepository.findTotalProductOkAndPending(Status.DELIVERED);
    }

    public List<TotalProductByEngineerDto> findTotalProductByEngineer() {
        List<TotalProductByEngineerDto> products = productRepository.findTotalProductByEngineer(Status.DELIVERED);
        return products.subList(0,Math.min(products.size(),10));
    }
}
