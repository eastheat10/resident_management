package com.nhnacademy.jpa.exception;

public class CertificationNotFoundException extends IllegalArgumentException {
    public CertificationNotFoundException() {
        super("해당 증명서를 찾을 수 없습니다.");
    }
}
