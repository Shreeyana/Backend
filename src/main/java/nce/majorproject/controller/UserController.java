package nce.majorproject.controller;

import lombok.extern.slf4j.Slf4j;
import nce.majorproject.constant.Route;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.UserAddRequest;
import nce.majorproject.services.UserService;
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
    @PostMapping
    public Response addUser(@Valid @RequestBody UserAddRequest request){
        log.info("adding user::{}{}",request.getFullName(),request.getEmail());
        return userService.addUser(request);
    }
}
