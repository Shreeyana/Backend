package nce.majorproject.services;

import nce.majorproject.dto.AuthRequest;
import nce.majorproject.dto.AuthResponse;

public interface AuthService {

    AuthResponse authenticateUser(AuthRequest request);
    AuthResponse authenticateAdmin(AuthRequest request );
}
