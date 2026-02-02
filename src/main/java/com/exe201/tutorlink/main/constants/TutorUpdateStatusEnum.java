package com.exe201.tutorlink.main.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TutorUpdateStatusEnum {
    FIRST(1, "chưa thêm profile"),
    PAGE_1(2, "Hoàn Thành trang 1"),
    PAGE_2(3, "Hoàn Thành trang 2"),
    PAGE_3(4, "Hoàn Thành trang 3"),
    PAGE_4(5, "Hoàn Thành trang 4");

    private final int value;
    private final String label;

    TutorUpdateStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int toValue() {
        return value;
    }

    public static TutorUpdateStatusEnum fromValue(int value) {
        for (var s : TutorUpdateStatusEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        throw new IllegalArgumentException("Không có giá trị: " + value);
    }
}
