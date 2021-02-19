package com.cfg.demo.core.technology.web;


import com.cfg.demo.core.technology.Technology;
import com.cfg.demo.core.technology.TechnologyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/technology")
public class TechnologyController {
    private final TechnologyService technologyService;


    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TechnologyView getTechnology(@PathVariable Long id) {
        return technologyService.getTechnology(id);
    }
    @GetMapping
    @ResponseBody
    public Page<TechnologyView> getAllTechnologies(@PageableDefault (sort="id",direction = Sort.Direction.ASC)Pageable pageable){
        return technologyService.findAllTechnologies(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TechnologyView create (@RequestBody @Valid TechnologyBaseReq req){
        return technologyService.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTechnology(@PathVariable Long id){
        technologyService.delete(id);
    }

    @PutMapping("/{id}")
    public TechnologyView updateTechnology(@PathVariable(name = "id") Long id,
                                  @RequestBody @Valid TechnologyBaseReq req){
        Technology technology = technologyService.findTechnologyOrThrow(id);
        return technologyService.update(technology, req);
    }
}
