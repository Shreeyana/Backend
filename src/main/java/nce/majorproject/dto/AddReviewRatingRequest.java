package nce.majorproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddReviewRatingRequest {


    @Size(min = 1,max = 224,message = "You have exceed maximum Review length!!")
    private String review;

    @Size(max = 5,message = "invalid length!!")
    private int rating;

    @NotNull(message = "Review Done to cannot be null!!")
    private Long reviewDoneTo;
}
