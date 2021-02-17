package com.cfg.demo.core.website.web;

import com.cfg.demo.core.country.Country;
import com.cfg.demo.core.technology.web.TechnologyView;

import java.util.HashSet;
import java.util.Set;

public class WebSiteView {
    private long id;

    private String name;

    private String url;

    private int rating;

    private Country country;

    private Set<TechnologyView> technologies = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<TechnologyView> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<TechnologyView> technologies) {
        this.technologies = technologies;
    }
}
