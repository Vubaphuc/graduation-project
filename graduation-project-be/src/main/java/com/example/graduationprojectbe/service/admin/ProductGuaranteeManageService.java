package com.example.graduationprojectbe.service.admin;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.ProductGuaranteeProjection;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.repository.ProductGuaranteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class ProductGuaranteeManageService {
    @Autowired
    private ProductGuaranteeRepository productGuaranteeRepository;





    // danh sách sản phẩm theo chờ sửa chữa
    public PageDto findProductGuaranteeWaitingForRepairAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate == null && endDate == null) {

            Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.WAITING_FOR_REPAIR);

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


        Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeWaitingForRepairAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.WAITING_FOR_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sản phẩm đang sửa chữa
    public PageDto findProductGuaranteeUnderRepairAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.UNDER_REPAIR);

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

        Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeUnderRepairAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.UNDER_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sản phẩm đã sửa xong
    public PageDto findProductGuaranteeRepairedAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.REPAIRED);

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

        Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeRepairedAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.REPAIRED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sanr phẩm chờ trả khách
    public PageDto findProductGuaranteeWaitingForReturnAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.WAITING_FOR_RETURN);

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

        Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeWaitingForReturnAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.WAITING_FOR_RETURN);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }


    // danh sách sản phẩm đã trả khách
    public PageDto findProductGuaranteeDeliveredAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {

            Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeAllByStartDateAndEndDateIsNull_ADMIN(PageRequest.of(page - 1, pageSize), Status.DELIVERED);

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


        Page<ProductGuaranteeProjection> products = productGuaranteeRepository.findProductGuaranteeDeliveredAll_ADMIN(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX), Status.DELIVERED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }
}
