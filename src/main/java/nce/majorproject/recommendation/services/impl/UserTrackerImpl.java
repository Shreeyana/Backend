package nce.majorproject.recommendation.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.entities.Product.SubSubCategory;
import nce.majorproject.entities.User;
import nce.majorproject.recommendation.constants.TypeOfInput;
import nce.majorproject.recommendation.dto.NextItemInferred;
import nce.majorproject.recommendation.dto.SubCategoryAndCountRecommender;
import nce.majorproject.recommendation.dto.UserSelectionRequest;
import nce.majorproject.recommendation.entity.DataSetReferer;
import nce.majorproject.recommendation.entity.UserProductData;
import nce.majorproject.recommendation.repository.DataSetRefererRepository;
import nce.majorproject.recommendation.repository.UserDataTrackerRepository;
import nce.majorproject.recommendation.services.UserTracker;
import nce.majorproject.services.ProductService;
import nce.majorproject.services.SubCategoryService;
import nce.majorproject.services.UserService;
import nce.majorproject.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTrackerImpl implements UserTracker {
    private final ContextHolderServices contextHolderServices;
    private final UserService userService;
    private final UserDataTrackerRepository userDataTrackerRepository;
    private final ProductService productService;
    private final DataSetRefererRepository dataSetRefererRepository;
    private final SubCategoryService subCategoryService;
    public UserTrackerImpl(ContextHolderServices contextHolderServices,
                           UserService userService,
                           UserDataTrackerRepository userDataTrackerRepository,
                           ProductService productService,
                           DataSetRefererRepository dataSetRefererRepository,
                           SubCategoryService subCategoryService){
        this.contextHolderServices = contextHolderServices;
        this.userService = userService;
        this.userDataTrackerRepository = userDataTrackerRepository;
        this.productService = productService;
        this.dataSetRefererRepository = dataSetRefererRepository;
        this.subCategoryService = subCategoryService;
    }
    @Override
    public NextItemInferred recordUserSelection(UserSelectionRequest request) {
        User user = userService.validateUser(contextHolderServices.getContext().getId());

        if(validateFirstTimeUser(user)){
            UserProductData userProductData = saveUserMapping(user,request);
            Thread thread = new Thread(() -> startRecommender(userProductData));
            thread.start();
            return  NextItemInferred.builder().recommendedItemsList(productService.showLatestAdded()).build();
        }
      UserProductData userProductData = saveUserMapping(user,request);
        Thread thread = new Thread(() -> startRecommender(userProductData));
        thread.start();
        if(user.getGender().equalsIgnoreCase("FEMALE")){//we have only data set of female user
            long age = DateUtil.getAge(user.getDob());
            long lower = (age/10);
            long upper = lower +1;
            List<DataSetReferer> groupedByAgeList = this.dataSetRefererRepository.findByAgeInterval(lower,upper);
            List<SubSubCategory> subSubCategoryList = subCategoryService.listSubSubCategory();
           long[]  countArray=new long[subSubCategoryList.size()];
            groupedByAgeList.forEach(data->{
                int count =0;
               for(SubSubCategory subSubCategory:subSubCategoryList) {
                   if(data.getSubSubCategory().equalsIgnoreCase(subSubCategory.getName())){
                       countArray[count]++;
                   }
                   count++;
               }
            });

            List<SubCategoryAndCountRecommender> recommenderSUBSUBCOUNT = new ArrayList<>();
            for(int i = 0 ;  i< subSubCategoryList.size();i++){
                SubCategoryAndCountRecommender subCategoryAndCountRecommender = new SubCategoryAndCountRecommender(subSubCategoryList.get(i) ,countArray[i]);
                recommenderSUBSUBCOUNT.add(subCategoryAndCountRecommender);
            }
            List<SubCategoryAndCountRecommender> sortedList =recommenderSUBSUBCOUNT
                    .stream()
                    .sorted(Comparator
                            .comparingLong(SubCategoryAndCountRecommender::getCount)
                            .reversed())
                    .collect(Collectors.toList());

           return createRecommendationMapper(sortedList,userProductData);


        }
        return  NextItemInferred.builder().recommendedItemsList(productService.showLatestAdded()).build();
    }
    private NextItemInferred createRecommendationMapper(List<SubCategoryAndCountRecommender> sortedList,
                                                        UserProductData userProductData){
       //todo
        long count =0;
        int index=0;
        for(int i=0; i<sortedList.size();i++) {
           if( userProductData.getProductId().getSubSubCategory().equals(sortedList.get(i).getSubSubCategory())){
                count = sortedList.get(i).getCount();
                index=i;
                break;
           }
        }
        for(int i=0;i<sortedList.size();i++){
            if(sortedList.get(i).getCount()>count){
                sortedList.get(i).setApproved(true);
            }
            if(index+3< sortedList.size()){
            if(i<index+3){
                sortedList.get(i).setApproved(true);
            }}
        }
        sortedList.forEach(sortList->{
            if(sortList.isApproved()){
              //setscore for the product . score amplify by current selection subcategory match findProductBySubSubCategory();
            }
        });

        //map user subsubcategory choice to

        return   NextItemInferred.builder().recommendedItemsList(productService.showLatestAdded()).build();
    }

    private void startRecommender(UserProductData userProductData){


        this.dataSetRefererRepository.save(prepareDataSetReferer(userProductData));
    }
    private DataSetReferer prepareDataSetReferer(UserProductData userProductData){
        DataSetReferer dataSetReferer = new DataSetReferer();
        dataSetReferer.setSubSubCategory(userProductData.getProductId().getSubSubCategory().getName());
        dataSetReferer.setClothingId(String.valueOf(userProductData.getProductId().getId()));
        Long userAge = DateUtil.getAge(userProductData.getUserId().getDob());
        dataSetReferer.setAge(userAge);
        Double rating = calculateUserChoiceToRating(userAge,userProductData.getSelectionParam());
        dataSetReferer.setRating(String.valueOf(rating));
        return  dataSetReferer;
    }
    private Double calculateUserChoiceToRating(Long userAge, TypeOfInput selectionParam){
return 0.0;
    }
    private UserProductData saveUserMapping(User user,UserSelectionRequest request){
        UserProductData userProductData = userProductMapping(user,request);
        return this.userDataTrackerRepository.save(userProductData);
    }
    private Boolean validateFirstTimeUser(User user){
        List<UserProductData> userProductDataList =  this.userDataTrackerRepository.findByUserId(user);
    return  !(userProductDataList.size()>0);
    }
    private UserProductData userProductMapping(User user, UserSelectionRequest request){
        UserProductData userProductData = new UserProductData();
        userProductData.setProductId(productService.validateProduct(request.getProductId()));
        userProductData.setUserId(user);
        userProductData.setSelectionParam(TypeOfInput.valueOf(request.getSelectionParam()));
        userProductData.setSelectionStamp(DateUtil.stringToDateTime(request.getLocalDateTime()));
        return  userProductData;
    }
}
