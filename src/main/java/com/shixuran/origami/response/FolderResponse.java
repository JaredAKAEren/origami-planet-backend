package com.shixuran.origami.response;

import com.shixuran.origami.pojo.*;

import java.util.List;

public class FolderResponse {
    String username;
    Profile profile;
    Folder folder;
    List<Tag> tag;
    List<Image> image;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Folder getFolder() {
        return folder;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public List<Image> getImage() {
        return image;
    }
}
