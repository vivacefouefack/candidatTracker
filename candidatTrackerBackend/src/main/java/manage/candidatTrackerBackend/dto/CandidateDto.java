package manage.candidatTrackerBackend.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateDto {
    private int id;

    private String companyName;

    private String jobTitle;

    private LocalDate applicationDate;

    private String status;

    private String jobOfferLink;

    private String notes;
}
