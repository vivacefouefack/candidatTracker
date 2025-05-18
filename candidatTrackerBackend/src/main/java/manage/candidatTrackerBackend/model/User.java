package manage.candidatTrackerBackend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a user of the application.
 * Each user can manage multiple job applications.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(unique = true) 
    private String username;

    @NotEmpty
    @Size(min=6)
    private String password;

    @NotEmpty
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Candidate> candidate = new ArrayList<>();

}
