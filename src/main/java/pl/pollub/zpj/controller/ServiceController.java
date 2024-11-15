package pl.pollub.zpj.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pollub.zpj.DTOs.ServiceDTO;
import pl.pollub.zpj.models.Service;
import pl.pollub.zpj.services.ServiceService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/service")
@Tag(name = "Service Controller", description = "Hibernate")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    @GetMapping
    Iterable<Service> findAll(){
        return serviceService.findAll();
    }

    @PostMapping
    Service createService(@RequestBody ServiceDTO serviceDTO){
        return serviceService.createService(serviceDTO);
    }

    @PatchMapping(path = "/{id}")
    Service updateService(@PathVariable int id, @RequestBody ServiceDTO serviceDTO){
        try {
            return serviceService.updateService(id, serviceDTO);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    String deleteService(@PathVariable int id){
        try {
            return serviceService.deleteService(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
