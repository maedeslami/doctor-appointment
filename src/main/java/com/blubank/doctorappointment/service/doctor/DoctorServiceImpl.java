package com.blubank.doctorappointment.service.doctor;

import com.blubank.doctorappointment.constants.ErrorMessage;
import com.blubank.doctorappointment.dto.doctorDto.DoctorOpenTimeSlotResponse;
import com.blubank.doctorappointment.dto.doctorDto.OpenTimeDto;
import com.blubank.doctorappointment.exception.InvalidAppointmentTimeException;
import com.blubank.doctorappointment.mapper.OpenTimeSlotMapper;
import com.blubank.doctorappointment.model.AppointmentStatus;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.repository.doctor.DoctorRepository;
import com.blubank.doctorappointment.repository.doctor.OpenTimeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    OpenTimeRepository openTimeRepository;
    @Autowired
    ObjectMapper mapper;

    private static final Integer durationInMinutes = 30;

    @Transactional
    @Override
    public void addTime(OpenTimeDto openTimeDto) throws Exception {

        if (openTimeDto.getStartTime().isAfter(openTimeDto.getEndTime())) {
            throw new InvalidAppointmentTimeException(ErrorMessage.INVALID_APPOINTMENT_TIME);
        }
        if (openTimeDto.getStartTime().plusMinutes(durationInMinutes).isAfter(openTimeDto.getEndTime())) {
            throw new InvalidAppointmentTimeException(ErrorMessage.ERROR_FOR_GENERATION);
        }
        ArrayList<LocalDateTime> doctorTimes = new ArrayList<>();
        for (LocalDateTime date = openTimeDto.getStartTime(); date.isBefore(openTimeDto.getEndTime()); date = date.plusMinutes(durationInMinutes)) {
            if (!date.plusMinutes(durationInMinutes).isAfter(openTimeDto.getEndTime()))
                doctorTimes.add(date);
        }

        List<OpenTime> collect = doctorTimes.stream().map(date -> {
            OpenTime openTime = new OpenTime();
            openTime.setStartTime(date);
            openTime.setEndTime(date.plusMinutes(durationInMinutes));
            openTime.setStatus(AppointmentStatus.FREE);
            return openTime;
        }).collect(Collectors.toList());
        openTimeRepository.saveAll(collect);
    }

    @Override
    public List<DoctorOpenTimeSlotResponse> getAllOpenTimesForToday(LocalDateTime date) {
        var openTimeSlots = this.openTimeRepository.findAllByDateBetweenAndStatus(date.with(LocalTime.MIN), date.with(LocalTime.MAX), AppointmentStatus.FREE);
        return OpenTimeSlotMapper.INSTANCE.toOpenTimeSlotDTOList(openTimeSlots);
    }
}
