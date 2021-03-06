package ncit.majorproject.controller;

import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.constant.Route;
import ncit.majorproject.dto.UserAddRequest;
import ncit.majorproject.dto.UserAddResponse;
import ncit.majorproject.dto.UserProfileResponse;
import ncit.majorproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Route.USER)
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
//    @GetMapping
//    public Response showCart(){}
    @PostMapping("/register")
    public UserAddResponse addUser(@Valid @RequestBody UserAddRequest request){
        log.info("adding user::{}{}",request.getFullName());
        return userService.addUser(request);
    }

    @GetMapping(value = "/profile")
    public UserProfileResponse getProfile(){
        log.info("getting user profile::");
        return userService.getProfile();
    }
}
