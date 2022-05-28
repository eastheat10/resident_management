package com.nhnacademy.jpa.exception;

public class ResidentNotFound extends IllegalArgumentException {
    public ResidentNotFound() {
        super("해당 주민을 찾을 수 없습니다.");
    }
}
