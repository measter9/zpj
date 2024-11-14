package pl.pollub.zpj.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pollub.zpj.DTOs.InspectionDto;
import pl.pollub.zpj.models.Inspection;
import pl.pollub.zpj.repository.InspectionRepostiory;
import pl.pollub.zpj.services.InspectionService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/inspection")
@RequiredArgsConstructor
public class InspectionController {

    @Autowired
    InspectionService inspectionService;

    @GetMapping(path = "/{id}" )
    Iterable<Inspection> getInspectionsForVehicleId(@PathVariable int id){
        return inspectionService.findByKamperId(id);
    }
    @GetMapping
    public @ResponseBody Iterable<Inspection> getAllInspections(){
        return inspectionService.findAll();
    }
    @PostMapping
    public @ResponseBody Inspection createInspection(@RequestBody InspectionDto inspectionDto){
        return inspectionService.createInspection(inspectionDto);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteInspection(@PathVariable int id)  {
        try {
            return inspectionService.deleteInspection(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @PatchMapping(path = "/{id}")
    public @ResponseBody Inspection updateInspection(@PathVariable int id ,@RequestBody InspectionDto inspectionDto) {
        try {
            return inspectionService.updateInspection(id, inspectionDto);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
