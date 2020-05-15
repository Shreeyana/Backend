package nce.majorproject.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponse {
    private String status;
    private String accessToken;
}
