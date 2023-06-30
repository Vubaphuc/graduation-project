package com.example.graduationprojectbe.service.auth;

import com.example.graduationprojectbe.exception.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class CheckFiles {

    // kiểm tra file xem có đúng yêu cầu và các thông số không
    public void validataFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // kiểm tra tên file có trống không
        if (fileName == null || fileName.isEmpty()) {
            // nếu trống thì trả về lỗi
            throw new BadRequestException("Tên file không hợp lệ");
        }


        // avatar.png, image.jpg => lấy ra đuôi file png và jpg
        // lấy ra đuôi ffile
        String fileExtension = getFileExtension(fileName);

        // kiểm tra type (loại) file có nằm trong danh sánh cho phép hay không
        if (!checkFileExtension(fileExtension)) {
            // nếu đuôi file không hợp lệ thì trả về lỗi
            throw new BadRequestException("Type File không hợp lệ");
        }

        // kiểm tra kích thước file có trong phạm vi cho phép upload không
        // 1048576 = 1MB
        double fileSize = (double) file.getSize() / 1048576;
        // kiểm tra dung lượng file có lớn hơn 2MB không
        if (fileSize > 2) {
            throw new BadRequestException("Size File không được vượt quá 2MB");
        }
    }


    // lấy ra đuôi ffile
    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    // kiểm tra đuôi file có theo yêu cầu không. có thì trả về true không trả về false
    private boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpeg", "jpg");
        return fileExtensions.contains(fileExtension);

    }
}
