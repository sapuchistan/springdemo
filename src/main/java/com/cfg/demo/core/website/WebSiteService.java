package com.cfg.demo.core.website;

import com.cfg.demo.DemoApplication;
import com.cfg.demo.base.BaseRequest;
import com.cfg.demo.core.country.CountryRepo;
import com.cfg.demo.core.rating.Rating;
import com.cfg.demo.core.rating.RatingRepo;
import com.cfg.demo.core.rating.RatingService;
import com.cfg.demo.core.technology.Technology;
import com.cfg.demo.core.technology.TechnologyRepo;
import com.cfg.demo.core.website.converter.WebSiteToWebSiteViewConverter;
import com.cfg.demo.core.website.web.WebSiteBaseReq;
import com.cfg.demo.core.website.web.WebSiteView;
import com.cfg.demo.error.EntityNotFoundException;
import com.cfg.demo.util.MessageUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;



import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WebSiteService {
    static Logger log = Logger.getLogger(DemoApplication.class.getName());
    private final WebSiteRepo webSiteRepo;
    private final WebSiteToWebSiteViewConverter webSiteToWebSiteViewConverter;
    private final CountryRepo countryRepo;
    private final MessageUtil messageUtil;
    private final TechnologyRepo technologyRepo;


    public WebSiteService(WebSiteRepo webSiteRepo, WebSiteToWebSiteViewConverter webSiteToWebSiteViewConverter, CountryRepo countryRepo, MessageUtil messageUtil, TechnologyRepo technologyRepo) {
        this.webSiteRepo = webSiteRepo;
        this.webSiteToWebSiteViewConverter = webSiteToWebSiteViewConverter;
        this.countryRepo = countryRepo;
        this.messageUtil = messageUtil;
        this.technologyRepo = technologyRepo;
    }

    public WebSite findWebSiteOrThrow(Long id) {
        return webSiteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("website.NotFound", id)));
    }

    public WebSiteView getWebSite(Long id) {
        WebSite webSite = findWebSiteOrThrow(id);
        return webSiteToWebSiteViewConverter.convert(webSite);
    }

    public WebSiteView create(WebSiteBaseReq req) {
        WebSite webSite = new WebSite();
        this.prepare(webSite,req);
        WebSite webSiteSave = webSiteRepo.save(webSite);
        return webSiteToWebSiteViewConverter.convert(webSiteSave);
    }

    public Page<WebSiteView> findAllWebSites(Pageable pageable) {
        Page<WebSite> webSites = webSiteRepo.findAll(pageable);
        List<WebSiteView> webSiteViews = new ArrayList<>();
        webSites.forEach(webSite -> {
            WebSiteView webSiteView = webSiteToWebSiteViewConverter.convert(webSite);
            webSiteViews.add(webSiteView);
        });
        log.debug("Found "+ webSites.getTotalElements() +" websites");

        return new PageImpl<>(webSiteViews, pageable, webSites.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            webSiteRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("rating.NotFound", id));
        }
    }

    public WebSiteView  update(WebSite webSite, WebSiteBaseReq req) {
        WebSite newWebSite = this.prepare(webSite, req);
        WebSite webSiteForSave = webSiteRepo.save(newWebSite);
        return webSiteToWebSiteViewConverter.convert(webSiteForSave);
    }

    private WebSite prepare(WebSite webSite, WebSiteBaseReq req) {
        webSite.setName(req.getName());
        webSite.setUrl(req.getUrl());
        webSite.setRating(req.getRating());
        webSite.setCountry(countryRepo.getOne(req.getCountryId()));
        List<Technology> technologiesList = technologyRepo.findAllById(req.getTechnology()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Technology> technologies = new HashSet<>(technologiesList);
        webSite.setTechnologies(technologies);
        return webSite;
    }
public void calculateSumRating(Rating rating){
        WebSite webSite=rating.getWebSite();

        if (webSite!=null) {
            log.debug("Entro a suma con: "+webSite.getId());
            webSite.setRatingCount(webSite.getRatingCount() + 1);
            webSite.setRatingSum(webSite.getRatingSum()+rating.getRating());
            webSite.setRating((int) Math.round(webSite.getRatingSum() / webSite.getRatingCount()));
            webSiteRepo.save(webSite);
            log.debug("actualizo suma en suma con: "+webSite.getRatingSum());
            log.debug("actualizo count en suma con: "+webSite.getRatingCount());
            log.debug("actualizo rating en suma con: "+webSite.getRating());
        }
}
    public void calculateDeleteRating(Rating rating){
        WebSite webSite=rating.getWebSite();
        if (webSite!=null) {
            log.debug("Entro a delete con: "+webSite.getId());
            log.debug("Entro a delete con count: "+webSite.getRatingCount());
            log.debug("Entro a delete con sum: "+webSite.getRatingSum());
            if(webSite.getRatingCount()>0 && webSite.getRatingSum()>0) {
                webSite.setRatingCount(webSite.getRatingCount() - 1);
                if(webSite.getRatingCount()==0){
                    webSite.setRatingSum(0L);
                }else if (webSite.getRatingSum()>0) {
                    webSite.setRatingSum(webSite.getRatingSum() - rating.getRating());
                    webSite.setRating((int) Math.round(webSite.getRatingSum() / webSite.getRatingCount()));
                }
                webSiteRepo.save(webSite);
                log.debug("actualizo suma en delete con: " + webSite.getRatingSum());
                log.debug("actualizo count en delete con: " + webSite.getRatingCount());
                log.debug("actualizo rating en delete con: "+webSite.getRating());
            }

        }
    }

}
