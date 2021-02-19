package com.cfg.demo.core.rating;

import com.cfg.demo.core.rating.converter.RatingToRatingView;
import com.cfg.demo.core.rating.web.RatingBaseReq;
import com.cfg.demo.core.rating.web.RatingView;
import com.cfg.demo.core.website.WebSiteRepo;
import com.cfg.demo.core.website.WebSiteService;
import com.cfg.demo.core.website.web.WebSiteView;
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
public class RatingService {
    private final RatingRepo ratingRepo;
    private final RatingToRatingView ratingToRatingView;
    private final MessageUtil messageUtil;
    private final WebSiteRepo webSiteRepo;
    private final WebSiteService webSiteService;

    public RatingService(RatingRepo ratingRepo, RatingToRatingView ratingToRatingView, MessageUtil messageUtil, WebSiteRepo webSiteRepo, WebSiteService webSiteService) {
        this.ratingRepo = ratingRepo;
        this.ratingToRatingView = ratingToRatingView;
        this.messageUtil = messageUtil;
        this.webSiteRepo = webSiteRepo;
        this.webSiteService = webSiteService;
    }

    public Rating findRatingOrThrow (Long id){
        return this.ratingRepo.findById(id).orElseThrow(()-> new EntityNotFoundException(messageUtil.getMessage("rating.NotFound",id)));
    }
    public RatingView getRating(Long id){
        Rating rating = this.findRatingOrThrow(id);
        return ratingToRatingView.convert(rating);
    }

    public RatingView create(RatingBaseReq req){
        Rating rating=new Rating();
        this.prepare(rating,req);
        Rating ratingSave=ratingRepo.save(rating);
        updateSumWebSiteRating(ratingSave);
        return ratingToRatingView.convert(ratingSave);
    }
public Page<RatingView> findAllRatings(Pageable pageable){
        Page <Rating> ratings =ratingRepo.findAll(pageable);
        List<RatingView> ratingViewList= new ArrayList<>();
        ratings.forEach(rating -> {
            RatingView ratingView=ratingToRatingView.convert(rating);
            ratingViewList.add(ratingView);
        });
        return new PageImpl<>(ratingViewList,pageable,ratings.getTotalElements());
}
    public Rating prepare(Rating rating, RatingBaseReq req){
        rating.setComment(req.getComment());
        rating.setRating(req.getRating());
        rating.setWebSite(webSiteRepo.getOne(req.getWebsiteId()));
        return rating;
    }

    @Transactional
    public void delete(Long id){
        try{
            updateDeleteWebSiteRating(findRatingOrThrow(id));
            ratingRepo.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("rating.NotFound",id));
        }
    }
    public RatingView update (Rating rating, RatingBaseReq req){
        updateDeleteWebSiteRating(rating);
        Rating newRating = this.prepare(rating, req);
        Rating ratingSave = ratingRepo.save(newRating);
        updateSumWebSiteRating(newRating);
        return ratingToRatingView.convert(ratingSave);
    }

    private void updateSumWebSiteRating(Rating rating){
            webSiteService.calculateSumRating(rating);
        }
    private void updateDeleteWebSiteRating(Rating rating){
        webSiteService.calculateDeleteRating(rating);

    }


}
