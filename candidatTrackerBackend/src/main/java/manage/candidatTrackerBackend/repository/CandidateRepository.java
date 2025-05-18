package manage.candidatTrackerBackend.repository;

import org.springframework.data.repository.CrudRepository;

import manage.candidatTrackerBackend.model.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
    
}
