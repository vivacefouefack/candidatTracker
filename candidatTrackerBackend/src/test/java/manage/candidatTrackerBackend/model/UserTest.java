package manage.candidatTrackerBackend.model;

import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;


import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends Validate {

    @Test
    public void shouldNotValidateUserEmptyFields() {
        User user = new User(); 
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(3); 
    }

    @Test
    public void shouldDetectShortPassword() {
        User user = new User();
        user.setUsername("vivace@gmail.com");
        user.setPassword("secur"); 
        user.setRole("USER");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("password"));
    }

    @Test
    public void shouldValidateValidUser() {
        User user = new User();
        user.setUsername("vivace@gmail.com");
        user.setPassword("secure");
        user.setRole("ADMIN");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isEmpty();
    }

    @Test
    public void shouldDetectNullUsername() {
        User user = new User();
        user.setPassword("securemypass");
        user.setRole("ADMIN");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("username"));
    }

    @Test
    public void shouldDetectNullRole() {
        User user = new User();
        user.setUsername("vivace@gmail.com");
        user.setPassword("securemypass");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("role"));
    }
}
