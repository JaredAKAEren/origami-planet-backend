package com.shixuran.origami.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
@Entity(name = "comment")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id 不能为 null")
    private int id;

    private String commentText;

    private int userId;

    private int articleId;

    private int diagramImageId;

    private int folderId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentDate;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public int getDiagramImageId() {
        return diagramImageId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
