package com.cfg.demo.core.website.web;

import com.cfg.demo.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class WebSiteBaseReq extends BaseRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String url;

    private int rating;

    @NotNull
    private Long countryId;

    @NotEmpty
    private List<@Valid Id> technology;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public List<Id> getTechnology() {
        return technology;
    }

    public void setTechnology(List<Id> technology) {
        this.technology = technology;
    }
}
