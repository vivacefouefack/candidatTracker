package manage.candidatTrackerBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import manage.candidatTrackerBackend.model.JwToken;


/**
 * Repository interface for performing CRUD operations on JwToken entity.
*/


public interface TokenDb extends CrudRepository<JwToken,Integer>{

    /**
     * Finds a JwToken entity by its value.
     *
     * @param value the value of the token to be found.
     * @return an Optional containing the found JwToken, or an empty Optional if no token is found with the given value.
    */
    Optional<JwToken> findByValue(String value);
    
    /**
     * Finds a JwToken entity associated with a specific username and expiration status.
     *
     * @param username the username associated with the token.
     * @param expire the expiration status of the token.
     * @return an Optional containing the found JwToken, or an empty Optional if no token is found with the given username and expiration status.
    */
    @Query("SELECT t FROM JwToken t WHERE t.user.userName = :username AND t.expire = :expire")
    Optional<JwToken> findByUserUsernameAndExpire(String username, boolean expire);

    /**
     * Updates the expiration status of JwToken entities associated with a specific username.
     * 
     * @param username the username associated with the tokens to be updated.
     * @param newValue the new expiration status to set.
     * @param oldValue the old expiration status to be replaced.
    */
    @Modifying
    @Transactional
    @Query("UPDATE JwToken t SET t.expire = :newValue WHERE t.user.userName = :username AND t.expire = :oldValue")
    void updateExpireField(String username, boolean newValue, boolean oldValue);

    /**
     * Deletes JwToken entities based on their expiration status.
     * 
     * @param expire the expiration status of the tokens to be deleted.
     */
    void deleteByExpire(boolean expire);
} 
