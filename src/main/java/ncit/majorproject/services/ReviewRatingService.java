package ncit.majorproject.services;

import ncit.majorproject.dto.AddReviewRatingRequest;
import ncit.majorproject.dto.IdResponse;
import ncit.majorproject.dto.Reviews;
import ncit.majorproject.dto.product.RatingResponse;

import java.util.List;

public interface ReviewRatingService {


    IdResponse addReview(AddReviewRatingRequest reviewRequest);
    List<Reviews> listProductReviews(Long productId);
    RatingResponse countAverageRating(Long productId);
    int countTotalReview(Long productId);
}
