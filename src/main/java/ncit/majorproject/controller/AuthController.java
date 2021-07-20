package ncit.majorproject.controller;

import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.constant.Route;
import ncit.majorproject.dto.AuthRequest;
import ncit.majorproject.dto.AuthResponse;
import ncit.majorproject.dto.UserAuthResponse;
import ncit.majorproject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Route.Auth)
@Slf4j
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/admin")
    public AuthResponse authenticateAdmin(@Valid @RequestBody AuthRequest request){

        log.info("authenticating admin::{}",request.getUserName());

        return authService.authenticateAdmin(request);
    }
    @PostMapping(value = "/user")
    public UserAuthResponse authenticateUser(@Valid @RequestBody AuthRequest request){
        log.info("authenticating user::{}",request.getUserName());
        //log.info("Encode: ", SecurityUtil.encode("slap4msth").toString());
        return authService.authenticateUser(request);
    }
}
