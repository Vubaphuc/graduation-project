package com.example.graduationprojectbe.service.warehouseemployee;

import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.OrderMaterialProjection;
import com.example.graduationprojectbe.entity.Material;
import com.example.graduationprojectbe.entity.OrderMaterial;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.MaterialRepository;
import com.example.graduationprojectbe.repository.OrderMaterialRepository;
import com.example.graduationprojectbe.request.ApproveOrderMaterialRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ApproverOrderMaterialService {
    @Autowired
    private OrderMaterialRepository orderMaterialRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private MaterialRepository materialRepository;



    // danh sách order vật liệu có status = false - 1
    public PageDto getListOrderMaterialStatusFalse(int page, int pageSize) {

        // danh sách order material by status = false
        Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.getListOrderMaterialStatusFalse(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                orderMaterials.getNumber() + 1,
                orderMaterials.getSize(),
                orderMaterials.getTotalPages(),
                (int) Math.ceil(orderMaterials.getTotalElements()),
                orderMaterials.getContent()
        );
    }
    // danh sách order material by status = true và do user phê duyệt - 2
    public PageDto getListOrderMaterialStatusTrue(int page, int pageSize) {

        Page<OrderMaterialProjection> orderMaterials = orderMaterialRepository.getListOrderMaterialStatusTrue(PageRequest.of(page - 1, pageSize), iCurrentUserLmpl.getUser().getId());

        return new PageDto(
                orderMaterials.getNumber() + 1,
                orderMaterials.getSize(),
                orderMaterials.getTotalPages(),
                (int) Math.ceil(orderMaterials.getTotalElements()),
                orderMaterials.getContent()
        );
    }
    // lấy 1 order Material theo ID - 3
    public OrderMaterialProjection getOrderMaterialById(Integer id) {
        return orderMaterialRepository.getOrderMaterialById(id)
                .orElseThrow(() -> new NotFoundException("Not Found with id : " + id));
    }

    // phê duyệt order material cho nhân viên sửa chữa - 4
    public StatusResponse approveOrderMaterial(ApproveOrderMaterialRequest request, Integer id) {

        // lấy ra order material đang chờ phê duyệt theo
        OrderMaterial orderMaterial = orderMaterialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id : " + id));
        // lấy ra vật liệu theo code
        Material material = materialRepository.findByCodeAndDeleteTrue(request.getMaterialCode())
                .orElseThrow(() -> new NotFoundException("Not Found with id : " + id));
        // kiểm tra order đã phê duyệt chưa
        if (orderMaterial.getApprover() != null) {
            throw new BadRequestException("Order has been approved");
        }
        // chưa phê duyệt thì bắt đầu phê duyệt
        orderMaterial.setApprover(iCurrentUserLmpl.getUser());
        orderMaterial.setApprovalDate(LocalDateTime.now());
        orderMaterial.setStatus(request.isStatus());
        // lưu ;lại lên csdl
        orderMaterialRepository.save(orderMaterial);
        // trừ số lượng vật liệu trong kho
        material.setExportQuantity(material.getExportQuantity() + request.getQuantity());
        // lưu lại vật liệu lên csdl
        materialRepository.save(material);


        return new StatusResponse(HttpStatus.OK,
                "approval success",
                DataMapper.toDataResponse(orderMaterial.getId(), orderMaterial.getOrderCode()));
    }
    // tìm kiếm order material theo term - 5
    public PageDto searchOrderMaterialByTerm(int page, int pageSize, String term) {

        // kiểm tra term rỗng hoặc null không
        if (term == null || term.trim().isEmpty()) {
            return new PageDto(
                    0,0,0,0,new ArrayList<>()
            );
        }

        Page<OrderMaterialProjection> orderMaterialDtos = orderMaterialRepository.searchOrderMaterialByTerm(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                orderMaterialDtos.getNumber() + 1,
                orderMaterialDtos.getSize(),
                orderMaterialDtos.getTotalPages(),
                (int) Math.ceil(orderMaterialDtos.getTotalElements()),
                orderMaterialDtos.getContent()
        );
    }
}
