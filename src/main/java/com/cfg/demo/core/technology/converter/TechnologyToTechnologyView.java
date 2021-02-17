package com.cfg.demo.core.technology.converter;

import com.cfg.demo.core.technology.Technology;
import com.cfg.demo.core.technology.web.TechnologyView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TechnologyToTechnologyView implements Converter<Technology, TechnologyView> {
    @Override
    public TechnologyView convert(@NonNull Technology technology){
        TechnologyView technologyView = new TechnologyView();
        technologyView.setName(technology.getName());
        technologyView.setId(technology.getId());
        technologyView.setDescription(technology.getDescription());
        return technologyView;

    }
}
