package pl.pollub.zpj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pollub.zpj.models.Service;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
}
