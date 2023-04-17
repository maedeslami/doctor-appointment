package com.blubank.doctorappointment.dto;

import com.blubank.doctorappointment.model.AppointmentStatus;
import com.blubank.doctorappointment.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.lang.Nullable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetail {


    private String appointmentId;
    //    private PatientDto patientDto;
    @Nullable
    private Patient patient;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTimeAppointment;

    private AppointmentStatus appointmentStatus;
//    private LocalDateTime date;

}
