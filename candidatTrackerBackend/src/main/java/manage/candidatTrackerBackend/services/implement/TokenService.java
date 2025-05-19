package manage.candidatTrackerBackend.services.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manage.candidatTrackerBackend.dto.TokenDto;
import manage.candidatTrackerBackend.model.JwToken;
import manage.candidatTrackerBackend.repository.TokenDb;


@Service
public class TokenService {
    
    @Autowired private TokenDb tokenDb;

    /**
     * Adds a new JWT token to the database.
     *
     * @param dto the data transfer object containing the token details to be added.
    */
    public void addToken(TokenDto dto) {
        JwToken newToken = new JwToken();
        newToken.setValue(dto.getValue());
        newToken.setExpire(dto.isExpire());
        newToken.setUser(dto.getUser());
        tokenDb.save(newToken);
    }

    /**
     * Finds a JWT token by its value.
     *
     * @param token the value of the token to find.
     * @return the JwToken with the specified value.
    */
    public JwToken findByValue(String token) {
        return tokenDb.findByValue(token).get();
    }

    /**
     * Updates an existing JWT token in the database.
     *
     * @param token the JwToken to update.
    */
    public void updateToken(JwToken token) {
        tokenDb.save(token);
    }

    /**
     * Finds a JWT token by the user's username and its expiration status.
     *
     * @param username the username of the user associated with the token.
     * @param expire the expiration status of the token.
     * @return an Optional containing the found JwToken, if any.
    */
    public Optional<JwToken> findByUserUsernameAndExpire(String username, boolean expire) {
        return tokenDb.findByUserUsernameAndExpire(username, expire);
    }

    /**
     * Deletes all expired JWT tokens from the database.
     *
     * @param expire the expiration status of the tokens to delete.
    */
    public void deleteAllExpire(boolean expire) {
        tokenDb.deleteByExpire(expire);
    }

    /**
     * Sets the expiration status of a JWT token for a specific user.
     *
     * @param userName the username of the user associated with the token.
     * @param newValue the new expiration status to set.
    */
    public void setExpire(String userName, boolean newValue) {
        tokenDb.updateExpireField(userName, newValue, false);
    }
}
