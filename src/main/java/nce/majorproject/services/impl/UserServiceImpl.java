package nce.majorproject.services.impl;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.UserAddRequest;
import nce.majorproject.entities.User;
import nce.majorproject.repositories.UserRepository;
import nce.majorproject.services.UserService;
import nce.majorproject.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response addUser(UserAddRequest userAddRequest) {
       User user=prepareUserAddRequest(userAddRequest);
       User response=userRepository.save(user);
       return Response.builder().id(response.getId()).build();
    }

    private User prepareUserAddRequest(UserAddRequest request){
        User user=new User();
        user.setAddedDate(LocalDateTime.now());
        user.setAddress(request.getAddress());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setPasword(SecurityUtil.encode(request.getPassword()));
        user.setUserName(request.getUserName());
        return user;
    }
}
