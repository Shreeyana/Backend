package ncit.majorproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.context.ContextHolderServices;
import ncit.majorproject.dto.AddReviewRatingRequest;
import ncit.majorproject.dto.IdResponse;
import ncit.majorproject.dto.Reviews;
import ncit.majorproject.dto.product.RatingResponse;
import ncit.majorproject.entities.Product.Product;
import ncit.majorproject.entities.ReviewRating;
import ncit.majorproject.entities.User;
import ncit.majorproject.exception.RestException;
//import ncit.majorproject.recommendation.entity.DataSetReferer;
//import ncit.majorproject.recommendation.repository.DataSetRefererRepository;
import ncit.majorproject.repositories.ReviewRatingRepository;
import ncit.majorproject.services.ProductService;
import ncit.majorproject.services.ReviewRatingService;
import ncit.majorproject.services.UserService;
import ncit.majorproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewRatingServiceImpl implements ReviewRatingService {


    private ReviewRatingRepository reviewRatingRepository;
    private ContextHolderServices contextHolderServices;
    private UserService userService;
    private ProductService productService;
//    private DataSetRefererRepository dataSetRefererRepository;

    @Autowired
    public ReviewRatingServiceImpl(ReviewRatingRepository reviewRatingRepository,
                                   ContextHolderServices contextHolderServices,
                                   UserService userService,
                                   ProductService productService) {
        this.reviewRatingRepository = reviewRatingRepository;
        this.contextHolderServices = contextHolderServices;
        this.userService = userService;
        this.productService = productService;
//        this.dataSetRefererRepository = dataSetRefererRepository;
    }

    @Override
    public IdResponse addReview(AddReviewRatingRequest reviewRequest) {
        ReviewRating review=prepareToAddReview(reviewRequest);
        if(!userHasAlreadyRatedProduct(review)){

        ReviewRating response=reviewRatingRepository.save(review);
//        Thread thread = new Thread(() -> addDataToDataset(response));
//        thread.start();
        return IdResponse.builder().id(response.getId()).build();
        }else{
            throw new RestException("Rating has already been done");
        }
    }
    private boolean userHasAlreadyRatedProduct(ReviewRating review){
       return reviewRatingRepository.findByUserAndProduct(review.getReviewDoneBy(),review.getReviewDoneOn()).isPresent();
    }
//    private void addDataToDataset(ReviewRating reviewRating){
////        DataSetReferer dataSetReferer = new DataSetReferer();
//        dataSetReferer.setSubSubCategory(reviewRating.getReviewDoneOn().getSubSubCategory().getName());
//        dataSetReferer.setClothingId("WARDROBE"+reviewRating.getId());
//        dataSetReferer.setAge(DateUtil.getAge(reviewRating.getReviewDoneBy().getDob()));
//        dataSetReferer.setRating(String.valueOf(reviewRating.getRating()));
//        this.dataSetRefererRepository.save(dataSetReferer);
//    }

    @Override
    public List<Reviews> listProductReviews(Long productId) {
        Product product=productService.validateProduct(productId);
        List<ReviewRating> reviewList=reviewRatingRepository.findProductReviews(product);
        List<Reviews> reviews=new ArrayList<>();
        reviewList.forEach(reviewRating -> {
            Reviews response=findMyReviews(reviewRating);
            reviews.add(response);
        });
        return reviews;
    }

    Reviews findMyReviews(ReviewRating review){
        Reviews response=new Reviews();
        response.setAddedDate(review.getAddedDate());
        response.setRating(review.getRating());
        response.setReviewId(review.getId());
        response.setReview(review.getContent());
        response.setUserId(review.getReviewDoneBy().getId());
        response.setUserName(review.getReviewDoneBy().getFullName());
        response.setProductId(review.getReviewDoneOn().getId());
        return response;
    }

    private ReviewRating prepareToAddReview(AddReviewRatingRequest reviewRequest){
        User reviewDoneBy=userService.validateUser(contextHolderServices.getContext().getId());
        Product reviewDoneTo=productService.validateProduct(reviewRequest.getReviewDoneTo());
        Optional<ReviewRating> optionalReview=reviewRatingRepository.findByUserAndProduct(reviewDoneBy,reviewDoneTo);
        if (optionalReview.isPresent()) {
            throw new RestException("Review already done!!");
        }
        ReviewRating review=new ReviewRating();
        review.setAddedDate(LocalDateTime.now());
        review.setContent(reviewRequest.getReview());
        if (reviewRequest.getRating()<1 && reviewRequest.getRating()>5){
            throw new RestException("Invalid Rating value");
        }
        review.setRating(reviewRequest.getRating());
        review.setReviewDoneBy(reviewDoneBy);
        review.setReviewDoneOn(reviewDoneTo);
        return review;
    }

    @Override
    public RatingResponse countAverageRating(Long id){
        float data=0;
        float ratingCount=0;
        Product product=productService.validateProduct(id);
        try {
            data=reviewRatingRepository.findNoOfRatings(product);
            ratingCount =reviewRatingRepository.countTotalReviews(product);
        }catch (Exception e){
            log.info("no ratings");
        }
        return RatingResponse.builder()
                .productId(id)
                .rating(data)
                .ratingCount(ratingCount)
                .build();
    }

    @Override
    public int countTotalReview(Long productId) {
        Product product=productService.validateProduct(productId);
        return reviewRatingRepository.countTotalReviews(product);
    }
}
