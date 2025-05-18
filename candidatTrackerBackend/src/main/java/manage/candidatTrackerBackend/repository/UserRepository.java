package manage.candidatTrackerBackend.repository;

import org.springframework.data.repository.CrudRepository;

import manage.candidatTrackerBackend.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
    
}
