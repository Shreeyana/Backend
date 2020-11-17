package nce.majorproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nce.majorproject.entities.User;

@Getter
@Builder
public class UserAuthResponse {
    private String accessToken;
    private User user;
}
