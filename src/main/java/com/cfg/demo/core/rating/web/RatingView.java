package com.cfg.demo.core.rating.web;

import com.cfg.demo.core.website.WebSite;

public class RatingView {
    private long id;
    private long rating;
    private String comment;
    private Long webSite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getWebSite() {
        return webSite;
    }

    public void setWebSite(Long webSite) {
        this.webSite = webSite;
    }
}
