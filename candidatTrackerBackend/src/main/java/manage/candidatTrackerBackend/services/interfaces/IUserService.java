package manage.candidatTrackerBackend.services.interfaces;

import java.util.List;

import manage.candidatTrackerBackend.dto.UserDto;
import manage.candidatTrackerBackend.model.Candidate;

public interface IUserService {
    
    UserDto register(String username, String password);

    UserDto login(String username, String password);

    UserDto updateProfile(int userId, String username, String newPassword);

    List<Candidate> getMyCandidates(int userId);
}
