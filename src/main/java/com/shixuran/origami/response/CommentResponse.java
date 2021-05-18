package com.shixuran.origami.response;

import com.shixuran.origami.pojo.Comment;
import com.shixuran.origami.pojo.Profile;

public class CommentResponse {
    Comment comment;
    Profile profile;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
