package com.nhnacademy.jpa.exception;

public class FamilyRelationshipNotFoundException extends IllegalArgumentException {
    public FamilyRelationshipNotFoundException() {
        super("해당 가족관계를 찾을 수 없습니다.");
    }
}
