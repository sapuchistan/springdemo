package com.cfg.demo.core.rating.converter;

import com.cfg.demo.core.rating.Rating;
import com.cfg.demo.core.rating.web.RatingView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RatingToRatingView implements Converter <Rating, RatingView> {

    @Override
    public RatingView convert(Rating rating) {
        RatingView ratingView=new RatingView();
        ratingView.setId(rating.getId());
        ratingView.setRating(rating.getRating());
        ratingView.setComment(rating.getComment());
        return ratingView;
    }
}
