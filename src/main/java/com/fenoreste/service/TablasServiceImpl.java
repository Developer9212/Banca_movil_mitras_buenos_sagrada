package com.fenoreste.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.TablasRepository;
import com.fenoreste.entity.Tablas;

@Service
public class TablasServiceImpl implements ITablasService {
	
	@Autowired
	TablasRepository tablasDao;
	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public Tablas findTabla(String idtabla, String idelemento) {		
		return tablasDao.find1(idtabla, idelemento);
	}

	@Override
	public Tablas findIdtablaAndIdelemento(String idtabla, String idelemento) {
		System.out.println("IdTabla:"+idtabla+",Idelemento:"+idelemento);
		String consulta = "SELECT * FROM tablas WHERE idtabla='"+idtabla+"' and idelemento='"+idelemento+"'";
		System.out.println("LA cosulta es :"+consulta);
		int size=  jdbc.query(consulta,new BeanPropertyRowMapper<>(Tablas.class)).size();
		Tablas tb=null;
		if(size > 0) {
			tb = jdbc.query(consulta,new BeanPropertyRowMapper<>(Tablas.class)).get(0);
		}
		return tb;
	}

}
