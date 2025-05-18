package manage.candidatTrackerBackend.model;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
