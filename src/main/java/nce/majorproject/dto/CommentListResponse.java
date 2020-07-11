package nce.majorproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentListResponse {

    private Long id;
    private String status;
    private LocalDateTime addedDate;
    private Long userId;
    private String userName;
}
