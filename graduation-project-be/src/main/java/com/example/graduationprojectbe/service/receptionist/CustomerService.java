package com.example.graduationprojectbe.service.receptionist;

import com.example.graduationprojectbe.dto.dto.CustomerDto;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.CustomerInfo;
import com.example.graduationprojectbe.dto.projection.ProductInfo;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.entity.Customer;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.CustomerRepository;
import com.example.graduationprojectbe.repository.ProductRepository;
import com.example.graduationprojectbe.request.CreateCustomerRequest;
import com.example.graduationprojectbe.request.UpdateCustomerRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private ProductRepository productRepository;



    // tạo khách hàng mới
    public StatusResponse createCustomer(CreateCustomerRequest request) {
        // kiểm tra email khách hàng đã tồn tại chưa
        if (customerRepository.findByEmail(request.getCustomerEmail()).isPresent()) {
            throw new BadRequestException("Customer already exists");
        }
        // tạo khách hàng mới
        Customer customer = Customer.builder()
                .fullName(request.getCustomerName())
                .email(request.getCustomerEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getCustomerAddress())
                .receptionists(iCurrentUserLmpl.getUser())
                .build();
        // lưu vào csdl
        customerRepository.save(customer);

        return new StatusResponse(HttpStatus.CREATED,
                "Create Customer success",
                DataMapper.toDataResponse(customer.getId(), customer.getFullName()));
    }


    // lấy ra khàng hàng theo id
    public CustomerInfo getCustomerById(Integer id) {
        return customerRepository.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }

    // lấy ra list product theo id khách hàng -  3
    public PageDto getListProductByCustomerId(int page, int pageSize, Integer id) {

        Page<ProductInfo> products = productRepository.getListProductByCustomerId(PageRequest.of(page - 1, pageSize), id);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // tìm kiếm sản phẩm có status = false theo từng khách hàng - 2
    public PageDto searchProductByCustomer(int page, int pageSize, String term) {

        Page<CustomerDto> products = productRepository.searchProductByCustomer(PageRequest.of(page - 1, pageSize), term);


        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // cập nhật thông tin khách hàng
    public StatusResponse updateCustomerById(UpdateCustomerRequest request, Integer id) {
        // lấy ra khách hàng theo id
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
        // kiểm tra email
        if (!customer.getEmail().equals(request.getCustomerEmail())) {
            if (customerRepository.findByEmail(request.getCustomerEmail()).isPresent()) {
                throw new BadRequestException("Email already exists");
            }
        }

        // cập nhật thông tin mới
        customer.setAddress(request.getCustomerAddress());
        customer.setEmail(request.getCustomerEmail());
        customer.setFullName(request.getCustomerName());
        customer.setPhoneNumber(request.getPhoneNumber());
        // lưu trong csdl
        customerRepository.save(customer);

        return new StatusResponse(HttpStatus.OK,
                "Update Customer success",
                DataMapper.toDataResponse(customer.getId(), customer.getFullName()));
    }

    public StatusResponse deleteCustomerById(Integer id) {
        // lấy ra khách hàng theo id
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));

        CustomerDto customerDto = customerRepository.searchProductByCustomerID(customer.getId());

        if (customerDto.getSumProduct() > 0 ) {
            throw new BadRequestException("The customer has a product in the store. Can not delete");
        }

        customer.setDelete(false);
        customerRepository.save(customer);

        return new StatusResponse(HttpStatus.NO_CONTENT,
                "Update Customer success",
                DataMapper.toDataResponse(customer.getId(), customer.getFullName()));
    }
}
