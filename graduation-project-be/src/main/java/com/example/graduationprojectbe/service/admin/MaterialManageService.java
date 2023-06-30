package com.example.graduationprojectbe.service.admin;

import com.example.graduationprojectbe.dto.dto.OrderMaterialDto;
import com.example.graduationprojectbe.dto.dto.TotalMaterialDto;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.MaterialProjection;
import com.example.graduationprojectbe.dto.projection.OrderMaterialProjection;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.repository.MaterialRepository;
import com.example.graduationprojectbe.repository.OrderMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MaterialManageService {
    @Autowired
    private OrderMaterialRepository orderMaterialRepository;
    @Autowired
    private MaterialRepository materialRepository;


    // danh sách order
    public PageDto findOrderMaterialsAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {

        // nếu startDate == null && endDate == null thì lấy tất cả
        if (startDate == null && endDate == null) {
            Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.findOrderMaterialsAll(PageRequest.of(page - 1, pageSize));
            return new PageDto(
                    orderMaterials.getNumber() + 1,
                    orderMaterials.getSize(),
                    orderMaterials.getTotalPages(),
                    (int) Math.ceil(orderMaterials.getTotalElements()),
                    orderMaterials.getContent()
            );
        }
        // nếu startDate == null && endDate != null lấy theo endDate
        if (startDate == null) {

            Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.findOrderMaterialsAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize),endDate, endDate.with(LocalTime.MAX));

            return new PageDto(
                    orderMaterials.getNumber() + 1,
                    orderMaterials.getSize(),
                    orderMaterials.getTotalPages(),
                    (int) Math.ceil(orderMaterials.getTotalElements()),
                    orderMaterials.getContent()
            );
        }
        // startDate != null && endDate == null => kiểm tra  startDate có vượt quá hiện taij không. nếu không lấy từ startDate tới hiện tại. nếu có thì báo lỗi
        if (endDate == null) {
            LocalDateTime today = LocalDateTime.now();
            if (startDate.isAfter(today)) {
                throw new BadRequestException("Invalid date range");
            }
            Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.findOrderMaterialsAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize), startDate, today.with(LocalTime.MAX));
            return new PageDto(
                    orderMaterials.getNumber() + 1,
                    orderMaterials.getSize(),
                    orderMaterials.getTotalPages(),
                    (int) Math.ceil(orderMaterials.getTotalElements()),
                    orderMaterials.getContent()
            );
        }

        // trường hợp còn lại
        Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.findOrderMaterialsAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX));
        return new PageDto(
                orderMaterials.getNumber() + 1,
                orderMaterials.getSize(),
                orderMaterials.getTotalPages(),
                (int) Math.ceil(orderMaterials.getTotalElements()),
                orderMaterials.getContent()
        );
    }

    public TotalMaterialDto totalPriceAndQuantityMaterial() {

        TotalMaterialDto totalMaterialDto = TotalMaterialDto.builder()
                .totalImportPrice(materialRepository.totalPriceMaterialImportQuantity())
                .totalExportPrice(materialRepository.totalPriceMaterialExportQuantity())
                .totalMonthExportPrice(orderMaterialRepository.totalPriceMaterialOrderThisMonth())
                .totalTodayExportPrice(orderMaterialRepository.totalPriceMaterialOrderToday())
                .totalImportQuantity(materialRepository.totalQuantityImportMaterial())
                .totalExportQuantity(materialRepository.totalQuantityExportMaterial())
                .totalToDayExportQuantity(orderMaterialRepository.totalQuantityExportMaterialToday())
                .totalMonthExportQuantity(orderMaterialRepository.totalQuantityExportMaterialThisMonth())
                .build();

        return totalMaterialDto;
    }


    // danh sách vật liêu
    public PageDto findMaterialsAll(Integer page, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate, String term) {
        // nếu startDate == null && endDate == null thì lấy tất cả
        if (startDate == null && endDate == null) {
            Page<MaterialProjection> materials = materialRepository.findMaterialsAll(PageRequest.of(page - 1, pageSize),term);
            return new PageDto(
                    materials.getNumber() + 1,
                    materials.getSize(),
                    materials.getTotalPages(),
                    (int) Math.ceil(materials.getTotalElements()),
                    materials.getContent()
            );
        }
        // nếu startDate == null && endDate != null lấy theo endDate
        if (startDate == null) {

            Page<MaterialProjection> materials = materialRepository.findMaterialsAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize),endDate, endDate.with(LocalTime.MAX),term);

            return new PageDto(
                    materials.getNumber() + 1,
                    materials.getSize(),
                    materials.getTotalPages(),
                    (int) Math.ceil(materials.getTotalElements()),
                    materials.getContent()
            );
        }
        // startDate != null && endDate == null => kiểm tra  startDate có vượt quá hiện taij không. nếu không lấy từ startDate tới hiện tại. nếu có thì báo lỗi
        if (endDate == null) {
            LocalDateTime today = LocalDateTime.now();
            if (startDate.isAfter(today)) {
                throw new BadRequestException("Invalid date range");
            }
            Page<MaterialProjection> materials = materialRepository.findMaterialsAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize), startDate, today.with(LocalTime.MAX),term);
            return new PageDto(
                    materials.getNumber() + 1,
                    materials.getSize(),
                    materials.getTotalPages(),
                    (int) Math.ceil(materials.getTotalElements()),
                    materials.getContent()
            );
        }
        // trường hợp còn lại
        Page<MaterialProjection> materials = materialRepository.findMaterialsAllByStartDateAndEndDate(PageRequest.of(page - 1, pageSize), startDate, endDate.with(LocalTime.MAX),term);
        return new PageDto(
                materials.getNumber() + 1,
                materials.getSize(),
                materials.getTotalPages(),
                (int) Math.ceil(materials.getTotalElements()),
                materials.getContent()
        );
    }

    // danh sách tổng số lượng export material theo từng mã vật liệu
    public PageDto findListTotalQuantityExportMaterialByMaterialCode(int page, int pageSize) {

        Page<OrderMaterialDto> orderMaterialInfos = orderMaterialRepository.findListTotalQuantityExportMaterialByMaterialCode(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                orderMaterialInfos.getNumber() + 1,
                orderMaterialInfos.getSize(),
                orderMaterialInfos.getTotalPages(),
                (int) Math.ceil(orderMaterialInfos.getTotalElements()),
                orderMaterialInfos.getContent()
        );
    }

    public List<OrderMaterialDto> findOrderMaterialLimit() {

        List<OrderMaterialDto> orders = orderMaterialRepository.findTotalQuantityExportMaterialByMaterialCode();

        return orders.subList(0,Math.min(orders.size(),10));
    }

    // danh sách material có số lượng giảm dần và lấy 10 cái đầu tiên
    public List<MaterialProjection> findMaterialRemainingQuantityLimit() {

        List<MaterialProjection> materials = materialRepository.findMaterialRemainingQuantityLimit();

        return materials.subList(0,Math.min(materials.size(),10));
    }
}
