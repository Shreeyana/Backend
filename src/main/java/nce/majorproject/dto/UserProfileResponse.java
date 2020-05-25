package nce.majorproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    private String userName;
    private Long userId;
    private String email;
    private String address;
}
