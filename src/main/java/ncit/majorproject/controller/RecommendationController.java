//package ncit.majorproject.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import ncit.majorproject.constant.Route;
//import ncit.majorproject.context.ContextHolderServices;
//import ncit.majorproject.dto.product.LatestAddedProductResponse;
//import ncit.majorproject.entities.Product.Product;
//import ncit.majorproject.entities.User;
//import ncit.majorproject.recommendation.dto.CurrentSelection;
//import ncit.majorproject.recommendation.dto.NextItemInferred;
//import ncit.majorproject.recommendation.dto.UserSelectionRequest;
//import ncit.majorproject.recommendation.entity.DataSetReferer;
//import ncit.majorproject.recommendation.repository.DataSetRefererRepository;
//import ncit.majorproject.recommendation.repository.RecommendationRepository;
//import ncit.majorproject.recommendation.services.UserTracker;
//import ncit.majorproject.recommendation.services.validator.ProductValidator;
//import ncit.majorproject.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@Slf4j
//@RequestMapping(Route.BASE_URL+"/recommendation")
//public class RecommendationController {
//
//    private final DataSetRefererRepository dataSetRefererRepository;
//    private final UserService userService;
//    private final ContextHolderServices contextHolderServices;
//    private final RecommendationRepository recommendationRepository;
//    private final ProductValidator productValidator;
//    private final UserTracker userTracker;
//
//    @Autowired
//    public RecommendationController(DataSetRefererRepository dataSetRefererRepository,
//                                    UserService userService,
//                                    ContextHolderServices contextHolderServices,
//                                    RecommendationRepository recommendationRepository,
//                                    ProductValidator productValidator,
//                                    UserTracker userTracker){
//        this.dataSetRefererRepository = dataSetRefererRepository;
//        this.userService = userService;
//        this.contextHolderServices = contextHolderServices;
//        this.recommendationRepository = recommendationRepository;
//
//        this.productValidator=productValidator;
//        this.userTracker = userTracker;
//
//    }
//    @PostMapping(value = "/track-user")
//    public NextItemInferred trackUserProductData(@Valid @RequestBody UserSelectionRequest request){
//        log.info("recording user product data[details] {}",request.getLocalDateTime());
//        return  userTracker.recordUserSelection(request);
//    }
//    @GetMapping(value = "/all")
//    public List<DataSetReferer> getAll(){
//
//        return this.dataSetRefererRepository.findAll();
//    }
//
//    @PostMapping(value = "/set-recommendation")
//    public void setRecommendationTable(CurrentSelection currentSelection){
//        User user = userService.validateUser(contextHolderServices.getContext().getId());
//        mapNewRecommendation(user,currentSelection);
//    }
//    @GetMapping(value = "/hiter")
//    public List<LatestAddedProductResponse> hitter(){
//        return userTracker.hitter();
//    }
//    private void mapNewRecommendation(User user,CurrentSelection currentSelection){
//
//        Product product = productValidator.validateProduct(currentSelection.getCurrentProductId());
////        DataSetReferer dataSetReferer = new DataSetReferer();
////        dataSetReferer.setAge(getAge(user.getDob()));
////        dataSetReferer.setClothingId(String.valueOf(product.getId()));
////        dataSetReferer.setRating(TypeOfInput.valueOf(currentSelection.getTypeOfInput()).name());
////        dataSetReferer.setSubSubCategory(product.getSubSubCategory().getName());
////        this.dataSetRefererRepository.save(dataSetReferer);
//
//    }
//
//}
