package pl.pollub.zpj.repository;


import pl.pollub.zpj.models.Inspection;
import org.springframework.data.repository.CrudRepository;


public interface InspectionRepostiory extends CrudRepository<Inspection, Integer> {
    Iterable<Inspection> findByKamperId(int id);
}
