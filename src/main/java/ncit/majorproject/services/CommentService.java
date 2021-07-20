package ncit.majorproject.services;

import ncit.majorproject.dto.CommentListResponse;
import ncit.majorproject.dto.CommentRequest;
import ncit.majorproject.dto.CommentResponse;
import ncit.majorproject.dto.CountResponse;
import ncit.majorproject.entities.Comment;

import java.util.List;

public interface CommentService {

    List<CommentListResponse> addComment(CommentRequest commentRequest) throws Exception;

    CommentResponse disableComment(Long id);
    Comment validateCommentById(Long id);
    CountResponse countComment(Long id);
    List<CommentListResponse> getAllCommentFromPostId(Long id);
    CommentResponse activateComment(Long id);
    CommentResponse deleteComment(Long id);
}
