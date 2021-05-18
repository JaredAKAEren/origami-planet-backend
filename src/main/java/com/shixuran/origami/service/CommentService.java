package com.shixuran.origami.service;

import com.shixuran.origami.dao.CommentDAO;
import com.shixuran.origami.dao.ProfileDAO;
import com.shixuran.origami.pojo.Comment;
import com.shixuran.origami.pojo.Profile;
import com.shixuran.origami.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;

    @Autowired
    ProfileDAO profileDAO;

    public int addOrUpdate(Comment comment) {
        comment.setCommentDate(new Date());
        Comment commentAfterSave = commentDAO.save(comment);
        return commentAfterSave.getId();
    }

    public List<CommentResponse> getCommentList(int articleId) {
        List<Comment> commentList = commentDAO.findAllByArticleId(articleId);
        return getCommentResponses(commentList);
    }

    public List<CommentResponse> getDiagramCommentList(int diagramId) {
        List<Comment> commentList = commentDAO.findAllByDiagramImageId(diagramId);
        return getCommentResponses(commentList);
    }

    public List<CommentResponse> getFolderCommentList(int folderId) {
        List<Comment> commentList = commentDAO.findAllByFolderId(folderId);
        return getCommentResponses(commentList);
    }

    private List<CommentResponse> getCommentResponses(List<Comment> commentList) {
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setComment(comment);
            Profile profile = profileDAO.findByProfileUserId(comment.getUserId());
            commentResponse.setProfile(profile);
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }

    public boolean delete(int id) {
        try {
            commentDAO.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
