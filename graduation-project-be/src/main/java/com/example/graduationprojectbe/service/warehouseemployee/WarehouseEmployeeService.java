package com.example.graduationprojectbe.service.warehouseemployee;

import com.example.graduationprojectbe.dto.dto.VendorCountDto;
import com.example.graduationprojectbe.dto.dto.VendorTotalMaterialDto;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.*;
import com.example.graduationprojectbe.entity.Components;
import com.example.graduationprojectbe.entity.Material;
import com.example.graduationprojectbe.entity.UpdateMaterial;
import com.example.graduationprojectbe.entity.Vendor;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.ComponentsRepository;
import com.example.graduationprojectbe.repository.MaterialRepository;
import com.example.graduationprojectbe.repository.UpdateMaterialRepository;
import com.example.graduationprojectbe.repository.VendorRepository;
import com.example.graduationprojectbe.request.create.CreateComponentsRequest;
import com.example.graduationprojectbe.request.create.CreateMaterialRequest;
import com.example.graduationprojectbe.request.create.CreateVendorRequest;
import com.example.graduationprojectbe.request.update.UpdateComponentRequest;
import com.example.graduationprojectbe.request.update.UpdateMaterialRequest;
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
public class WarehouseEmployeeService {
    @Autowired
    private ComponentsRepository componentsRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private UpdateMaterialRepository updateMaterialRepository;


    // lấy ra danh sách Components có phân trang - 1
    public PageDto getListComponents(int page, int pageSize) {

        Page<ComponentProjection> components = componentsRepository.getListComponentPhone(PageRequest.of(page - 1 ,pageSize));

        return new PageDto(
                components.getNumber() + 1,
                components.getSize(),
                components.getTotalPages(),
                (int) Math.ceil(components.getTotalElements()),
                components.getContent()
        );
    }
    // lấy ra linh kiện theo id - 2
    public ComponentProjection getComponentsById(Integer id) {
        return componentsRepository.getComponentsById(id)
                .orElseThrow(() -> new NotFoundException("Not Found with id: " + id));
    }
    // tạo mới Components - 3
    public StatusResponse createComponents(CreateComponentsRequest request) {
        // kiểm tra xem components đã tồn tại hãy chưa
        if (componentsRepository.findByName(request.getName()).isPresent()) {
            throw new BadRequestException("Components already exist");
        }
        // tạo 1 componentss mới
        Components components = Components.builder()
                .name(request.getName())
                .warrantyPeriod(request.getWarrantyPeriod())
                .warehouseEmployee(iCurrentUserLmpl.getUser())
                .build();
        // lưu vào csdl
        componentsRepository.save(components);

        return new StatusResponse(HttpStatus.CREATED,
                "Create Components success" ,
                DataMapper.toDataResponse(components.getId(), components.getName()));
    }

    // cập nhật component by id
    public StatusResponse updateComponentById(UpdateComponentRequest request, Integer id) {
        Components components = componentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
        if (componentsRepository.findByName(request.getName()).isPresent()) {
            throw new BadRequestException("Component name already exists");
        }
        components.setName(request.getName());
        components.setWarrantyPeriod(request.getWarrantyPeriod());

        componentsRepository.save(components);

        return new StatusResponse(HttpStatus.OK,
                "Update Component success",
                DataMapper.toDataResponse(components.getId(), components.getName()));
    }

    // tạo mới Material - 4
    public StatusResponse createMaterial(CreateMaterialRequest request) {
        // kiểm tra xem material đã tồn tại hay chưa
        if (materialRepository.findByCodeAndDeleteTrue(request.getMaterialCode()).isPresent()) {

            // nếu tồn tại rồi lấy ra set lại số lương (quantity cũ + quantity mới)
            Material material = materialRepository.findByCodeAndDeleteTrue(request.getMaterialCode()).get();
            material.setImportQuantity(material.getImportQuantity() + request.getQuantity());
            // lưu lại vào csdl
            materialRepository.save(material);


            return new StatusResponse(HttpStatus.OK,
                    "Update quantity Material success" ,
                    DataMapper.toDataResponse(material.getId(), material.getCode()));

        }
        // lấy ra Components theo ComponentsId - 5
        Components components = componentsRepository.findById(request.getComponentsId())
                .orElseThrow(() -> new NotFoundException("Not Found with id : " + request.getComponentsId()));
        // lấy ra Vendor theo VendorId
        Vendor vendor = vendorRepository.findById(request.getVenderId())
                .orElseThrow(() -> new NotFoundException("Not Found with id : " + request.getVenderId()));

        // tạo Material mới
        Material material = Material.builder()
                .code(request.getMaterialCode())
                .nameModel(request.getNameModel())
                .importQuantity(request.getQuantity())
                .price(request.getPrice())
                .components(components)
                .vendor(vendor)
                .warehouseEmployee(iCurrentUserLmpl.getUser())
                .build();
        // lưu vvào ccsdl
        materialRepository.save(material);

        return new StatusResponse(HttpStatus.CREATED,
                "Create Material success" ,
                DataMapper.toDataResponse(material.getId(), material.getNameModel()));
    }

    // cập nhật số lượng vật liệu
    public StatusResponse updateMaterialById(UpdateMaterialRequest request, Integer id) {
        // lấy ra vật liệu theo id
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
        //kiểm tra số lượng nhập vào có lớn hơn 0 không
        if (request.getQuantity() <= 0 ) {
            throw new BadRequestException("quantity must be greater than 0");
        }
        // tạo thông tin cập nhật
        UpdateMaterial updateMaterial = UpdateMaterial.builder()
                .quantity(request.getQuantity())
                .updateDate(LocalDateTime.now())
                .material(material)
                .employeeUpdate(iCurrentUserLmpl.getUser())
                .build();
        updateMaterialRepository.save(updateMaterial);
        // cập nhât số lượng
        material.setImportQuantity(material.getImportQuantity() + request.getQuantity());
        // lưu lại
        materialRepository.save(material);

        return new StatusResponse(HttpStatus.OK,
                "Update quantity Material success" ,
                DataMapper.toDataResponse(material.getId(), material.getCode()));
    }

    // lấy ra danh sách material all có phân trang - 6
    public PageDto getListMaterialAll(int page, int pageSize) {

        Page<MaterialProjection> materials = materialRepository.getListMaterialAll(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                materials.getNumber() + 1,
                materials.getSize(),
                materials.getTotalPages(),
                (int) Math.ceil(materials.getTotalElements()),
                materials.getContent()
        );
    }

    // tìm kiếm Material theo Term c phân trang - 7
    public PageDto searchHistoryMaterial(int page, int pageSize, String term) {

        if (term == null || term.trim().isEmpty()) {
            return new PageDto<>(
                    0,0,0,0,new ArrayList<>()
            );
        }

        Page<MaterialProjection> materials = materialRepository.searchHistoryMaterial(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                materials.getNumber() + 1,
                materials.getSize(),
                materials.getTotalPages(),
                (int) Math.ceil(materials.getTotalElements()),
                materials.getContent()
        );
    }


    // lấy danh vendor all có phân trang - 8
    public PageDto getListVendorAll(int page, int pageSize) {

        Page<VendorCountDto> vendors = vendorRepository.getListVendorAll(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                vendors.getNumber() + 1,
                vendors.getSize(),
                vendors.getTotalPages(),
                (int) Math.ceil(vendors.getTotalElements()),
                vendors.getContent()
        );
    }

    // tạo mới vendor - 9
    public StatusResponse createVendor(CreateVendorRequest request) {
        // kiểm tra vendor đã tồn tại chưa
        if (vendorRepository.findByName(request.getName()).isPresent()) {
            throw new BadRequestException("Vendor already exists");
        }
        // tạo vendor mới
        Vendor vendor = Vendor.builder()
                .name(request.getName())
                .warehouseEmployee(iCurrentUserLmpl.getUser())
                .build();
        // lưu vào csdl
        vendorRepository.save(vendor);

        return new StatusResponse(HttpStatus.OK,
                "Create Vendor success" ,
                DataMapper.toDataResponse(vendor.getId(), vendor.getName()));
    }
    // lấy ra vendor theo id - 10
    public VendorInfo getVendorById(Integer id) {
        return vendorRepository.getVendorById(id).orElseThrow(() -> {
            throw new NotFoundException("Not Found With id : " + id);
        });
    }
    // lấy ra vendor theo name - 11
    public VendorInfo getVendorByName(String name) {
        return vendorRepository.getVendorByName(name).orElseThrow(() -> {
            throw new NotFoundException("Not Found With name : " + name);
        });
    }
    // update lại name vendor -- 12
    public StatusResponse updateNameVendor(CreateVendorRequest request, Integer id) {
        // kiểm tra vendor đã tồn tại chưa
        if (vendorRepository.findByName(request.getName()).isPresent()) {
            throw new BadRequestException("Vendor already exists");
        }
        // lấy ra vendor theo id
        Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not Found with id : " + id);
        });
        // cập nhật thông tin vendor
        vendor.setName(request.getName());
        vendor.setWarehouseEmployee(iCurrentUserLmpl.getUser());
        // lưu vào csdl
        vendorRepository.save(vendor);

        return new StatusResponse(HttpStatus.OK,
                "update Vendor name success" ,
                DataMapper.toDataResponse(vendor.getId(), vendor.getName()));
    }
    // lấy ra danh sách vendor có tính tổng số material theo từng vendor - 13
    public PageDto getListVendorTotalMaterial(int page, int pageSize) {

        Page<VendorTotalMaterialDto> vendors = materialRepository.getListVendorTotalMaterial(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                vendors.getNumber() + 1,
                vendors.getSize(),
                vendors.getTotalPages(),
                (int) Math.ceil(vendors.getTotalElements()),
                vendors.getContent()
        );
    }
    // lấy danh sách vendot theo id vendor - 14
    public PageDto getListVendorById(int page, int pageSize, int vendorId) {

        Page<MaterialProjection> materials = materialRepository.getListVendorById(PageRequest.of(page - 1, pageSize), vendorId);

        return new PageDto(
                materials.getNumber() + 1,
                materials.getSize(),
                materials.getTotalPages(),
                (int) Math.ceil(materials.getTotalElements()),
                materials.getContent()
        );
    }
    // lấy ra vật lieuejtheo id - 15
    public MaterialProjection getMaterialById(Integer id) {
        return materialRepository.getMaterialById(id).orElseThrow(() -> {
            throw new NotFoundException("Not Found with id : " + id);
        });
    }


}
