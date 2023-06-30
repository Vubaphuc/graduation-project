package com.example.graduationprojectbe.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    // trường hợp Not Found - 404
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handlerNotFoundException (NotFoundException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,e.getMessage());
        // lưu thông tin vào file hoặc database
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    // trường hợp BAD Request - 400
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handlerBadRequestException (BadRequestException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        // lưu thông tin vào file hoặc database
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    // Các trường hợp còn lại - 500
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handlerOtherException (Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        // lưu thông tin vào file hoặc database
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // xử lý trường hợp các biến bị rỗng hoặc null
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST,errors);
        // lưu thông tin vào file hoặc database
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
