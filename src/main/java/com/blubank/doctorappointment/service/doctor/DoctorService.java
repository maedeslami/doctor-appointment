package com.blubank.doctorappointment.service.doctor;

import com.blubank.doctorappointment.dto.doctorDto.DoctorOpenTimeResponse;
import com.blubank.doctorappointment.dto.doctorDto.DoctorOpenTimeSlotResponse;
import com.blubank.doctorappointment.dto.doctorDto.OpenTimeDto;
import com.blubank.doctorappointment.model.Doctor;

import java.time.LocalDateTime;
import java.util.List;


public interface DoctorService {
    void addTime(OpenTimeDto doctor) throws Exception ;

//    Doctor findById(Long doctorId);

    List<DoctorOpenTimeSlotResponse> getAllOpenTimesForToday(LocalDateTime date);
}
