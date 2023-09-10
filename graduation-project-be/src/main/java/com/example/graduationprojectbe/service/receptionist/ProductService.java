package com.example.graduationprojectbe.service.receptionist;

import com.example.graduationprojectbe.config.GenerateCode;
import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.dto.projection.ReceiptInfo;
import com.example.graduationprojectbe.entity.*;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.*;
import com.example.graduationprojectbe.request.create.CreateBillAndGuaranteeRequest;
import com.example.graduationprojectbe.request.create.CreateProductRequest;
import com.example.graduationprojectbe.request.other.InformationEngineerRequest;
import com.example.graduationprojectbe.request.update.UpdateReceiptRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import com.example.graduationprojectbe.service.auth.EmailService;
import com.example.graduationprojectbe.service.auth.PDFService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private GuaranteeRepository guaranteeRepository;
    @Autowired
    private GenerateCode generateCode;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private PDFService pdfService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ReceiptRepository receiptRepository;


    // lấy ra danh sách sản phẩm đã sửa chữa ok chờ trả khách có phaân trang - 4
    public PageDto findProductWaitingReturnCustomerAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductWaitingReturnCustomerAll(PageRequest.of(page - 1, pageSize), term, Status.WAITING_FOR_RETURN);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sản phẩm vừa sửa chữa xong chờ tạo bảo hành
    public PageDto findProductRepairedAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductRepairedAll(PageRequest.of(page - 1, pageSize), term, Status.REPAIRED);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // danh sách sản phẩm chờ chuyển người sửa chữa WAITING_FOR_REPAIR
    public PageDto findProductWaitingForRepairAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductWaitingForRepairAll(PageRequest.of(page - 1, pageSize), term, Status.WAITING_FOR_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // lấy sản phẩm theo id
    public ProductProjection findProductByID(Integer id) {
        return productRepository.findProductByID(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }

    public StatusResponse registerEngineerInformationByProduct(InformationEngineerRequest request, Integer id) {
        // lấy ra user engineer theo employee code
        User user = userRepository.findByEmployeeCode(request.getEmployeeCode()).orElseThrow(() -> new NotFoundException("Not Found with employee code a " + request.getEmployeeCode()));
        // lấy ra pproduct theo id
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found with id a " + id));
        // thêm thông tin engineer
        product.setEngineer(user);
        product.setStatus(Status.UNDER_REPAIR);
        product.setTransferDate(LocalDateTime.now());
        // lưu lại trên csdl
        productRepository.save(product);

        return new StatusResponse(HttpStatus.OK,
                "update Engineer success",
                DataMapper.toDataResponse(product.getId(), product.getNameModel()));
    }

    public PageDto findProductUnderRepairAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductUnderRepairAll(PageRequest.of(page - 1, pageSize), term, Status.UNDER_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    public StatusResponse createProduct(CreateProductRequest requet) {
        // lấy ra khách hàng theo id
        Customer customer = customerRepository.findById(requet.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Not Found with id: " + requet.getCustomerId()));
        // kiểm tra xem số tiền > 0
        if (requet.getPrice() <= 0) {
            throw new BadRequestException("The amount must be greater than 0");
        }
        // kiểm tra sản phẩm đã qua sửa chữa chưa
        if (productRepository.findByIme(requet.getIme()).isPresent()) {
            throw new BadRequestException("The product has been repaired or is being repaired. Please check again");
        }
        // tạo sản phẩm mới
        Product product = Product.builder()
                .phoneCompany(requet.getPhoneCompany())
                .nameModel(requet.getModel())
                .ime(requet.getIme())
                .defectName(requet.getDefectName())
                .price(requet.getPrice())
                .repair(false)
                .customer(customer)
                .receptionists(iCurrentUserLmpl.getUser())
                .build();
        // lưu vào csdl
        productRepository.save(product);

        return new StatusResponse(HttpStatus.CREATED,
                "Create Product a success",
                DataMapper.toDataResponse(product.getId(), product.getNameModel()));
    }

    // danh sách sản phẩm đang pending trong cửa hàng
    public PageDto findProductPendingAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductPendingAll(PageRequest.of(page - 1, pageSize), term, Status.WAITING_FOR_REPAIR, Status.UNDER_REPAIR);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }

    // xóa sản phẩm
    public StatusResponse deletePorductBtID(Integer id) {
        // lấy sản phẩm theo id
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
        if (product.getStatus() == Status.DELIVERED || product.getStatus() == Status.WAITING_FOR_RETURN || product.getStatus() == Status.REPAIRED) {
            throw new BadRequestException("Product cannot be deleted");
        }

        product.setDelete(false);
        productRepository.save(product);

        return new StatusResponse(HttpStatus.NO_CONTENT,
                "Create Product a success",
                DataMapper.toDataResponse(product.getId(), product.getNameModel()));
    }

    // đăng ký bảo hành cho sản phẩm
    public StatusResponse createNewWarranty(CreateBillAndGuaranteeRequest request) {
        // lấy ra sản phẩm theo id
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("Not Found with id:  " + request.getProductId()));
        // // kiểm tra xem sản phẩm đã hoàn thành chưa
        if (product.getStatus() != Status.REPAIRED) {
            throw new BadRequestException("Product repair completed repair");
        }

        // tạo bảo hành mới
        Guarantee guarantee = Guarantee.builder()
                .guaranteeCode(generateCode.generateCode())
                .activationEmployee(iCurrentUserLmpl.getUser())
                .product(product)
                .build();
        guaranteeRepository.save(guarantee);

        product.getGuarantees().add(guarantee);
        product.setStatus(Status.WAITING_FOR_RETURN);
        product.setCreateGuaranteeDate(LocalDateTime.now());
        productRepository.save(product);

        return new StatusResponse(HttpStatus.CREATED,
                "registered guarantee successfully",
                DataMapper.toDataResponse(guarantee.getId(), guarantee.getGuaranteeCode()));

    }

    // tạo hóa đơn
    public StatusResponse createBill(CreateBillAndGuaranteeRequest request) throws DocumentException, IOException {
        // lấy ra biên lai nhận sản phẩm
        Receipt receipt = receiptRepository.findByProductId(request.getProductId()).orElseThrow(() -> new NotFoundException("Not Found With ID: " + request.getProductId()));

        // lấy ra sản phẩm theo id
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("Not Found with id : " + request.getProductId()));

        // kiểm tra sản phẩm đã hoàn thành chưa
        if (product.getStatus() != Status.WAITING_FOR_RETURN) {
            throw new BadRequestException("Payment for the product has been completed");
        }
        product.setFinishDate(LocalDateTime.now());
        product.setStatus(Status.DELIVERED);
        product.setProductPayer(iCurrentUserLmpl.getUser());
        productRepository.save(product);
        // tạo hóa đơn moi
        Bill bill = Bill.builder()
                .invoiceCreator(iCurrentUserLmpl.getUser())
                .invoiceCreationDate(LocalDateTime.now())
                .product(product)
                .build();
        // lưu vào csdl
        billRepository.save(bill);

        // cập nhật lại thông tin biên lai nhận sản phẩm
        receipt.setStatus(false);
        receiptRepository.save(receipt);

        emailService.sendPDFMail(bill.getProduct().getCustomer().getEmail(), "Hóa Đơn Sửa Chữa", "Gửi Khách Hàng Chi tiết thông tin hóa đơn", pdfService.convertBillToPdf(bill));

        return new StatusResponse(HttpStatus.CREATED,
                "registered guarantee successfully",
                DataMapper.toDataResponse(bill.getId(), bill.getProduct().getNameModel()));

    }


    // tạo biên lai thu nhận sản phẩm
    public StatusResponse createReceipt(Integer id) throws DocumentException, IOException {

        if (receiptRepository.findByProductId(id).isPresent()) {
            throw new BadRequestException("generated receipt");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));

        Receipt receipt = Receipt.builder()
                .createDate(LocalDateTime.now())
                .quantity(1)
                .status(true)
                .employeeCreate(iCurrentUserLmpl.getUser())
                .product(product)
                .build();

        receiptRepository.save(receipt);

        emailService.sendPDFMail(receipt.getProduct().getCustomer().getEmail(),
                "Biên Lai Thu Nhận Sản phẩm", "Gửi Khách Hàng Chi tiết thông tin biên lai",
                pdfService.convertReceiptToPdf(receipt));


        return new StatusResponse(HttpStatus.CREATED, "Create Receipt success", DataMapper.toDataResponse(receipt.getId(), receipt.getProduct().getNameModel()));
    }

    public PageDto findReceiptAll(int page, int pageSize, String term) {

        Page<ReceiptInfo> receipts = receiptRepository.findReceiptAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                receipts.getNumber() + 1,
                receipts.getSize(),
                receipts.getTotalPages(),
                (int) Math.ceil(receipts.getTotalElements()),
                receipts.getContent()
        );
    }

    public PageDto findReceiptStatusTrueAll(int page, int pageSize, String term) {

        Page<ReceiptInfo> receipts = receiptRepository.findReceiptStatusTrueAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                receipts.getNumber() + 1,
                receipts.getSize(),
                receipts.getTotalPages(),
                (int) Math.ceil(receipts.getTotalElements()),
                receipts.getContent()
        );
    }

    public ReceiptInfo findReceiptById(Integer id) {
        return receiptRepository.findReceiptById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));
    }

    public StatusResponse updateReceiptById(UpdateReceiptRequest request, Integer id) {
        Receipt receipt =  receiptRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));

        if (!receipt.isStatus()) {
            throw new BadRequestException("Updates are not allowed");
        }

        receipt.setPayDate(request.getPayDate());
        receiptRepository.save(receipt);

        return new StatusResponse(HttpStatus.OK, "Update Receipt success", DataMapper.toDataResponse(receipt.getId(), receipt.getProduct().getNameModel()));
    }

    public PageDto findProductNoCreateReceiptAll(int page, int pageSize, String term) {

        Page<ProductProjection> products = productRepository.findProductNoCreateReceiptAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                products.getNumber() + 1,
                products.getSize(),
                products.getTotalPages(),
                (int) Math.ceil(products.getTotalElements()),
                products.getContent()
        );
    }


}