package com.cfg.demo.core.rating.web;

import com.cfg.demo.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class RatingBaseReq extends BaseRequest  {

    @NotEmpty
    private String comment;

    @NotEmpty
    private Long rating;

    @NotEmpty
    private Long websiteId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Long websiteId) {
        this.websiteId = websiteId;
    }
}
