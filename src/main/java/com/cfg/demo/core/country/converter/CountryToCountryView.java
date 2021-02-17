package com.cfg.demo.core.country.converter;

import com.cfg.demo.core.country.Country;
import com.cfg.demo.core.country.web.CountryView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryView implements Converter<Country, CountryView> {
    @Override
    public CountryView convert(@NonNull Country country) {
        CountryView view = new CountryView();
        view.setId(country.getId());
        view.setName(country.getName());
        return view;
    }
}
