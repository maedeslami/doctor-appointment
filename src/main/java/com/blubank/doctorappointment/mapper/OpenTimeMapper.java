package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.doctorDto.OpenTimeDto;
import com.blubank.doctorappointment.model.OpenTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenTimeMapper {
    OpenTimeMapper INSTANCE = Mappers.getMapper(OpenTimeMapper.class);

    OpenTime toOpenTime(OpenTimeDto openTimeDTO);

}
