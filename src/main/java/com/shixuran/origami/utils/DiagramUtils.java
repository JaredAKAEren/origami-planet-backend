package com.shixuran.origami.utils;

import com.shixuran.origami.pojo.Diagram;

import java.util.List;

public class DiagramUtils {
    Diagram diagram;

    List<String> tags;

    List<String> images;

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
