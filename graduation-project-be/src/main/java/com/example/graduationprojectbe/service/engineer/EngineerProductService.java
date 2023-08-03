package com.example.graduationprojectbe.service.engineer;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.ComponentProjection;
import com.example.graduationprojectbe.dto.projection.ProductGuaranteeProjection;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.entity.Components;
import com.example.graduationprojectbe.entity.Product;
import com.example.graduationprojectbe.entity.ProductGuarantee;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.ComponentsRepository;
import com.example.graduationprojectbe.repository.ProductGuaranteeRepository;
import com.example.graduationprojectbe.repository.ProductRepository;
import com.example.graduationprojectbe.request.other.InformationRepairRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EngineerProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private ComponentsRepository componentsRepository;
    @Autowired
    private ProductGuaranteeRepository productGuaranteeRepository;



    // lấy danh sách sản phẩm đang sửa theo tên user
    public PageDto getListProductNewByUser(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.getListProductNewByUser(PageRequest.of(page - 1, pageSize), term, iCurrentUserLmpl.getUser().getId(), Status.UNDER_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // lấy ra sản phẩm theo id
    public ProductProjection getProductNewById(Integer id) {
        return productRepository.getProductNewById(id,iCurrentUserLmpl.getUser().getId())
                .orElseThrow(() -> new NotFoundException("Not Found with id: " + id));
    }

    // cập nhật thông tin sửa chữa
    public StatusResponse updateInformationProductNewById(InformationRepairRequest request, Integer id) {
        if (!request.isStatus()) {
            throw new BadRequestException("Status change to OK");
        }
        // lấy ra sản phẩm theo id
        Product product = productRepository.findProductById_Engineer(id, iCurrentUserLmpl.getUser().getId(),Status.UNDER_REPAIR).orElseThrow(() -> {
            throw new NotFoundException("Not Found with id : "  + id);
        });
        // lấy ra loại linh kiện
        Components components = componentsRepository.findById(request.getComponentsId()).orElseThrow(() -> {
            throw new NotFoundException("Not Found with id : "  + request.getComponentsId());
        });
        // cập nhật thông tin sửa chữa của sản phẩm
        product.setLocation(request.getLocation());
        product.setStatus(Status.REPAIRED);
        product.setComponents(components);
        product.setNote(request.getNote());
        product.setOutputDate(LocalDateTime.now());
        // lưu lại lên csdl
        productRepository.save(product);

        return new StatusResponse(HttpStatus.OK,
                "update information Product success",
                DataMapper.toDataResponse(product.getId(), product.getIme())
        );
    }

    // lấy danh sách các loại linh kiện
    public PageDto getListComponentPhone(int page, int pageSize) {

        Page<ComponentProjection> components = componentsRepository.getListComponentPhone(PageRequest.of(page - 1, pageSize));

        return new PageDto(
                components.getNumber() + 1,
                components.getSize(),
                components.getTotalPages(),
                (int) Math.ceil(components.getTotalElements()),
                components.getContent()
        );
    }

    // danh sách sản phẩm bảo hành theo user
    public PageDto findProductGuaranteeByUserAll(int page, int pageSize, String term) {

        Page<ProductGuaranteeProjection> productGuarantees = productGuaranteeRepository.findProductGuaranteeByUserAll(PageRequest.of(page - 1, pageSize), term, iCurrentUserLmpl.getUser().getId(), Status.UNDER_REPAIR);

        return new PageDto(
                productGuarantees.getNumber() + 1,
                productGuarantees.getSize(),
                productGuarantees.getTotalPages(),
                (int) Math.ceil(productGuarantees.getTotalElements()),
                productGuarantees.getContent()
        );
    }

    public StatusResponse updateInformationProductGuaranteeById(InformationRepairRequest request, Integer id) {
        // kiểm tra trạng thái output => OK
        if (!request.isStatus()) {
            throw new BadRequestException("Status change to OK");
        }
        // lấy ra sản phẩm theo id
        ProductGuarantee productGuarantee = productGuaranteeRepository.findProductGuaranteeById_Engineer(id, iCurrentUserLmpl.getUser().getId(),Status.UNDER_REPAIR)
                .orElseThrow(() -> new NotFoundException("Not Found with product guarantee id : " + id));
        // lấy ra loại linh kiện
        Components components = componentsRepository.findById(request.getComponentsId())
                .orElseThrow(() -> new NotFoundException("Not Found with component id : " + request.getComponentsId()));
        // cập nhật thông tin sửa chữa của sản phẩm
        productGuarantee.setLocation(request.getLocation());
        productGuarantee.setStatus(Status.REPAIRED);
        productGuarantee.setComponents(components);
        productGuarantee.setNote(request.getNote());
        productGuarantee.setOutputDate(LocalDateTime.now());
        // lưu lại lên csdl
        productGuaranteeRepository.save(productGuarantee);

        return new StatusResponse(HttpStatus.OK,
                "update information Product success",
                DataMapper.toDataResponse(productGuarantee.getId(), productGuarantee.getIme())
        );
    }

    // lấy sản phẩm bảo hành theo id
    public ProductGuaranteeProjection findProductGuaranteeByID(Integer id) {
        return productGuaranteeRepository.findById_Engineer(id,iCurrentUserLmpl.getUser().getId())
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }
}
