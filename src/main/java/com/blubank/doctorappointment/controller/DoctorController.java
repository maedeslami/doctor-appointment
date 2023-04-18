package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.GeneralResponse;
import com.blubank.doctorappointment.dto.doctorDto.DoctorOpenTimeSlotResponse;
import com.blubank.doctorappointment.dto.doctorDto.OpenTimeDto;
import com.blubank.doctorappointment.service.doctor.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorService service;

    @ApiResponse(responseCode = "200", description = "Open Time is added", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Date.class))})
    @PostMapping(path = "/addTime")
    public ResponseEntity<GeneralResponse> addOpenTimes(@RequestBody OpenTimeDto request) throws Exception {
        service.addTime(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(GeneralResponse.success());
    }

    @ApiResponse(responseCode = "200", description = "Found the Open Time Slots for a day",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DoctorOpenTimeSlotResponse.class))})
    @GetMapping(path = "/AllopenTime")
    public ResponseEntity<List<DoctorOpenTimeSlotResponse>> getAllOpenTimes() {
        var allOpenTimes = service.getAllOpenTimes();
        return ResponseEntity.status(HttpStatus.OK).body(allOpenTimes);
    }

    @Operation(summary = "Delete open time slots of a doctor by doctorId and openTimeSlot id")
    @ApiResponse(responseCode = "204", description = "Deleted the Open Time Slot",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @DeleteMapping("/{openTimeSlotId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTimeSlotByDoctorIdAndTimeSlotId(@PathVariable Long openTimeSlotId) {
        service.deleteAppointment(openTimeSlotId);
     }
}
