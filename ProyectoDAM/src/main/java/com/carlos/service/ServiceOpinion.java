package com.carlos.service;

import java.util.List;

import com.carlos.model.Opinion;
import com.carlos.repository.OpinionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOpinion implements IServiceOpinion {

    @Autowired
    private OpinionDAO opinionDAO;

    @Override
    public void add(Opinion opinion) {
        opinionDAO.save(opinion);
    }

    @Override
    public void update(Opinion opinion) {
        if(opinionDAO.existsById(opinion.getId()))
            opinionDAO.save(opinion);
    }

    @Override
    public void delete(Opinion opinion) {
        opinionDAO.delete(opinion);
    }

    @Override
    public List<Opinion> listarOpinionesPorLibro(String isbn) {
        return opinionDAO.listarLibrosPorGenero(isbn);
    }
    
}
