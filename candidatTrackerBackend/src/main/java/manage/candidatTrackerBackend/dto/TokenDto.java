package manage.candidatTrackerBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manage.candidatTrackerBackend.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDto {
    private Integer id;
    private String value;
    private boolean expire;
    private User user;
}
