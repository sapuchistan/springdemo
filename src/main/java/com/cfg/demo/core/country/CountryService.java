package com.cfg.demo.core.country;

import com.cfg.demo.core.country.converter.CountryToCountryView;
import com.cfg.demo.core.country.web.CountryBaseReq;
import com.cfg.demo.core.country.web.CountryView;

import com.cfg.demo.error.EntityNotFoundException;
import com.cfg.demo.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class CountryService {
    private final CountryRepo countryRepo;
    private final CountryToCountryView countryToCountryView;
    private final MessageUtil messageUtil;

    public CountryService(CountryRepo countryRepo,
                          CountryToCountryView countryToCountryView,
                       MessageUtil messageUtil) {
        this.countryRepo = countryRepo;
        this.countryToCountryView = countryToCountryView;
        this.messageUtil = messageUtil;
    }

    public CountryView getCountry(Long id) {
        Country country = findCountryOrThrow(id);
        return countryToCountryView.convert(country);
    }

    public Country findCountryOrThrow(Long id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("country.NotFound", id)));
    }

    public Page<CountryView> findAllCountries(Pageable pageable){
        Page<Country> countries = countryRepo.findAll(pageable);
        List<CountryView> countryViews = new ArrayList<>();
        countries.forEach(country -> {
            CountryView countryView = countryToCountryView.convert(country);
            countryViews.add(countryView);
        });
        return new PageImpl<>(countryViews, pageable, countries.getTotalElements());
    }

    public CountryView create(CountryBaseReq req) {
        Country country = new Country();
        this.prepare(country,req);
        Country countrySave = countryRepo.save(country);
        return countryToCountryView.convert(countrySave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            countryRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("country.NotFound", id));
        }
    }

    public CountryView update(Country country, CountryBaseReq req){
        Country newCountry = this.prepare(country,req);
        Country countrySave = countryRepo.save(newCountry);
        return countryToCountryView.convert(countrySave);
    }

    public Country prepare(Country country, CountryBaseReq countryBaseReq){
        country.setName(countryBaseReq.getName());
        return country;
    }
}
