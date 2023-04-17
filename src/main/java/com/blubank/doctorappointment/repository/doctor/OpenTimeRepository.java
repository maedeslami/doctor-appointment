package com.blubank.doctorappointment.repository.doctor;

import com.blubank.doctorappointment.dto.doctorDto.DoctorOpenTimeResponse;
import com.blubank.doctorappointment.model.AppointmentStatus;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OpenTimeRepository extends JpaRepository<OpenTime, Long> {
    List<OpenTimeSlot> findAllByDateBetweenAndStatus(LocalDateTime start, LocalDateTime end,
                                                     AppointmentStatus status);
}
