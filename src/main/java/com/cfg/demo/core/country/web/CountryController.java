package com.cfg.demo.core.country.web;

import com.cfg.demo.core.country.Country;
import com.cfg.demo.core.country.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CountryView getCountry(@PathVariable Long id) {
        return service.getCountry(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CountryView> getAllCountries(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllCountries(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CountryView create(@RequestBody @Valid CountryBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CountryView updateTeam(@PathVariable(name = "id") Long id,
                               @RequestBody @Valid CountryBaseReq req){
        Country country = service.findCountryOrThrow(id);
        return service.update(country, req);
    }
}
