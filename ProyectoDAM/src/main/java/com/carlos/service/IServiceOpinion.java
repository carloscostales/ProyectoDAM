package com.carlos.service;

import java.util.List;

import com.carlos.model.Opinion;

public interface IServiceOpinion {

    public void add(Opinion opinion);
	
	public void update(Opinion opinion);
	
	public void delete(Opinion opinion);
    
	public List<Opinion> listarOpinionesPorLibro(String isbn);
}
