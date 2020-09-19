package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.UserAddRequest;
import nce.majorproject.dto.UserProfileResponse;
import nce.majorproject.entities.User;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.UserRepository;
import nce.majorproject.services.UserService;
import nce.majorproject.util.DateUtil;
import nce.majorproject.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContextHolderServices contextHolderServices;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,ContextHolderServices contextHolderServices) {
        this.userRepository = userRepository;
        this.contextHolderServices=contextHolderServices;
    }

    @Override
    public Response addUser(UserAddRequest userAddRequest) {
       verifyUserAge(DateUtil.stringToDate(userAddRequest.getDob()));
       User user=prepareUserAddRequest(userAddRequest);
       User response=userRepository.save(user);
       return Response.builder().status("SUCCESS").id(response.getId()).build();
    }
private void verifyUserAge(LocalDate dob){
        if(dob.isAfter(LocalDate.now().minusYears(13))){
            throw new RestException("Your age should be greater than 13");
        }
}
    @Override
    public UserProfileResponse getProfile() {
//        System.out.println(contextHolderServices.getContext().getId());
        User user=userRepository.getUserProfile(contextHolderServices.getContext().getId());
        return UserProfileResponse.builder().address(user.getAddress()).gender(user.getGender()).userId(user.getId()).userName(user.getFullName()).build();
    }

    private User prepareUserAddRequest(UserAddRequest request){
        User user=new User();

        user.setAddedDate(LocalDateTime.now());
        user.setAddress(request.getAddress());
        user.setDob(DateUtil.stringToDate(request.getDob()));
        user.setGender(request.getGender());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setPasword(SecurityUtil.encode(request.getPassword()));
        user.setUserName(request.getUserName());

        return user;
    }
    @Override
    public User validateUser(Long userId){

        Optional<User> validate=userRepository.validateUserById(userId);

        return validate.orElseThrow(()->new RestException("invalid user id"));
    }
}
