package com.example.graduationprojectbe.service.warrantyemployee;

import com.example.graduationprojectbe.config.GenerateCode;
import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.dto.CustomerDto;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.*;
import com.example.graduationprojectbe.entity.*;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.*;
import com.example.graduationprojectbe.request.CreateBillAndGuaranteeRequest;
import com.example.graduationprojectbe.request.InformationEngineerRequest;
import com.example.graduationprojectbe.request.RegisterProductGuaranteeRequest;
import com.example.graduationprojectbe.request.UpdateReceiptRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import com.example.graduationprojectbe.service.auth.EmailService;
import com.example.graduationprojectbe.service.auth.PDFService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarrantyProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private ProductGuaranteeRepository productGuaranteeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillGuaranteeRepository billGuaranteeRepository;
    @Autowired
    private GuaranteeRepository guaranteeRepository;
    @Autowired
    private GenerateCode generateCode;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReceiptGuaranteeRepository receiptGuaranteeRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PDFService pdfService;



    // lấy danh sách sản phẩm new đã trả khàng
    public PageDto findProductDeliveredAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductDeliveredAll(PageRequest.of(page - 1, pageSize), term, Status.DELIVERED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // lấy sản phẩm new đã trả khách hàng theo id
    public ProductProjection findProductDeliveredByID(Integer id) {
        return productRepository.findProductDeliveredByID(id, Status.DELIVERED)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }

    // tạo mới sản phẩm bảo hành
    public StatusResponse createProductGuarantee(RegisterProductGuaranteeRequest request) {
        // lấy ra sản phẩm theo id
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Not Found With id: " + request.getId()));
        // kiểm tra sản phẩm đã hoàn thành sửa chữa chưa
        if (product.getStatus() != Status.DELIVERED) {
            throw new BadRequestException("The product has not completed the repair process");
        }
        // tạo sản phẩm bảo hành
        ProductGuarantee productGuarantee = ProductGuarantee.builder()
                .productId(product.getId())
                .phoneCompany(product.getPhoneCompany())
                .nameModel(product.getNameModel())
                .ime(product.getIme())
                .defectName(request.getDefectName())
                .price(request.getPrice())
                .cause(request.getCause())
                .repair(true)
                .customer(product.getCustomer())
                .engineer(product.getEngineer())
                .warranty(iCurrentUserLmpl.getUser())
                .build();
        // lưu vào csdl
        productGuaranteeRepository.save(productGuarantee);

        product.setRepair(true);
        productRepository.save(product);

        return new StatusResponse(HttpStatus.CREATED,
                "Create Prodcut Guarantee success",
                DataMapper.toDataResponse(productGuarantee.getId(), productGuarantee.getIme()));
    }

    // lấy danh sách sản phẩm bảo hành chờ sửa
    public PageDto findProductGuaranteeWaitingForRepairAll(int page, int pageSize, String term) {

        Page<ProductGuaranteeProjection> productGuarantees = productGuaranteeRepository.findProductGuaranteeWaitingForRepairAll(PageRequest.of(page - 1, pageSize), term, Status.WAITING_FOR_REPAIR);

        return new PageDto(
                productGuarantees.getNumber() + 1,
                productGuarantees.getSize(),
                productGuarantees.getTotalPages(),
                (int) Math.ceil(productGuarantees.getTotalElements()),
                productGuarantees.getContent()
        );
    }

    // lấy sản phẩm bảo hành theo id
    public ProductGuaranteeProjection findProductGuaranteeByID(Integer id) {
        return productGuaranteeRepository.findProductGuaranteeByID(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }


    // đăng ký nhân viên sủa chữa
    public StatusResponse registerEngineerInformationByProductGuarantee(InformationEngineerRequest request, Integer id) {
        // lấy ra sản phẩm bảo hành theo id
        ProductGuarantee productGuarantee = productGuaranteeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
        // kiểm tra sản phẩm có đang công đoạn chờ chuyển cho engineer không
        if (productGuarantee.getStatus() != Status.WAITING_FOR_REPAIR) {
            throw new BadRequestException("The product is in another stage. Please check again");
        }
        // lấy ra user theo code
        User user = userRepository.findByEmployeeCode(request.getEmployeeCode())
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
        // thêm thông tin nhân viên sửa chữa
        productGuarantee.setEngineer(user);
        productGuarantee.setStatus(Status.UNDER_REPAIR);
        productGuarantee.setTransferDate(LocalDateTime.now());
        // lưu lại trên csdl
        productGuaranteeRepository.save(productGuarantee);

        return new StatusResponse(HttpStatus.OK,
                "Update engineer success",
                DataMapper.toDataResponse(productGuarantee.getId(), productGuarantee.getIme()));
    }

    // danh sách sản phẩm bảo hành đã sửa xong
    public PageDto findProductGuaranteeRepairedAll(int page, int pageSize, String term) {

        Page<ProductGuaranteeProjection> productGuarantees =
                productGuaranteeRepository.findProductGuaranteeRepairedAll(PageRequest.of(page - 1, pageSize), term, Status.REPAIRED);

        return new PageDto(
                productGuarantees.getNumber() + 1,
                productGuarantees.getSize(),
                productGuarantees.getTotalPages(),
                (int) Math.ceil(productGuarantees.getTotalElements()),
                productGuarantees.getContent()
        );
    }

    // đăng ký bảo hành cho sản phẩm bảo hành
    // đăng ký bảo hành cho sản phẩm
    public StatusResponse createNewWarranty(CreateBillAndGuaranteeRequest request) {
        // lấy ra sản phẩm bảo hành theo id
        ProductGuarantee productGuarantee = productGuaranteeRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("Not Found with id : " + request.getProductId()));

        // lấy ra sản phẩm theo id
        Product product = productRepository.findById(productGuarantee.getProductId()).orElseThrow(() -> new NotFoundException("Not Found with id:  " + request.getProductId()));

        // // kiểm tra xem sản phẩm đã hoàn thành chưa
        if (productGuarantee.getStatus() != Status.REPAIRED) {
            throw new BadRequestException("Product repair completed repair");
        }
        // lấy ra list bảo hành
        List<Guarantee> guarantees = product.getGuarantees();
        // kiểm tra xem bảo hành còn hạn không
        boolean hasExpiredGuarantee = guarantees.stream()
                .anyMatch(guarantee -> guarantee.isStatus() && guarantee.getExpirationDate().isAfter(LocalDateTime.now()));
        // nếu hết hạn thì tạo mới ngược lại bỏ qua
        if (!hasExpiredGuarantee) {
            // tạo bảo hành mới
            Guarantee guarantee = Guarantee.builder()
                    .guaranteeCode(generateCode.generateCode())
                    .activationEmployee(iCurrentUserLmpl.getUser())
                    .product(product)
                    .build();
            guaranteeRepository.save(guarantee);
            // thêm bảo hành mới vào product
            product.getGuarantees().add(guarantee);
            // lưu vào csdl
            productRepository.save(product);
        }

        // thêm thông tin trạng thái sản phẩm bảo hành
        productGuarantee.setStatus(Status.WAITING_FOR_RETURN);
        product.setCreateGuaranteeDate(LocalDateTime.now());
        // lưu vào csdl
        productGuaranteeRepository.save(productGuarantee);

        return new StatusResponse(HttpStatus.CREATED,
                "registered guarantee successfully",
                DataMapper.toDataResponse(productGuarantee.getId(), productGuarantee.getIme()));

    }

    // tạo hóa đơn cho sản phẩm bảo hành
    public StatusResponse createBill(CreateBillAndGuaranteeRequest request) {

        ReceiptGuarantee receiptGuarantee = receiptGuaranteeRepository.findByProductGuarantee_Id(request.getProductId()).orElseThrow(() -> new NotFoundException("Not Found With ID: " + request.getProductId()));

        // lấy ra sản phẩm theo id
        ProductGuarantee productGuarantee = productGuaranteeRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("Not Found with id : " + request.getProductId()));

        // kiểm tra sản phẩm đã hoàn thành chưa
        if (productGuarantee.getStatus() != Status.WAITING_FOR_RETURN) {
            throw new BadRequestException("Payment for the product has been completed");
        }
        productGuarantee.setFinishDate(LocalDateTime.now());
        productGuarantee.setStatus(Status.DELIVERED);
        productGuarantee.setProductPayer(iCurrentUserLmpl.getUser());
        productGuaranteeRepository.save(productGuarantee);
        // tạo hóa đơn moi
        BillGuarantee billGuarantee = BillGuarantee.builder()
                .invoiceCreator(iCurrentUserLmpl.getUser())
                .invoiceCreationDate(LocalDateTime.now())
                .productGuarantee(productGuarantee)
                .build();
        // lưu vào csdl
        billGuaranteeRepository.save(billGuarantee);

        receiptGuarantee.setStatus(false);
        receiptGuaranteeRepository.save(receiptGuarantee);

        return new StatusResponse(HttpStatus.CREATED,
                "registered guarantee successfully",
                DataMapper.toDataResponse(billGuarantee.getId(), billGuarantee.getProductGuarantee().getNameModel()));

    }

    // lấy danh sách sản phẩm chờ trả khách
    public PageDto findProductGuaranteeWaitingForReturnAll(int page, int pageSize, String term) {

        Page<ProductGuaranteeProjection> productGuarantees = productGuaranteeRepository.findProductGuaranteeWaitingForReturnAll(PageRequest.of(page - 1, pageSize), term, Status.WAITING_FOR_RETURN);

        return new PageDto(
                productGuarantees.getNumber() + 1,
                productGuarantees.getSize(),
                productGuarantees.getTotalPages(),
                (int) Math.ceil(productGuarantees.getTotalElements()),
                productGuarantees.getContent()
        );
    }

    public PageDto findProductGuaranteeUnderRepairAll(int page, int pageSize, String term) {

        Page<ProductGuaranteeProjection> productGuarantees = productGuaranteeRepository.findProductUnderRepairAll(PageRequest.of(page - 1, pageSize), term, Status.UNDER_REPAIR);

        return new PageDto(
                productGuarantees.getNumber() + 1,
                productGuarantees.getSize(),
                productGuarantees.getTotalPages(),
                (int) Math.ceil(productGuarantees.getTotalElements()),
                productGuarantees.getContent()
        );
    }

    // danh sách khách hàng có tổng số sản phẩm
    public PageDto findProductByCustomers(int page, int pageSize, String term) {

        Page<CustomerProjection> products = customerRepository.findProductByCustomers(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }





    // tạo biên lai thu nhận sản phẩm
    public StatusResponse createReceipt(Integer id) throws DocumentException, IOException {

        if (receiptGuaranteeRepository.findByProductGuarantee_Id(id).isPresent()) {
            throw new BadRequestException("generated receipt");
        }

        ProductGuarantee productGuarantee = productGuaranteeRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));

        ReceiptGuarantee receiptGuarantee = ReceiptGuarantee.builder()
                .createDate(LocalDateTime.now())
                .quantity(1)
                .status(true)
                .employeeCreate(iCurrentUserLmpl.getUser())
                .productGuarantee(productGuarantee)
                .build();

        receiptGuaranteeRepository.save(receiptGuarantee);

        emailService.sendPDFMail(receiptGuarantee.getProductGuarantee().getCustomer().getEmail(),
                "Biên Lai Thu Nhận Sản phẩm", "Gửi Khách Hàng Chi tiết thông tin biên lai",
                pdfService.convertReceiptGuaranteeToPdf(receiptGuarantee));


        return new StatusResponse(HttpStatus.CREATED, "Create Receipt success", DataMapper.toDataResponse(receiptGuarantee.getId(), receiptGuarantee.getProductGuarantee().getNameModel()));
    }

    public PageDto findReceiptAll(int page, int pageSize, String term) {

        Page<ReceiptGuaranteeInfo> receiptGuarantees = receiptGuaranteeRepository.findReceiptAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                receiptGuarantees.getNumber() + 1,
                receiptGuarantees.getSize(),
                receiptGuarantees.getTotalPages(),
                (int) Math.ceil(receiptGuarantees.getTotalElements()),
                receiptGuarantees.getContent()
        );
    }

    public PageDto findReceiptStatusTrueAll(int page, int pageSize, String term) {

        Page<ReceiptGuaranteeInfo> receiptGuarantees = receiptGuaranteeRepository.findReceiptStatusTrueAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                receiptGuarantees.getNumber() + 1,
                receiptGuarantees.getSize(),
                receiptGuarantees.getTotalPages(),
                (int) Math.ceil(receiptGuarantees.getTotalElements()),
                receiptGuarantees.getContent()
        );
    }

    public PageDto findProductNoCreateReceiptAll(int page, int pageSize, String term) {

        Page<ProductGuaranteeProjection> productsGuarantee = productGuaranteeRepository.findProductNoCreateReceiptAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                productsGuarantee.getNumber() + 1,
                productsGuarantee.getSize(),
                productsGuarantee.getTotalPages(),
                (int) Math.ceil(productsGuarantee.getTotalElements()),
                productsGuarantee.getContent()
        );
    }

    public ReceiptGuaranteeInfo findReceiptById(Integer id) {
        return receiptGuaranteeRepository.findReceiptById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));
    }

    public StatusResponse updateReceiptById(UpdateReceiptRequest request, Integer id) {
        ReceiptGuarantee receiptGuarantee =  receiptGuaranteeRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));

        if (!receiptGuarantee.isStatus()) {
            throw new BadRequestException("Updates are not allowed");
        }

        receiptGuarantee.setPayDate(request.getPayDate());
        receiptGuaranteeRepository.save(receiptGuarantee);

        return new StatusResponse(HttpStatus.OK, "Update Receipt success", DataMapper.toDataResponse(receiptGuarantee.getId(), receiptGuarantee.getProductGuarantee().getNameModel()));
    }


}
