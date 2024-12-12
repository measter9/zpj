package pl.pollub.zpj.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pollub.zpj.DTOs.PriceDTO;
import pl.pollub.zpj.models.Price;
import pl.pollub.zpj.services.PriceService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/price")
@Tag(name = "Price Controller", description = "Hibernate")
public class PriceController{
    @Autowired
    PriceService priceService;

    @GetMapping
    Iterable<Price> findAll(){
        return priceService.getAll();
    }

    @PostMapping
    Price createPrice(@RequestBody PriceDTO priceDTO){
        return priceService.createPrice(priceDTO);
    }

    @PutMapping(path = "/{id}")
    Price updatePrice(@PathVariable int id, @RequestBody PriceDTO priceDTO){
        try {
            return priceService.updatePric(id,priceDTO);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    String deletePrice(@PathVariable int id){
        try {
            return priceService.deletePrice(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
