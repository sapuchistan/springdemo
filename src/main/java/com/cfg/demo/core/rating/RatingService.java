package com.cfg.demo.core.rating;

import com.cfg.demo.core.rating.converter.RatingToRatingView;
import com.cfg.demo.core.rating.web.RatingBaseReq;
import com.cfg.demo.core.rating.web.RatingView;
import com.cfg.demo.error.EntityNotFoundException;
import com.cfg.demo.util.MessageUtil;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingRepo ratingRepo;
    private final RatingToRatingView ratingToRatingView;
    private final MessageUtil messageUtil;

    public RatingService(RatingRepo ratingRepo, RatingToRatingView ratingToRatingView, MessageUtil messageUtil) {
        this.ratingRepo = ratingRepo;
        this.ratingToRatingView = ratingToRatingView;
        this.messageUtil = messageUtil;
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
        return ratingToRatingView.convert(ratingSave);
    }

    public Rating prepare(Rating rating, RatingBaseReq req){
        rating.setComment(req.getComment());
        rating.setRating(req.getRating());
        return rating;
    }
}
