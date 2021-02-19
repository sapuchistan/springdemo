package com.cfg.demo;

import com.cfg.demo.core.country.web.CountryController;
import com.cfg.demo.core.rating.web.RatingController;
import com.cfg.demo.core.technology.web.TechnologyController;
import com.cfg.demo.core.website.web.WebSiteController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private WebSiteController webSiteController;
    @Autowired
    private CountryController countryController;
    @Autowired
    private TechnologyController technologyController;
    @Autowired
    private RatingController ratingController;


    @Test
    public void contextLoads() throws Exception{
        assertThat (webSiteController).isNotNull();
        assertThat(countryController).isNotNull();
        assertThat(technologyController).isNotNull();
        assertThat(ratingController).isNotNull();
    }
}
