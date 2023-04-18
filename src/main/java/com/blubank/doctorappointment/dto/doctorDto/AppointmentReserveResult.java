package com.blubank.doctorappointment.dto.doctorDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppointmentReserveResult {

    private String appointmentId;

    public static AppointmentReserveResult of(String appointmentId) {
        var result = new AppointmentReserveResult();
        result.setAppointmentId(appointmentId);
        return result;
    }

}
