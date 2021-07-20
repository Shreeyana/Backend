package ncit.majorproject.services.impl;

import ncit.majorproject.context.ContextHolderServices;
import ncit.majorproject.dto.AdminRegisterRequest;
import ncit.majorproject.dto.CountStatResponse;
import ncit.majorproject.dto.Response;

import ncit.majorproject.entities.Admin;
import ncit.majorproject.entities.User;
import ncit.majorproject.exception.RestException;
import ncit.majorproject.repositories.AdminRepository;
import ncit.majorproject.repositories.CartRepository;
import ncit.majorproject.repositories.UserRepository;
import ncit.majorproject.services.AdminServices;
import ncit.majorproject.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServices {

    private AdminRepository adminRepository;
    private UserRepository userRepository;
    private ContextHolderServices contextHolderServices;
    private CartRepository cartRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                            UserRepository userRepository,
                            ContextHolderServices contextHolderServices, CartRepository cartRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.contextHolderServices = contextHolderServices;
        this.cartRepository = cartRepository;
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

    @Override
    public CountStatResponse countStatResponse() {
        adminRepository.validateAdminId(contextHolderServices.getContext().getId()).orElseThrow(()->new RestException("Not a valid admin"));
        return prepareCountStatResponse();
    }

    private CountStatResponse prepareCountStatResponse() {
        return CountStatResponse
                .builder()
                .checkedOutCount(findCheckedOutCount())
                .femaleUserCount(findFemaleUserCount())
                .maleUserCount(findMaleUserCount())
                .checkOutTotalAmount(findTodayTransactionAmount())
                .totalUsers(findFemaleUserCount()+findMaleUserCount())
                .build();
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
    }
    private Double findTodayTransactionAmount() {
        Double transactionAmount =  cartRepository.findTransactionToday(LocalDateTime.of(LocalDate.now(), LocalTime.MIN),LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        if(transactionAmount==null){
            return 0d;
        }
        return transactionAmount;
    }

    private int findMaleUserCount() {
        return userRepository.findUserBasedOnGender("Male");
    }

    private int findFemaleUserCount() {
        return userRepository.findUserBasedOnGender("Female");
    }

    private Double findCheckedOutCount() {
        return  cartRepository.findCheckedOutToday(LocalDateTime.of(LocalDate.now(), LocalTime.MIN),LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
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
