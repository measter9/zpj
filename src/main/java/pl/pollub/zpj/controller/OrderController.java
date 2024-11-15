package pl.pollub.zpj.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pollub.zpj.DTOs.InspectionDto;
import pl.pollub.zpj.DTOs.OrderDTO;
import pl.pollub.zpj.models.Inspection;
import pl.pollub.zpj.models.Orders;
import pl.pollub.zpj.services.OrderService;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    OrderService orderService;

    @Operation(summary ="find orders by camper id")
    @GetMapping(path = "/{id}" )
    Iterable<Orders> getInspectionsForVehicleId(@PathVariable int id){
        return orderService.findByKamperId(id);
    }

    @GetMapping
    public @ResponseBody Iterable<Orders> getAllInspections(){
        return orderService.findAll();
    }
    @PostMapping
    public @ResponseBody Orders createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteOrder(@PathVariable int id)  {
        try {
            return orderService.deleteOrder(id);
        } catch (ChangeSetPersister.NotFoundException e ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @PatchMapping(path = "/{id}")
    public @ResponseBody Orders updateOrder(@PathVariable int id ,@RequestBody OrderDTO orderDTO) {
        try {
            return orderService.updateOrder(id, orderDTO);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
