package manage.candidatTrackerBackend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a job application submitted by a user.
 * Each application is linked to a user and may have multiple reminders.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String jobTitle;

    @PastOrPresent
    private LocalDate applicationDate;

    @NotEmpty
    private String status;

    private String cvFilePath;

    private String coverLetterFilePath;

    private String jobOfferLink;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Reminder> reminders = new ArrayList<>();
}
