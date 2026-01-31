package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.dto.response.BaseResponse;
import com.exe201.tutorlink.main.dto.LoginDTO;
import com.exe201.tutorlink.main.dto.UserBaseDTO;
import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.service.UserService;
import com.exe201.tutorlink.main.validator.UserValidator;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator validation;

    @PostMapping
    public ResponseEntity<BaseResponse<UserDTO>> create(@Valid @RequestBody UserDTO dto) {
        Map<String,String> validator = validation.validateCreate(dto);
        if(validator != null && !validator.isEmpty()){
            Map<String,String> errors = UserValidator.convertKeys(validator);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.<UserDTO>builder()
                    .success(false)
                    .data(null)
                    .message("Đã có lỗi xảy ra")
                    .errors(errors)
                    .build());
        }
        UserDTO created = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.<UserDTO>builder().success(true).data(created).build());
    }

    @GetMapping("/login")
    public ResponseEntity<BaseResponse<com.exe201.tutorlink.main.dto.UserBaseDTO>> login(@ParameterObject LoginDTO dto) {
        var created = userService.login(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.<UserBaseDTO>builder().success(true).data(created).build());
    }
}
