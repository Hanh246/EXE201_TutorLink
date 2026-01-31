package com.exe201.tutorlink.main.service;

import com.exe201.tutorlink.main.constants.RoleEnum;
import com.exe201.tutorlink.main.dto.LoginDTO;
import com.exe201.tutorlink.main.dto.UserBaseDTO;
import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.entity.Parents;
import com.exe201.tutorlink.main.entity.Students;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.entity.Users;
import com.exe201.tutorlink.main.plugin.crud.ParentCrudPlugin;
import com.exe201.tutorlink.main.plugin.crud.StudentCrudPlugin;
import com.exe201.tutorlink.main.plugin.crud.TutorCrudPlugin;
import com.exe201.tutorlink.main.plugin.mapper.UserMapperPlugin;
import com.exe201.tutorlink.main.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final ParentCrudPlugin parentCrudPlugin;
    private final StudentCrudPlugin studentCrudPlugin;
    private final TutorCrudPlugin tutorCrudPlugin;
    private final UserMapperPlugin userMapper;
    private final EmailService emailService;

    public UserDTO save(UserDTO dto) {
        var user = userMapper.toModel(dto);
        user.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));

        setupUserRoleRelation(user, dto.getRole());

        return userMapper.toDto(userRepository.save(user));
    }

    public UserBaseDTO login(LoginDTO dto) {
        return userRepository.findByEmail(dto.getEmail())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPasswordHash()))
                .map(this::getUserDetailsByRole)
                .orElse(null);
    }

    public void requestRegistration(UserDTO dto) {
        String otp = emailService.generateOTP(dto.getEmail());
        emailService.sendOtpEmail(dto.getEmail(), otp);
    }

    public UserDTO verifyAndSave(UserDTO dto, String otp) {
        if (emailService.validateOTP(dto.getEmail(), otp)) {
            UserDTO savedUser = save(dto);
            emailService.clearOTP(dto.getEmail());
            return savedUser;
        } else {
            throw new RuntimeException("OTP_INVALID:Mã OTP không chính xác hoặc đã hết hạn!");
        }
    }

    private void setupUserRoleRelation(Users user, RoleEnum role) {
        if (role == null) return;

        switch (role) {
            case PARENT -> {
                Parents parent = new Parents();
                parent.setUser(user);
                user.setParent(parent);
            }
            case STUDENT -> {
                Students student = new Students();
                student.setUser(user);
                user.setStudent(student);
            }
            case TUTORS -> {
                Tutors tutor = new Tutors();
                tutor.setUser(user);
                user.setTutor(tutor);
            }
        }
    }

    private UserBaseDTO getUserDetailsByRole(Users user) {
        return switch (user.getRole()) {
            case PARENT -> parentCrudPlugin.read(user.getId()).orElse(null);
            case STUDENT -> studentCrudPlugin.read(user.getId()).orElse(null);
            case TUTORS -> tutorCrudPlugin.read(user.getId()).orElse(null);
            default -> null;
        };
    }
}
