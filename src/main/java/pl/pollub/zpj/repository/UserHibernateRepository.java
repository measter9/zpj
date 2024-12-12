package pl.pollub.zpj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pollub.zpj.models.User;
import java.util.Optional;

public interface UserHibernateRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
}
