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
@Entity(name = "folder")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id 不能为 null")
    private int id;

    private String folderTitle;

    private String folderContent;

    private String folderCover;

    private int folderState;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date folderDate;

    private int folderUserId;

    public int getId() {
        return id;
    }

    public int getFolderUserId() {
        return folderUserId;
    }

    public void setFolderDate(Date folderDate) {
        this.folderDate = folderDate;
    }

}
