package nce.majorproject.services.impl;

import nce.majorproject.dto.AdminRegisterRequest;
import nce.majorproject.dto.Response;
import nce.majorproject.entities.Admin;
import nce.majorproject.repositories.AdminRepository;
import nce.majorproject.services.AdminServices;
import nce.majorproject.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminServices {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Response addAdmin(AdminRegisterRequest registerRequest) {
       Admin admin=prepareAdminAddRequest(registerRequest);
       Admin response=adminRepository.save(admin);
       return Response.builder().id(response.getId()).build();
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
