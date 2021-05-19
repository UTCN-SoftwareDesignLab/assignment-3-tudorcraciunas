package com.example.assig3.consultation;

import com.example.assig3.consultation.dto.ConsultationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assig3.UrlMapping.CONSULTATION;
import static com.example.assig3.UrlMapping.ENTITY;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDto> findAllConsultations(){
        return consultationService.findAllDto();
    }

    @PostMapping
    public ConsultationDto create(@RequestBody ConsultationDto consultationDto){
        return consultationService.save(consultationDto);
    }

    @PatchMapping(ENTITY)
    public ConsultationDto edit(@RequestBody ConsultationDto consultationDto, @PathVariable Long id){
        return consultationService.update(id, consultationDto);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        consultationService.deleteById(id);
    }

}
