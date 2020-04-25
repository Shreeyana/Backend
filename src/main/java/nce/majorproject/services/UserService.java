package nce.majorproject.services;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.UserAddRequest;

public interface UserService {
    Response addUser(UserAddRequest userAddRequest);
}
