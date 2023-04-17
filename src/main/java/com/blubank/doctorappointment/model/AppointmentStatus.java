package com.blubank.doctorappointment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppointmentStatus {
    FREE,
    TAKEN,
    DELETED

}
