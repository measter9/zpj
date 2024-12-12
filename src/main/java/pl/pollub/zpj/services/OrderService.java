package pl.pollub.zpj.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.DTOs.OrderDTO;
import pl.pollub.zpj.models.Orders;
import pl.pollub.zpj.repository.KamperRepository;
import pl.pollub.zpj.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    KamperRepository kamperRepository;

    public Iterable<Orders> findByKamperId(int id){
        return orderRepository.findByKamperId(id);
    }

    public Iterable<Orders> findAll(){
        return orderRepository.findAll();
    }
    public Orders createOrder(OrderDTO orderDTO){
        Orders o = orderDTO.toEntity();
        o.setKamper( kamperRepository.findById(orderDTO.getKamperid()));
        orderRepository.save(o);
        return o;
    }
    public Orders updateOrder(int id, OrderDTO orderDTO) throws ChangeSetPersister.NotFoundException {
        Orders o = orderRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        o.setKamper(kamperRepository.findById(orderDTO.getKamperid()));
        o.setDuration(orderDTO.getDuration());
        orderRepository.save(o);
        return o;
    }

    public String deleteOrder(int id)throws ChangeSetPersister.NotFoundException {
            orderRepository.delete(orderRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
            return "success";
    }

}
