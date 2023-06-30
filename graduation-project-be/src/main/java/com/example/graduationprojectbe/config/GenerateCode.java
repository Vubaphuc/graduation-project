package com.example.graduationprojectbe.config;


import com.example.graduationprojectbe.entity.UsedCodes;
import com.example.graduationprojectbe.repository.UsedCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class GenerateCode {

    @Autowired
    private UsedCodesRepository usedCodesRepository;

    public String generateCode() {
        String code;
        do {
            // tạo mã
            code = "";
            int year = LocalDateTime.now().getYear() % 100;
            code += String.format("%02d", year);
            Random rd = new Random();
            for (int i = 0; i < 4; i++) {
                code += String.valueOf(rd.nextInt(10));
            }
            year = LocalDateTime.now().getYear() % 100;
            code += String.format("%02d", year);
        } while (usedCodesRepository.existsByCode(code));

        UsedCodes usedCode = UsedCodes.builder()
                .code(code)
                .build();

        usedCodesRepository.save(usedCode);

        return code;
    }
}
