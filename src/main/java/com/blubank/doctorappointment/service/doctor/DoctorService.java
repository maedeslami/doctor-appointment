package com.blubank.doctorappointment.service.doctor;

import com.blubank.doctorappointment.dto.doctorDto.*;

import java.util.Date;
import java.util.List;


public interface DoctorService {
    void addTime(OpenTimeDto doctor) throws Exception ;
    List<DoctorOpenTimeSlotResponse> getAllOpenTimes();
    List<DoctorOpenTimeSlotResponse> getAppointmentsByCellNumber(String cellNumber);

    AppointmentReserveResult reserve(AppointmentReserveModel model) throws Exception;

    Long deleteAppointment(Long appointmentId) ;
}
