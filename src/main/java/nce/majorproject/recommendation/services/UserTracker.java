package nce.majorproject.recommendation.services;

import nce.majorproject.dto.product.LatestAddedProductResponse;
import nce.majorproject.recommendation.dto.NextItemInferred;
import nce.majorproject.recommendation.dto.UserSelectionRequest;

import java.util.List;

public interface UserTracker {
    NextItemInferred recordUserSelection(UserSelectionRequest request);

    List<LatestAddedProductResponse> hitter();
}
