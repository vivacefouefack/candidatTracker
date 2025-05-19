package manage.candidatTrackerBackend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private UserDto user;
    private List<CandidateDto> candidates = new ArrayList<>();
    private String token;
}
