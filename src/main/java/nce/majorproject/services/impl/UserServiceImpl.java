package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.UserAddRequest;
import nce.majorproject.dto.UserProfileResponse;
import nce.majorproject.entities.User;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.UserRepository;
import nce.majorproject.services.UserService;
import nce.majorproject.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ContextHolderServices contextHolderServices;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,ContextHolderServices contextHolderServices) {
        this.userRepository = userRepository;
        this.contextHolderServices=contextHolderServices;
    }

    @Override
    public Response addUser(UserAddRequest userAddRequest) {
       User user=prepareUserAddRequest(userAddRequest);
       User response=userRepository.save(user);
       return Response.builder().id(response.getId()).build();
    }

    @Override
    public UserProfileResponse getProfile() {
//        System.out.println(contextHolderServices.getContext().getId());
        User user=userRepository.getUserProfile(contextHolderServices.getContext().getId());
        return UserProfileResponse.builder().address(user.getAddress()).email(user.getEmail()).userId(user.getId()).userName(user.getFullName()).build();
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
    @Override
    public User validateUser(Long userId){
    Optional<User> validate=userRepository.validateUserById(userId);
    User user=validate.orElseThrow(()->new RestException("invalid user id"));
        return user;
    }
}
