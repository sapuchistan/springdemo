package com.cfg.demo.core.technology;

import com.cfg.demo.core.technology.converter.TechnologyToTechnologyView;
import com.cfg.demo.core.technology.web.TechnologyBaseReq;
import com.cfg.demo.core.technology.web.TechnologyView;
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
public class TechnologyService {
    private final TechnologyRepo technologyRepo;
    private final TechnologyToTechnologyView technologyToTechnologyView;
    private final MessageUtil messageUtil;

    public TechnologyService(TechnologyRepo technologyRepo, TechnologyToTechnologyView technologyToTechnologyView, MessageUtil messageUtil){
        this.technologyRepo=technologyRepo;
        this.technologyToTechnologyView=technologyToTechnologyView;
        this.messageUtil=messageUtil;
    }

    public Technology findTechnologyOrThrow(Long id){
        return this.technologyRepo.findById(id).orElseThrow(()-> new EntityNotFoundException(messageUtil.getMessage("technology.NotFound",id)));
    }
    public TechnologyView getTechnology(Long id){
        Technology technology=this.findTechnologyOrThrow(id);
       return technologyToTechnologyView.convert(technology);
    }
    public TechnologyView create(TechnologyBaseReq req){
        Technology technology=new Technology();
        this.prepare(technology,req);
        Technology technologySave = technologyRepo.save(technology);
        return technologyToTechnologyView.convert(technologySave);
    }
    public Technology prepare (Technology technology, TechnologyBaseReq req){
        technology.setName(req.getName());
        technology.setDescription(req.getDescription());
        return technology;
    }
    public TechnologyView update(Technology technology,TechnologyBaseReq req) {
        Technology newTechnology = this.prepare(technology,req);
        Technology technologySave = technologyRepo.save(newTechnology);
        return technologyToTechnologyView.convert(technologySave);
    }
    public Page<TechnologyView> findAllTechnologies(Pageable pageable){
        Page <Technology> technologies = technologyRepo.findAll(pageable);
        List<TechnologyView> technologyViews = new ArrayList<>();
        technologies.forEach(technology -> {
            TechnologyView technologyView = technologyToTechnologyView.convert(technology);
            technologyViews.add(technologyView);
        });
        return new PageImpl<>(technologyViews,pageable,technologies.getTotalElements());
    }
    @Transactional
    public void delete(Long id) {
        try {
            technologyRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("technology.NotFound", id));
        }
    }
}
