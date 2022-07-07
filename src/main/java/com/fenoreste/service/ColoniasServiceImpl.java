package com.fenoreste.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.ColoniasRepository;
import com.fenoreste.entity.Colonia;

@Service
public class ColoniasServiceImpl implements IColoniasService{

	@Autowired
	ColoniasRepository coloniasDao;
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Colonia findColoniaById(Integer id) {	   
		return coloniasDao.findColoniaByIdConverted(id);
	}



	
}
