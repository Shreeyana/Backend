package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.AdminRegisterRequest;
import nce.majorproject.dto.Response;

import nce.majorproject.entities.Admin;
import nce.majorproject.entities.User;
import nce.majorproject.repositories.AdminRepository;
import nce.majorproject.repositories.UserRepository;
import nce.majorproject.services.AdminServices;
import nce.majorproject.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServices {

    private AdminRepository adminRepository;
    private UserRepository userRepository;
    private ContextHolderServices contextHolderServices;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                            UserRepository userRepository,
                            ContextHolderServices contextHolderServices) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.contextHolderServices = contextHolderServices;
    }

    @Override
    public Response addAdmin(AdminRegisterRequest registerRequest) {
       Admin admin=prepareAdminAddRequest(registerRequest);
       Admin response=adminRepository.save(admin);
       return Response.builder().id(response.getId()).build();
    }

    @Override
    public List<User> getRegisteredUsers() {
        adminRepository.validateAdminId(contextHolderServices.getContext().getId());
        return userRepository.getAllUsers();
    }

    private Admin prepareAdminAddRequest(AdminRegisterRequest request){
        Admin admin=new Admin();
        admin.setDob(request.getDob());
        admin.setFullName(request.getFullName());
        admin.setPassword(SecurityUtil.encode(request.getPassword()));
        admin.setPhone(request.getPhone());
        admin.setUserName(request.getUserName());
        admin.setAddedDate(LocalDateTime.now());
        return admin;
    }
}
