package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.GeneralResponse;
import com.blubank.doctorappointment.dto.doctorDto.AppointmentGenerationRequest;
import com.blubank.doctorappointment.model.ResponseStatus;
import com.blubank.doctorappointment.repository.doctor.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DoctorControllerTest extends AbstractBaseIT {
    @Autowired
    DoctorRepository doctorRepository;

    @Test
    void generateDailyAppointments() {
        var request = new AppointmentGenerationRequest();
        request.setStartTime(LocalDateTime.now());
        request.setEndTime(LocalDateTime.now().plusHours(12));

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        var url = getBaseUrl() + "/api/addTime";

        ResponseEntity<GeneralResponse> response = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<>(request, headers), GeneralResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(ResponseStatus.SUCCESS);
        assertThat(response.getBody().isSuccess()).isTrue();

        assertThat(doctorRepository.findAll()).isNotEmpty();

        doctorRepository.findAll().forEach(appointment -> {
            assertThat(appointment).isNotNull();
            assertThat(appointment.getDate()).isNotNull();

        });
    }
    @Test
    @DisplayName("delete appointment - invalid appointment id - return error")
    void deleteAppointment1() {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        var url = getBaseUrl() + "/api/" + UUID.randomUUID();
        ResponseEntity<GeneralResponse> response = restTemplate.exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(headers), GeneralResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(ResponseStatus.APPOINTMENT_NOT_FOUND);
    }

}