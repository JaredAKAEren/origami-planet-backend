package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByArticleId(int articleId);

    List<Comment> findAllByDiagramImageId(int diagramImageId);

    List<Comment> findAllByFolderId(int folderId);
}
