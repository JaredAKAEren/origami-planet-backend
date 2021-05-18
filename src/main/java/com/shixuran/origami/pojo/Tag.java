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
@Entity(name = "tag")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id 不能为 null")
    private int id;

    private String tagName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tagDate;

    private int tagFolderId;

    private int tagDiagramId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getTagDate() {
        return tagDate;
    }

    public void setTagDate(Date tagDate) {
        this.tagDate = tagDate;
    }

    public int getTagFolderId() {
        return tagFolderId;
    }

    public void setTagFolderId(int tagFolderId) {
        this.tagFolderId = tagFolderId;
    }

    public Tag(@NotNull(message = "id 不能为 null") int id, String tagName, Date tagDate, int tagFolderId, int tagDiagramId) {
        this.id = id;
        this.tagName = tagName;
        this.tagDate = tagDate;
        this.tagFolderId = tagFolderId;
        this.tagDiagramId = tagDiagramId;
    }

    public Tag() {
    }
}
