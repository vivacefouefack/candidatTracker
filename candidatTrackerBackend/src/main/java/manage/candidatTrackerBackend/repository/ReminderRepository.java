package manage.candidatTrackerBackend.repository;

import org.springframework.data.repository.CrudRepository;

import manage.candidatTrackerBackend.model.Reminder;

public interface ReminderRepository extends CrudRepository<Reminder, Integer>{
    
}
