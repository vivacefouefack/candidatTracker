package manage.candidatTrackerBackend.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

public class ReminderTest extends Validate {
    

    private Reminder createValidReminder() {
        Candidate candidate = new Candidate(); 
        candidate.setCompanyName("Google");
        candidate.setJobTitle("Software Engineer");
        candidate.setApplicationDate(LocalDateTime.now().toLocalDate());
        candidate.setStatus("Pending");
        candidate.setNotes("First interview");

        Reminder reminder = new Reminder();
        reminder.setCandidate(candidate);
        reminder.setReminderDate(LocalDateTime.now().plusDays(1));
        reminder.setMessage("Send email");
        reminder.setSent(false);

        return reminder;
    }

    @Test
    public void testValidReminder() {
        Reminder reminder = createValidReminder();
        Set<ConstraintViolation<Reminder>> violations = validator.validate(reminder);
        assertTrue(violations.isEmpty(), "Reminder should be valid");
    }

    @Test
    public void testReminderDateNotNull() {
        Reminder reminder = createValidReminder();
        reminder.setReminderDate(null);
        Set<ConstraintViolation<Reminder>> violations = validator.validate(reminder);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("reminderDate")));
    }

    @Test
    public void testReminderDateMustBeInFuture() {
        Reminder reminder = createValidReminder();
        reminder.setReminderDate(LocalDateTime.now().minusHours(1)); 
        Set<ConstraintViolation<Reminder>> violations = validator.validate(reminder);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("reminderDate")));
    }

    @Test
    public void testMessageNotEmpty() {
        Reminder reminder = createValidReminder();
        reminder.setMessage("");
        Set<ConstraintViolation<Reminder>> violations = validator.validate(reminder);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("message")));
    }

    @Test
    public void testCandidateNotNull() {
        Reminder reminder = createValidReminder();
        reminder.setCandidate(null);
        Set<ConstraintViolation<Reminder>> violations = validator.validate(reminder);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("candidate")));
    }

}
