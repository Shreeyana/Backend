package ncit.majorproject.services;

import ncit.majorproject.dto.AdminRegisterRequest;
import ncit.majorproject.dto.CountStatResponse;
import ncit.majorproject.dto.Response;
import ncit.majorproject.entities.User;

import java.util.List;

public interface AdminServices {
    Response addAdmin(AdminRegisterRequest registerRequest);
    List<User> getRegisteredUsers();

    CountStatResponse countStatResponse();
}
