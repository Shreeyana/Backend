package nce.majorproject.services;

import nce.majorproject.dto.AdminRegisterRequest;
import nce.majorproject.dto.Response;

public interface AdminServices {
    Response addAdmin(AdminRegisterRequest registerRequest);
}
