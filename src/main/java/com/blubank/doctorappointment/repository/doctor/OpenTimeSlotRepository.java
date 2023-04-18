package com.blubank.doctorappointment.repository.doctor;

import com.blubank.doctorappointment.model.AppointmentStatus;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenTimeSlotRepository extends JpaRepository<OpenTimeSlot, Long> {
    List<OpenTimeSlot> findAllByStatus(AppointmentStatus status);
}
