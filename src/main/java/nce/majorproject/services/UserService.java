package nce.majorproject.services;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.UserAddRequest;
import nce.majorproject.dto.UserProfileResponse;

public interface UserService {
    Response addUser(UserAddRequest userAddRequest);
    UserProfileResponse getProfile();
}
