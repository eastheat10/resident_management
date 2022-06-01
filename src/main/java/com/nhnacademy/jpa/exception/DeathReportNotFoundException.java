package com.nhnacademy.jpa.exception;

public class DeathReportNotFoundException extends IllegalArgumentException {

    public DeathReportNotFoundException() {
        super("사망신고서를 찾을 수 없습니다.");
    }
}
