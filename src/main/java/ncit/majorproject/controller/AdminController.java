package ncit.majorproject.controller;

import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.constant.Route;
import ncit.majorproject.dto.AdminRegisterRequest;
import ncit.majorproject.dto.CountStatResponse;
import ncit.majorproject.dto.Response;
import ncit.majorproject.entities.User;
import ncit.majorproject.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Route.ADMIN)
@Slf4j
public class AdminController {
    private final AdminServices adminServices;

    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping(value = "/add")
    public Response addAdmin(@Valid @RequestBody AdminRegisterRequest registerRequest){
        log.info("registering new admin::{}{}",registerRequest.getFullName(),registerRequest.getUserName());
        return adminServices.addAdmin(registerRequest);
    }
    @GetMapping(value = "/get-registered-users")
    public List<User> getRegisteredUsers(){
        log.info("getting registered users List");
        return  adminServices.getRegisteredUsers();
    }

    @GetMapping(value = "/count-stat")
    public CountStatResponse countStatResponse(){
        log.info("getting count stat response");
        return adminServices.countStatResponse();
    }
    //shree test123

}
