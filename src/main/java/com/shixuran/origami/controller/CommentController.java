package com.shixuran.origami.controller;

import com.shixuran.origami.pojo.Comment;
import com.shixuran.origami.response.CommentResponse;
import com.shixuran.origami.result.Result;
import com.shixuran.origami.result.ResultFactory;
import com.shixuran.origami.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("api/comment")
    public Result saveComment(@RequestBody Comment comment) {
        int afterSaveId = commentService.addOrUpdate(comment);
        return ResultFactory.buildSuccessResult(afterSaveId);
    }

    @GetMapping("api/comment/{articleId}")
    public Result getComments(@PathVariable("articleId") int articleId) {
        List<CommentResponse> commentResponseList = commentService.getCommentList(articleId);
        return ResultFactory.buildSuccessResult(commentResponseList);
    }

    @GetMapping("api/comment/image/{diagramId}")
    public Result getDiagramComments(@PathVariable("diagramId") int diagramId) {
        List<CommentResponse> commentResponseList = commentService.getDiagramCommentList(diagramId);
        return ResultFactory.buildSuccessResult(commentResponseList);
    }

    @GetMapping("api/comment/folder/{folderId}")
    public Result getFolderComments(@PathVariable("folderId") int folderId) {
        List<CommentResponse> commentResponseList = commentService.getFolderCommentList(folderId);
        return ResultFactory.buildSuccessResult(commentResponseList);
    }

    @PostMapping("api/comment/delete/{id}")
    public Result deleteComment(@PathVariable("id") int id) {
        boolean isDelete = commentService.delete(id);
        if (isDelete) {
            return ResultFactory.buildSuccessResult("删除成功");
        } else {
            return ResultFactory.buildFailResult("删除失败");
        }
    }
}
