package ncit.majorproject.services;

import ncit.majorproject.dto.AuthRequest;
import ncit.majorproject.dto.AuthResponse;
import ncit.majorproject.dto.UserAuthResponse;

public interface AuthService {

    UserAuthResponse authenticateUser(AuthRequest request);
    AuthResponse authenticateAdmin(AuthRequest request );
}
