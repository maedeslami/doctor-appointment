package com.blubank.doctorappointment.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    SUCCESS(200),
    VALIDATION_ERROR(1001),
    FAILURE(1002),

    APPOINTMENT_INVALID_TIME(2001),
    APPOINTMENT_NOT_FOUND(2002),
    APPOINTMENT_ALREADY_TAKEN(2003);

    private final int value;

    @JsonValue
    public Integer getStatus() {
        return this.value;
    }
}
