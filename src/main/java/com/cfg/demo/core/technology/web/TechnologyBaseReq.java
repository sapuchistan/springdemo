package com.cfg.demo.core.technology.web;

import javax.validation.constraints.NotEmpty;

public class TechnologyBaseReq {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
