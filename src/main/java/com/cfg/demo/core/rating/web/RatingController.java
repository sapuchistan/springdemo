package com.cfg.demo.core.rating.web;

import com.cfg.demo.core.rating.Rating;
import com.cfg.demo.core.rating.RatingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RatingView getRating(@PathVariable Long id) {
        return ratingService.getRating(id);
    }

    @GetMapping
    @ResponseBody
    public Page<RatingView> getAllRatings(@PageableDefault (sort="id", direction= Sort.Direction.ASC)Pageable pageable){
        return ratingService.findAllRatings(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public  RatingView create (@RequestBody @Valid RatingBaseReq req){
        return ratingService.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable Long id){
        ratingService.delete(id);
    }

    @PutMapping("/{id}")
    public RatingView updateRating (@PathVariable(name = "id") Long id, @RequestBody @Valid RatingBaseReq req){
    Rating rating = ratingService.findRatingOrThrow(id);
    return ratingService.update(rating,req);
    }
}
