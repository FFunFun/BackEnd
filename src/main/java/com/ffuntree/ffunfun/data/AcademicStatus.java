package com.ffuntree.ffunfun.data;

import lombok.Getter;

@Getter
public enum AcademicStatus {
    ATTENDING("ATTENDING"),
    GRADUATED("GRADUATED"),
    DROPOUT("DROPOUT");

    private final String status;

    AcademicStatus(String status) {
        this.status = status;
    }

}
