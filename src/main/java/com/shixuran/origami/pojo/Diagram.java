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
@Entity(name = "diagram")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Diagram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id 不能为 null")
    private int id;

    private String diagramTitle;

    private String diagramContent;

    private String diagramCover;

    private int diagramState;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date diagramDate;

    private int diagramUserId;

    public int getId() {
        return id;
    }

    public int getDiagramUserId() {
        return diagramUserId;
    }

    public void setDiagramDate(Date diagramDate) {
        this.diagramDate = diagramDate;
    }
}
