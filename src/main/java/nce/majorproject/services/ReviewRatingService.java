package nce.majorproject.services;

import nce.majorproject.dto.AddReviewRatingRequest;
import nce.majorproject.dto.IdResponse;
import nce.majorproject.dto.Reviews;

import java.util.List;

public interface ReviewRatingService {


    IdResponse addReview(AddReviewRatingRequest reviewRequest);
    List<Reviews> listProductReviews(Long productId);
    float countAverageRating(Long productId);
    int countTotalReview(Long productId);
}
