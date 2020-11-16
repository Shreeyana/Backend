package nce.majorproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.AddReviewRatingRequest;
import nce.majorproject.dto.IdResponse;
import nce.majorproject.dto.Reviews;
import nce.majorproject.dto.product.RatingResponse;
import nce.majorproject.entities.Product.Product;
import nce.majorproject.entities.ReviewRating;
import nce.majorproject.entities.User;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.ReviewRatingRepository;
import nce.majorproject.services.ProductService;
import nce.majorproject.services.ReviewRatingService;
import nce.majorproject.services.UserService;
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

    @Autowired
    public ReviewRatingServiceImpl(ReviewRatingRepository reviewRatingRepository, ContextHolderServices contextHolderServices, UserService userService, ProductService productService) {
        this.reviewRatingRepository = reviewRatingRepository;
        this.contextHolderServices = contextHolderServices;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public IdResponse addReview(AddReviewRatingRequest reviewRequest) {
        ReviewRating review=prepareToAddReview(reviewRequest);
        ReviewRating response=reviewRatingRepository.save(review);
        return IdResponse.builder().id(response.getId()).build();
    }

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
        Product product=productService.validateProduct(id);
        try {
            data=reviewRatingRepository.findNoOfRatings(product);
        }catch (Exception e){
            log.info("no ratings");
        }
        return RatingResponse.builder()
                .productId(id)
                .rating(data)
                .build();
    }

    @Override
    public int countTotalReview(Long productId) {
        Product product=productService.validateProduct(productId);
        return reviewRatingRepository.countTotalReviews(product);
    }
}
