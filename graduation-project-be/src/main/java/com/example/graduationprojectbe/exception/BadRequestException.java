package com.example.graduationprojectbe.exception;


// class để xử lý lỗi cái Request không giống như mình mong muốn
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
