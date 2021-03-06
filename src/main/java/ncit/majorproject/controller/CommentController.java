package ncit.majorproject.controller;


import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.constant.Route;
import ncit.majorproject.dto.CommentListResponse;
import ncit.majorproject.dto.CommentRequest;
import ncit.majorproject.dto.CommentResponse;
import ncit.majorproject.dto.CountResponse;
import ncit.majorproject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Route.COMMENT)
@Slf4j
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/addComment")
    public List<CommentListResponse> addComment(@Valid @RequestBody CommentRequest commentRequest) throws Exception {
        log.info("adding comment",commentRequest.getProductId(),commentRequest.getPostComment());
        return commentService.addComment(commentRequest);
    }

    @GetMapping(value = "/admin/disableComment/{id}")
    CommentResponse disableComment(@PathVariable("id") Long id){
        log.info("disabling comment::{}",id);
        return commentService.disableComment(id);
    }

    @GetMapping(value = "/countComment/{id}")
    public CountResponse countComment(@PathVariable ("id") Long postId){
        log.info("counting comments::",postId);
        return commentService.countComment(postId);
    }

    @GetMapping("/getComment/{id}")
    public List<CommentListResponse> getAllComment(@PathVariable ("id") Long postId){
        log.info("showing all comment::",postId);
        return commentService.getAllCommentFromPostId(postId);
    }

    @GetMapping(value = "admin/activateComment/{id}")
    public CommentResponse activateComment(@PathVariable("id") Long id){
        log.info("activating comment::{}",id);
        return commentService.activateComment(id);
    }

    @GetMapping(value = "/deleteComment/{id}")
    public CommentResponse deleteComment(@PathVariable("id") Long id){
        log.info("deleting comment::{}",id);
        return commentService.deleteComment(id);
    }
}
