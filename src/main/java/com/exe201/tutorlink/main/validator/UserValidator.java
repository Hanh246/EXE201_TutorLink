package com.exe201.tutorlink.main.validator;

import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.entity.Users;
import com.exe201.tutorlink.main.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserValidator {
    private final IUserRepository repository;

    public UserValidator(IUserRepository repository) {
        this.repository = repository;
    }

    private static final Map<String, String> KEY_MAPPING = Map.ofEntries(
            Map.entry("Email", "Email"),
            Map.entry("Số Điện Thoại", "Phone")
    );

    public static Map<String, String> convertKeys(Map<String, String> source) {
        Map<String, String> target = new HashMap<>();
        for (Map.Entry<String, String> entry : source.entrySet()) {
            String newKey = KEY_MAPPING.getOrDefault(entry.getKey(), entry.getKey());
            target.put(newKey, entry.getValue());
        }
        return target;
    }

    public Map<String, String> validateCreate(UserDTO dto) {
        Map<String, String> errors = new HashMap<>();

        checkEmpty(errors, "Số Điện Thoại", dto.getPhone());
        if (!checkEmpty(errors, "Email", dto.getEmail())) {
            String maLinhVuc = dto.getEmail().trim();
            Optional<Users> bienPhap = repository.findByEmail(maLinhVuc);
            if (bienPhap.isPresent()) {
                errors.put("Email", "Email đã tồn tại");
            }
        }

        return errors;
    }

    public boolean checkEmpty(Map<String, String> errors, String fieldCheck, String input) {
        boolean isEmpty = false;
        if (!notNullOrEmpty(input)) {
            errors.put(fieldCheck, fieldCheck + " không được để trống");
            isEmpty = true;
        }
        return isEmpty;
    }

    private boolean notNullOrEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
