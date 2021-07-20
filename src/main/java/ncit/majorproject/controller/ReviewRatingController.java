package ncit.majorproject.controller;

import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.constant.Route;
import ncit.majorproject.dto.AddReviewRatingRequest;
import ncit.majorproject.dto.IdResponse;
import ncit.majorproject.dto.Reviews;
import ncit.majorproject.dto.product.RatingResponse;
import ncit.majorproject.services.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Route.REVIEW_RATING)
@Slf4j
public class ReviewRatingController {

    private ReviewRatingService reviewRatingService;

    @Autowired
    public ReviewRatingController(ReviewRatingService reviewRatingService) {
        this.reviewRatingService = reviewRatingService;
    }

    @PostMapping
    public IdResponse addReview(@Valid @RequestBody AddReviewRatingRequest reviewRequest){
        log.info("Adding review to::{}",reviewRequest.getReviewDoneTo());
        return reviewRatingService.addReview(reviewRequest);
    }
    @GetMapping("/rating/{productId}")
    public RatingResponse getProductRating(@PathVariable("productId")Long productId){
        return  reviewRatingService.countAverageRating(productId);
    }
    @GetMapping(value = "/list/{productId}")
    public List<Reviews> listReviews(@PathVariable ("productId") Long productId){
        log.info("listing my reviews::{}",productId);
        return reviewRatingService.listProductReviews(productId);
    }
}
