package com.exe201.tutorlink.main.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageStatus {
    SENT(1, "Gửi"),
    DELIVERED(2, "Đã Gửi");

    private final int value;
    private final String label;

    MessageStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int toValue() {
        return value;
    }

    public static RoleEnum fromValue(int value) {
        for (var s : RoleEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        throw new IllegalArgumentException("Không có giá trị: " + value);
    }
}
