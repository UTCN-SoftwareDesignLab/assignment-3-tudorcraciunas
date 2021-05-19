package com.example.assig3.consultation.mapper;

import com.example.assig3.consultation.model.Consultation;
import com.example.assig3.consultation.dto.ConsultationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings({
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),

    })
    ConsultationDto toDto(Consultation consultation);

    default String stringAsFormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return dateFormat.format(date);
    }


    Consultation fromDto(ConsultationDto consultationDto);

}
