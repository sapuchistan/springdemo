package com.cfg.demo.core.country.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CountryBaseReq {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
