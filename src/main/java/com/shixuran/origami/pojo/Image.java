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
@Entity(name = "image")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id 不能为 null")
    private int id;

    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date imageDate;

    private int imageFolderId;

    private int imageDiagramId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getImageDate() {
        return imageDate;
    }

    public void setImageDate(Date imageDate) {
        this.imageDate = imageDate;
    }

    public int getImageFolderId() {
        return imageFolderId;
    }

    public void setImageFolderId(int imageFolderId) {
        this.imageFolderId = imageFolderId;
    }

    public int getImageDiagramId() {
        return imageDiagramId;
    }

    public void setImageDiagramId(int imageDiagramId) {
        this.imageDiagramId = imageDiagramId;
    }

    public Image(@NotNull(message = "id 不能为 null") int id, String imageUrl, Date imageDate, int imageFolderId, int imageDiagramId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageDate = imageDate;
        this.imageFolderId = imageFolderId;
        this.imageDiagramId = imageDiagramId;
    }

    public Image() {
    }
}
