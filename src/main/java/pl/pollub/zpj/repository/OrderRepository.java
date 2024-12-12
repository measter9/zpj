package pl.pollub.zpj.repository;

import pl.pollub.zpj.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Integer> {
    Iterable<Orders> findByKamperId(int id);
}
