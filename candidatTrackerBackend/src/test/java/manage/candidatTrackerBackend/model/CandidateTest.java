package manage.candidatTrackerBackend.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    private Candidate createValidCandidate() {
        Candidate candidate = new Candidate();
        candidate.setCompanyName("Google");
        candidate.setJobTitle("Software Engineer");
        candidate.setApplicationDate(LocalDate.now());
        candidate.setStatus("Pending");
        candidate.setCvFilePath("cv.pdf");
        candidate.setCoverLetterFilePath("letter.pdf");
        candidate.setJobOfferLink("https://www.google.com/about/careers/applications/jobs");
        candidate.setNotes("First interview");
        return candidate;
    }


    @Test
    public void testValidCandidate() {
        Candidate candidate = createValidCandidate();
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        assertTrue(violations.isEmpty(), "Candidate should be valid");
    }

    @Test
    public void testCompanyNameNotEmpty() {
        Candidate candidate = createValidCandidate();
        candidate.setCompanyName("");
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("companyName")));
    }

    @Test
    public void testJobTitleNotEmpty() {
        Candidate candidate = createValidCandidate();
        candidate.setJobTitle(null);
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("jobTitle")));
    }

    @Test
    public void testStatusNotEmpty() {
        Candidate candidate = createValidCandidate();
        candidate.setStatus("");
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("status")));
    }

    @Test
    public void testApplicationDateInPastOrPresent() {
        Candidate candidate = createValidCandidate();
        candidate.setApplicationDate(LocalDate.now().plusDays(1)); 
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("applicationDate")));
    }
}
