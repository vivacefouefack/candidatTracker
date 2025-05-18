package manage.candidatTrackerBackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import manage.candidatTrackerBackend.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
    
    Optional<User> findByUsername(String username);
}
