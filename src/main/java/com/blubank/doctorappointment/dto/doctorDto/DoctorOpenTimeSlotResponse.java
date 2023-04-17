package com.blubank.doctorappointment.dto.doctorDto;

import com.blubank.doctorappointment.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorOpenTimeSlotResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openTimeAppointment;

    @Nullable
    private Patient patient;
}
