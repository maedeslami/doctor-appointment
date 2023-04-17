package com.blubank.doctorappointment.dto.doctorDto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class OpenTimeDto {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "IRST")
     private LocalDateTime date;


    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "IRST")
     private LocalDateTime startTime;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "IRST")
     private LocalDateTime endTime;



}
