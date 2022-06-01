package com.nhnacademy.jpa.exception;

public class BirthReportNotFoundException extends IllegalArgumentException {

    public BirthReportNotFoundException() {
        super("출생신고서를 찾을 수 없습니다.");
    }
}
