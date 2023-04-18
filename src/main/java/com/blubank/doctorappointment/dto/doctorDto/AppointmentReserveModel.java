package com.blubank.doctorappointment.dto.doctorDto;

import com.blubank.doctorappointment.dto.patientDto.PatientDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppointmentReserveModel {

    private PatientDto patient;

    private String appointmentId;

}
