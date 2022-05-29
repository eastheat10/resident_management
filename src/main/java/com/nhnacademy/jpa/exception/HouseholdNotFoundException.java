package com.nhnacademy.jpa.exception;

public class HouseholdNotFoundException extends IllegalArgumentException {
    public HouseholdNotFoundException() {
        super("해당 세대를 찾을 수 없습니다.");
    }
}
