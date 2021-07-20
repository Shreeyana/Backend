//package ncit.majorproject.recommendation.services.impl;
//
//import ncit.majorproject.entities.Product.Product;
//import ncit.majorproject.recommendation.dto.Request;
//import ncit.majorproject.recommendation.repository.RecommendationRepository;
//import ncit.majorproject.recommendation.services.RecommendationService;
//import ncit.majorproject.recommendation.services.validator.ProductValidator;
//import ncit.majorproject.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class RecommendationServiceImpl implements RecommendationService {
//
//    private ProductService productService;
//    private RecommendationRepository recommendationRepository;
//    private ProductValidator productValidator;
//    @Autowired
//    public RecommendationServiceImpl(ProductService productService,
//                                     RecommendationRepository recommendationRepository){
//        this.productService = productService;
//        this.recommendationRepository = recommendationRepository;
//    }
//    @Override
//    public List<Product> getRecommendation(Request request) {
//
//        return fetchRecommendedData(request);
//    }
//
//    private List<Product> fetchRecommendedData(Request request){
//
//        Product validProduct = productValidator.validateProduct(request.getCurrentSelectionProductId());
//        //store user pick for user-item recommendation:
//        //save data:
//        return  recommendationRepository.findByCurrentInputData(validProduct);
//    }
//}
