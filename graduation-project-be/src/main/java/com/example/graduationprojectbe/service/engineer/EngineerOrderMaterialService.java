package com.example.graduationprojectbe.service.engineer;

import com.example.graduationprojectbe.config.GenerateCode;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.MaterialProjection;
import com.example.graduationprojectbe.dto.projection.OrderMaterialProjection;
import com.example.graduationprojectbe.entity.Components;
import com.example.graduationprojectbe.entity.Material;
import com.example.graduationprojectbe.entity.OrderMaterial;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.MaterialRepository;
import com.example.graduationprojectbe.repository.OrderMaterialRepository;
import com.example.graduationprojectbe.request.CreateOrderMaterialRequest;
import com.example.graduationprojectbe.request.UpdateOrderMaterialRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.crypto.Data;

@Service
public class EngineerOrderMaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private GenerateCode generateCode;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private OrderMaterialRepository orderMaterialRepository;



    // danh sách vật liệu
    public PageDto getListMaterialByQuantity(int page, int pageSize) {

        Page<MaterialProjection> materials = materialRepository.getListMaterialByQuantity(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                materials.getNumber() + 1,
                materials.getSize(),
                materials.getTotalPages(),
                (int) Math.ceil(materials.getTotalElements()),
                materials.getContent()
        );
    }

    // lấy vật liệu theo id
    public MaterialProjection getMaterialById(Integer id) {
        return materialRepository.getMaterialById(id).
                orElseThrow(() -> new NotFoundException("Not Found With Id: "+ id));
    }

    // tạo order
    public StatusResponse CreateOrderMaterial(CreateOrderMaterialRequest request) {
        // lấy vật liệu theo id
        Material material = materialRepository.findByCodeAndDeleteTrue(request.getMaterialCode())
                .orElseThrow(() -> new NotFoundException("Not Found with code : " + request.getMaterialCode()));
        // kiểm tra vật liệu còn hàng không
        if (material.getRemainingQuantity() <= 0) {
            throw new BadRequestException("material is no longer available");
        }
        // kiểm tra số lượng order có lớn hơn số lượng tồn kho không
        if (request.getQuantity() > material.getRemainingQuantity()) {
            throw new BadRequestException("order quantity exceeds stock quantity");
        }
        // tạo mới order
        OrderMaterial orderMaterial = OrderMaterial.builder()
                .orderCode(generateCode.generateCode())
                .quantity(request.getQuantity())
                .material(material)
                .orderer(iCurrentUserLmpl.getUser())
                .build();
        // lưu vào csdl
        orderMaterialRepository.save(orderMaterial);

        return new StatusResponse(HttpStatus.CREATED,
                "Create order material success",
                DataMapper.toDataResponse(orderMaterial.getId(), orderMaterial.getOrderCode()));
    }

    // lấy order theo id
    public OrderMaterialProjection getOrderMaterialById(Integer id) {
        return orderMaterialRepository.getOrderMaterialById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }

    // lấy danh sách order đã phê duyệt
    @GetMapping("order/status-true")
    public PageDto getListOrderMaterialByStatusTrue(int page, int pageSize) {

        Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.getListOrderMaterialByStatusTrue(PageRequest.of(page - 1, pageSize),iCurrentUserLmpl.getUser().getId());

        return new PageDto(
                orderMaterials.getNumber() + 1,
                orderMaterials.getSize(),
                orderMaterials.getTotalPages(),
                (int) Math.ceil(orderMaterials.getTotalElements()),
                orderMaterials.getContent()
        );
    }

    // lấy danh sách order chuaw phê duyệt
    public Object getListOrderMaterialByStatusFalse(int page, int pageSize) {

        Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.getListOrderMaterialByStatusFalse(PageRequest.of(page - 1, pageSize),iCurrentUserLmpl.getUser().getId());

        return new PageDto(
                orderMaterials.getNumber() + 1,
                orderMaterials.getSize(),
                orderMaterials.getTotalPages(),
                (int) Math.ceil(orderMaterials.getTotalElements()),
                orderMaterials.getContent()
        );
    }

    // cập nhật số lượng order vật liệu
    public StatusResponse updateQuantityOrderMaterialById(UpdateOrderMaterialRequest request, Integer id) {
        // lấy ra order vật liệu theo id
        OrderMaterial orderMaterial = orderMaterialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found with id: " + id));
        // kiểm tra order vật liệu đã được phê duyệt chưa
        if (orderMaterial.isStatus()) {
            throw new BadRequestException("Order approved successfully. Can't update");
        }
        // cập nhật lại thông tin order vật liệu
        orderMaterial.setQuantity(request.getQuantity());
        orderMaterialRepository.save(orderMaterial);


        return new StatusResponse(HttpStatus.OK,
                "Update successful",
                DataMapper.toDataResponse(orderMaterial.getId(), orderMaterial.getOrderCode()));
    }

    // hủy order theo id (status === false)
    public StatusResponse deleteOrderById(Integer id) {

        // lấy ra order vât liệu theo id
        OrderMaterial orderMaterial = orderMaterialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found with id: " + id));
        // kiểm tra order vật liệu đã được phê duyệt chưa
        if (orderMaterial.isStatus()) {
            throw new BadRequestException("Order approved successfully. Can't cancel");
        }
        // hủy order
        orderMaterial.setDelete(false);
        // lưu lại
        orderMaterialRepository.save(orderMaterial);

        return new StatusResponse(HttpStatus.NO_CONTENT,
                "Update successful",
                DataMapper.toDataResponse(orderMaterial.getId(), orderMaterial.getOrderCode()));
    }
}
