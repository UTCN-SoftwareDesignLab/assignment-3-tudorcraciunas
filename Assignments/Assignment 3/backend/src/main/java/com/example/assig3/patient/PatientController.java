package com.example.assig3.patient;


import com.example.assig3.patient.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assig3.UrlMapping.*;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @GetMapping(value = FIRST_TEST)
    public String test(){
        patientService.addDummyData();
        return "Working test!";
    }

    @GetMapping
    public List<PatientDto> findAll(){
        return patientService.findAllDto();
    }

    @PostMapping
    public PatientDto create(@RequestBody PatientDto patientDto){
        return patientService.create(patientDto);
    }

    @PatchMapping(ENTITY)
    public PatientDto update(@RequestBody PatientDto patientDto, @PathVariable Long id){
        return patientService.edit(id, patientDto);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        patientService.delete(id);
    }


}
