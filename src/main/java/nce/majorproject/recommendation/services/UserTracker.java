package nce.majorproject.recommendation.services;

import nce.majorproject.recommendation.dto.NextItemInferred;
import nce.majorproject.recommendation.dto.UserSelectionRequest;

public interface UserTracker {
    NextItemInferred recordUserSelection(UserSelectionRequest request);
}
