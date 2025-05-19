package manage.candidatTrackerBackend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponseDto {
    private int id;
    private String username;
    private String role;
    private List<CandidateDto> candidatures = new ArrayList<>();
}
