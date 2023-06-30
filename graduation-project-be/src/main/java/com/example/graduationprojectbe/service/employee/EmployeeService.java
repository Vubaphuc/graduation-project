package com.example.graduationprojectbe.service.employee;

import com.example.graduationprojectbe.dto.dto.UserDto;
import com.example.graduationprojectbe.dto.projection.EmployeeInfo;
import com.example.graduationprojectbe.entity.Bill;
import com.example.graduationprojectbe.entity.Image;
import com.example.graduationprojectbe.entity.Product;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.mapper.UserMapper;
import com.example.graduationprojectbe.repository.BillRepository;
import com.example.graduationprojectbe.repository.ImageRepository;
import com.example.graduationprojectbe.repository.ProductRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.ChangePasswordRequest;
import com.example.graduationprojectbe.request.ForgotPasswordRequest;
import com.example.graduationprojectbe.request.UpdatePersonalInformationRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import com.example.graduationprojectbe.service.auth.CheckFiles;
import com.example.graduationprojectbe.service.auth.EmailService;
import com.example.graduationprojectbe.service.auth.PDFService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CheckFiles checkFiles;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private PDFService pdfService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ExcelExporterService excelExporterService;


    // lấy danh sách nhân viên sửa chữa
    public List<EmployeeInfo> findEngineerAll() {
        return userRepository.findEngineerAll();
    }

    // lấy danh sách nhân viên lễ tân
    public List<EmployeeInfo> findReceptionistAll() {
        return userRepository.findReceptionistAll();
    }


    // lấy danh sách nhân viên kho
    public List<EmployeeInfo> findWarehouseEmployeeAll() {
        return userRepository.findWarehouseEmployeeAll();
    }


    // lấy danh sách nhân viên bảo hành và lễ tân
    public List<EmployeeInfo> findReceptionistAndWarrantyEmployeeAll() {
        return userRepository.findReceptionistAndWarrantyEmployeeAll();
    }


    //Quên mật khẩu
    public StatusResponse forgotPassword(ForgotPasswordRequest request) {
        // lấy user theo email
        User user = userRepository.findUsersByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("Not Found With email: " + request.getEmail()));

        //tạo mật khẩu ngẫu nhiên
        Random rd = new Random();
        String newPassword = String.valueOf(rd.nextInt(900) + 100);
        // lưu lại mật khẩu mới cho user
        user.setPassword(passwordEncoder.encode(newPassword));
        // lưu lại
        userRepository.save(user);

        // gửi mật khẩu về mail
        emailService.sendMail(user.getEmail(), "New Password", "Mật khẩu mới của bạn là " + newPassword);

        return new StatusResponse(HttpStatus.OK,
                "Lấy lại mật khẩu thành công",
                DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));
    }

    // thay đổi mật khẩu
    public StatusResponse changePassword(ChangePasswordRequest request) {
        // lấy ra tài khoản đang login
        User user = iCurrentUserLmpl.getUser();
        // kiểm tra xem mật khẩu đúng không
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Old password is incorrect");
        }
        // kiểm tra xem mật khẩu mới và mật khẩu cũ có trùng nhau không
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("The new password cannot be the same as the old password");
        }
        // kiểm tra mật khẩu mới và nhập lại mật khẩu mới có trùng nhau không
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new BadRequestException("New password does not match confirm new password");
        }
        // set lại password cho user
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        // lưu lại trên csdl
        userRepository.save(user);

        return new StatusResponse(HttpStatus.OK,
                "Change password success",
                DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));
    }


    // Cập nhât thông thông tin cá nhân
    public UserDto updatePersonalInformation(UpdatePersonalInformationRequest request) {
        // lấy ra tài khoản đang login
        User user = iCurrentUserLmpl.getUser();
        // set lại thông tin cho use
        user.setEmployeeName(request.getFullName());
        user.setPhoneNumber(request.getPhone());
        user.setAddress(request.getAddress());
        // lưu lên csdl
        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    public StatusResponse updateProfilePicture(MultipartFile avatar) {

        // kiểm tra tiêu chuẩn file(kích thước file, type file, tên file)
        checkFiles.validataFile(avatar);

        try {
            // lấy ra user đang login
            User user = iCurrentUserLmpl.getUser();
            // tạo 1 image
            Image image = Image.builder()
                    .type(avatar.getContentType())
                    .data(avatar.getBytes())
                    .user(user)
                    .build();

            // lưu lại trên csdl
            imageRepository.save(image);

            // cập nhât avatar cho user
            user.setAvatar(image);
            // lưu lại thay đổi lên csdl
            userRepository.save(user);



            return new StatusResponse(HttpStatus.OK,
                    "Cập nhật thành công",
                    DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));


        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // lấy ảnh đại diện user theo id
    public Image getAvatarById(Integer id) {
        // kểm tra xem id có phải id user đang login không
        if (iCurrentUserLmpl.getUser().getId() != id) {
            throw new BadRequestException("ID: " + id + " not your ID");
        }
        // kiểm tra xem user đã có avater hay chưa
        if (iCurrentUserLmpl.getUser().getAvatar() == null) {
            try {
                Path path = Paths.get("src/main/resources/static/images/avatar-mac-dinh.png");
                byte[] defaultImageData = Files.readAllBytes(path);

                Image image = Image.builder()
                        .type("avatar-mac-dinh/png")
                        .data(defaultImageData)
                        .build();
                // nếu không có avatar nào thì trả về 1 image mặc định
                return image;

            } catch (IOException e) {
                throw new NotFoundException("Unable to access avatar");
            }
        }
        // nếu có thì trả về image của user
        Image image = imageRepository.findById(iCurrentUserLmpl.getUser().getAvatar().getId())
                .orElseThrow(() -> new NotFoundException("Not Found with id = " + id));

        return image;

    }

    public byte[] exportBillToPdf(Integer id) throws DocumentException, IOException {
        Bill bill = billRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found With id: " + id));
        return pdfService.convertBillToPdf(bill);
    }


}
