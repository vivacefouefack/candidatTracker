package manage.candidatTrackerBackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a reminder for a specific candidate application.
 * A reminder includes a message, a scheduled date, and a flag to indicate whether it has been sent.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @NotNull
    @Future
    private LocalDateTime reminderDate;

    @NotEmpty
    private String message;

    @NotNull
    private boolean isSent;
}
