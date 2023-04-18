package com.blubank.doctorappointment.service.doctor;

import com.blubank.doctorappointment.constants.ErrorMessage;
import com.blubank.doctorappointment.dto.doctorDto.AppointmentReserveModel;
import com.blubank.doctorappointment.dto.doctorDto.AppointmentReserveResult;
import com.blubank.doctorappointment.dto.doctorDto.DoctorOpenTimeSlotResponse;
import com.blubank.doctorappointment.dto.doctorDto.OpenTimeDto;
import com.blubank.doctorappointment.exception.ElementNotFoundException;
import com.blubank.doctorappointment.exception.InvalidAppointmentTimeException;
import com.blubank.doctorappointment.mapper.OpenTimeSlotMapper;
import com.blubank.doctorappointment.model.AppointmentStatus;
import com.blubank.doctorappointment.model.OpenTime;
import com.blubank.doctorappointment.model.OpenTimeSlot;
import com.blubank.doctorappointment.repository.doctor.DoctorRepository;
import com.blubank.doctorappointment.repository.doctor.OpenTimeRepository;
import com.blubank.doctorappointment.repository.doctor.OpenTimeSlotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    OpenTimeSlotRepository openTimeSlotRepository;
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
    public List<DoctorOpenTimeSlotResponse> getAllOpenTimes() {
        List<OpenTime> allByStatus = this.openTimeRepository.findAllByStatus(AppointmentStatus.FREE);
        return OpenTimeSlotMapper.INSTANCE.toOpenTimeDTOList(allByStatus);
    }

    @Override
    public List<DoctorOpenTimeSlotResponse> getAppointmentsByCellNumber(String cellNumber) {
        return null;
    }

    @Override
    public AppointmentReserveResult reserve(AppointmentReserveModel model) throws Exception {
        return null;
    }
    @Transactional
    @Override
    public Long deleteAppointment(Long openTimeSlotId){
        var openSlot = openTimeRepository.findById(openTimeSlotId)
                .orElseThrow(() -> new ElementNotFoundException(ErrorMessage.ERROR_NOT_SLOT_EXIST + openTimeSlotId));
        if (openSlot.getStatus().equals(AppointmentStatus.DELETED)) {
            throw new ElementNotFoundException(ErrorMessage.ERROR_NOT_SLOT_EXIST + openTimeSlotId);
        }

        if (openSlot.getStatus().equals(AppointmentStatus.TAKEN)) {
            throw new HttpServerErrorException(HttpStatus.NOT_ACCEPTABLE,ErrorMessage.ERROR_SLOT_IS_BOOKED);
        }

        openSlot.setStatus(AppointmentStatus.DELETED);
        openTimeRepository.deleteById(openTimeSlotId);
        return openTimeSlotId;
    }
}
