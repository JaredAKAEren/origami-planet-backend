package com.shixuran.origami.utils;

import com.shixuran.origami.pojo.Folder;

import java.util.List;

public class FolderUtils {
    Folder folder;

    List<String> tags;

    List<String> images;

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
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
