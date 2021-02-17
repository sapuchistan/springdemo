package com.cfg.demo.core.rating.web;

import com.cfg.demo.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class RatingBaseReq extends BaseRequest {

    @NotEmpty
    private String comment;

    @NotEmpty
    private int rating;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
