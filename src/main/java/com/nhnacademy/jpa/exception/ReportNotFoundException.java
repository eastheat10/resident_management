package com.nhnacademy.jpa.exception;

public class ReportNotFoundException extends IllegalArgumentException {
    public ReportNotFoundException() {
        super("해당 신고를 찾을 수 없습니다.");
    }
}
