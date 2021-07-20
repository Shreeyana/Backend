package ncit.majorproject.services;

import ncit.majorproject.dto.UserAddRequest;
import ncit.majorproject.dto.UserAddResponse;
import ncit.majorproject.dto.UserProfileResponse;
import ncit.majorproject.entities.User;

public interface UserService {
    UserAddResponse addUser(UserAddRequest userAddRequest);
    UserProfileResponse getProfile();
    User validateUser(Long userId);
}
