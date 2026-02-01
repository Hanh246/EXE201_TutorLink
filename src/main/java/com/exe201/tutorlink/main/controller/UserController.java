package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.dto.response.BaseResponse;
import com.exe201.tutorlink.main.dto.LoginDTO;
import com.exe201.tutorlink.main.dto.UserBaseDTO;
import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.service.UserService;
import com.exe201.tutorlink.main.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator validation;

    @PostMapping("/register-request")
    public ResponseEntity<BaseResponse<String>> requestOtp(@RequestBody UserDTO dto) {
//        Map<String,String> validator = validation.validateCreate(dto);
//        if(validator != null && !validator.isEmpty()){
//            Map<String,String> errors = UserValidator.convertKeys(validator);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.<String>builder()
//                    .success(false)
//                    .data(null)
//                    .message("Đã có lỗi xảy ra")
//                    .errors(errors)
//                    .build());
//        }
        userService.requestRegistration(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(BaseResponse.<String>builder()
                        .success(true)
                        .data("Mã OTP đã được gửi đến email.")
                        .build());
    }

    @PostMapping("/register-confirm")
    public ResponseEntity<BaseResponse<UserBaseDTO>> confirmRegister(@RequestBody UserDTO dto, @RequestParam String otp) {
        UserBaseDTO created = userService.verifyAndSave(dto, otp);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.<UserBaseDTO>builder().success(true).data(created).build());
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<com.exe201.tutorlink.main.dto.UserBaseDTO>> login(@RequestBody LoginDTO dto) {
        var created = userService.login(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.<UserBaseDTO>builder().success(true).data(created).build());
    }
}
