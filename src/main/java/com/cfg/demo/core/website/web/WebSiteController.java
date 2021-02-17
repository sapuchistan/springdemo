package com.cfg.demo.core.website.web;

import com.cfg.demo.core.website.WebSite;
import com.cfg.demo.core.website.WebSiteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/website")
public class WebSiteController {
    private final WebSiteService service;

    public WebSiteController(WebSiteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public WebSiteView getWebSite(@PathVariable Long id) {
        return service.getWebSite(id);
    }

    @GetMapping
    @ResponseBody
    public Page<WebSiteView> getAllWebSites(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllWebSites(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public WebSiteView create(@RequestBody @Valid WebSiteBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public WebSiteView updateWebSite(@PathVariable(name = "id") Long id,
                               @RequestBody @Valid WebSiteBaseReq req){
        WebSite webSite = service.findWebSiteOrThrow(id);
        return service.update(webSite, req);
    }
}
