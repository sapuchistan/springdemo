package com.cfg.demo.core.website.converter;

import com.cfg.demo.core.technology.Technology;
import com.cfg.demo.core.technology.converter.TechnologyToTechnologyView;
import com.cfg.demo.core.technology.web.TechnologyView;
import com.cfg.demo.core.website.WebSite;
import com.cfg.demo.core.website.web.WebSiteView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class WebSiteToWebSiteViewConverter  implements Converter<WebSite, WebSiteView> {

    private TechnologyToTechnologyView technologyToTechnologyView;

    public WebSiteToWebSiteViewConverter(TechnologyToTechnologyView technologyToTechnologyView) {
        this.technologyToTechnologyView = technologyToTechnologyView;
    }

    @Override
    public WebSiteView convert(@NonNull WebSite website) {
        WebSiteView view = new WebSiteView();
        view.setId(website.getId());
        view.setName(website.getName());
        view.setUrl(website.getUrl());
        view.setCountry(website.getCountry());
        view.setRating(website.getRating());
        Set<TechnologyView> technologyViews = new HashSet<>();
        Set<Technology> technologies = website.getTechnologies();
        technologies.forEach(technology -> {
            TechnologyView technologyView = technologyToTechnologyView.convert(technology);
            technologyViews.add(technologyView);
        });
        view.setTechnologies(technologyViews);
        return view;
    }
}
