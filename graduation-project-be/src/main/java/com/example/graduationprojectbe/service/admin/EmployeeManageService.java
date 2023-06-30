package com.example.graduationprojectbe.service.admin;

import com.example.graduationprojectbe.config.GenerateCode;
import com.example.graduationprojectbe.dto.page.PageDto;
import com.example.graduationprojectbe.dto.projection.EmployeeProjection;
import com.example.graduationprojectbe.dto.projection.RoleInfo;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.RoleRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.AdminCreateEmployeeRequest;
import com.example.graduationprojectbe.request.AdminUpdateInformationEmployeeRequest;
import com.example.graduationprojectbe.request.AdminUpdatePasswordRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.service.auth.EmailService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManageService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private GenerateCode generateCode;
    @Autowired
    private RoleRepository roleRepository;



    // lấy tất cả danh sách Nhân Viên
    public PageDto findEmployeesAll(Integer page, Integer pageSize, String term) {

        Page<EmployeeProjection> employees = userRepository.findEmployeesAll(PageRequest.of(page - 1, pageSize), term);

        return new PageDto(
                employees.getNumber() + 1,
                employees.getSize(),
                employees.getTotalPages(),
                (int) Math.ceil(employees.getTotalElements()),
                employees.getContent()
        );
    }

    // lấy nhân viên theo id
    public EmployeeProjection findEmployeeById(Integer id) {
        return userRepository.findEmployeeById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id: " + id));
    }

    // lấy danh sách roles
    public List<RoleInfo> findRolesAll() {
        return roleRepository.findRolesAll();
    }


    // tạo nhân viên mới
    public StatusResponse createEmployee(AdminCreateEmployeeRequest request) {
        // kiểm tra xem email có tồn tại không
        if (userRepository.findUsersByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists. please use another email");
        }
        // tạo user mới
        User user = User.builder()
                .employeeName(request.getFullName())
                .employeeCode(generateCode.generateCode())
                .email(request.getEmail())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .roles(roleRepository.findAllById(request.getRoleIds()))
                .build();
        //lưu lại
        userRepository.save(user);

        return new StatusResponse(HttpStatus.CREATED,
                "Create Employee success",
                DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));

    }

    // cập nhật thông tin nhân viên theo id
    public StatusResponse updateInformationEmployeeById(AdminUpdateInformationEmployeeRequest request, Integer id) {
        // lấy ra user theo id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id" + id));
        // kiểm tra email đã tồn tại chưa
        if (userRepository.findUsersByEmail(request.getEmail()).isPresent()){
            throw new BadRequestException("Email already exists. please use another email");
        }
        // cập nhật lại thông tin user
        user.setEmployeeName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhone());
        user.setAddress(request.getAddress());
        // lưu lại
        userRepository.save(user);

        return new StatusResponse(HttpStatus.OK,
                "Update Information Employee success",
                DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));
    }

    // cập nhật mật khẩu tài khoản nhân viên theo id
    public StatusResponse updatePasswordAccEmployeeById(AdminUpdatePasswordRequest request, Integer id) {
        // lấy ra user theo id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id" + id));
        // lưu lại thay đổi mật khẩu
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // lưu lại
        userRepository.save(user);

        // gửi mật khẩu mới về mail
        emailService.sendMail(user.getEmail(), "New Password", "Mật khẩu mới của bạn là " + request.getPassword());

        return new StatusResponse(HttpStatus.OK,
                "Update password success",
                DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));
    }

    // xóa nhân viên đồng thời  khóa tài khoản nhân viên không sử dụng nữa
    public StatusResponse deleteEmployeeById(Integer id) {
        // lấy ra user theo id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found With id" + id));
        // cập nhật lại trạng thái login
        user.setEnabled(false);
        // lưu lại
        userRepository.save(user);

        return new StatusResponse(HttpStatus.NO_CONTENT,
                "Delete Employee success",
                DataMapper.toDataResponse(user.getId(), user.getEmployeeName()));
    }
}
