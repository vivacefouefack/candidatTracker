package manage.candidatTrackerBackend.services.interfaces;

import java.util.List;

import manage.candidatTrackerBackend.dto.UserDto;
import manage.candidatTrackerBackend.model.Candidate;
import manage.candidatTrackerBackend.model.User;

/**
 * Interface defining the operations related to user management.
 */
public interface IUserService {
    
   /**
     * Registers a new user with the given username and password.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @return A UserDto object representing the registered user.
     */
    UserDto register(String username, String password);

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return A link UserDto object representing the logged-in user.
     */
    UserDto login(String username, String password);

    /**
     * Updates the profile information of an existing user.
     *
     * @param userId      The ID of the user to update.
     * @param username    The new username.
     * @param newPassword The new password.
     * @return A UserDto object representing the updated user.
     */
    UserDto updateProfile(int userId, String username, String newPassword);

    /**
     * Retrieves the list of candidates associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of Candidate objects linked to the user.
     */
    List<Candidate> getMyCandidates(int userId);

    User loadUserByUserName(String username);
}
