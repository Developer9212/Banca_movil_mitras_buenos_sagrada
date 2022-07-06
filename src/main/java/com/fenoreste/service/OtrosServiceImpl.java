package com.fenoreste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.OtrosRepository;
import com.fenoreste.dao.UsuariosBancaMovilRepository;
import com.fenoreste.entity.FoliosTarjeta;
import com.fenoreste.entity.Sopar;
import com.fenoreste.entity.Tablas;

@Service
public class OtrosServiceImpl implements IOtrosService {

	@Autowired
	OtrosRepository otrosDao;
	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	ITablasService tablasService;
	@Override
	public Integer count_sopar(Integer idorigen, Integer idgrupo, Integer idsocio, String tipo) {
		return otrosDao.count_sopar(idorigen, idgrupo, idsocio, tipo) ;
	}

	@Override
	public String sesion() {
		return otrosDao.sesion();
	}

	@Override
	public boolean servicios_activos() {
		return otrosDao.servicios_activos();
	}

	@Override
	public boolean validaciones_sopar_bloqueo(Integer idorigen, Integer idgrupo, Integer idsocio) {
		boolean bandera = false;
		Tablas tb_sopar = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","sopar");
		String consulta = "SELECT * FROM sopar WHERE idorigen = "+idorigen+" AND idgrupo = "+idgrupo+" AND idsocio = "+idsocio+" AND tipo = '"+tb_sopar.getDato2()+"'";
		System.out.println("Consulta :"+ consulta);
		int size = jdbc.query(consulta,new BeanPropertyRowMapper<>(Sopar.class)).size();
		if(size > 0) {
			bandera = true;
		}
		return bandera;
	}
	
	@Override
	public boolean validaciones_sopar_cancelacion(Integer idorigen, Integer idgrupo, Integer idsocio) {
		boolean bandera = false;
		Tablas tb_sopar = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","banca_movil_cancelacion");
		String consulta = "SELECT * FROM sopar WHERE idorigen = "+idorigen+" AND idgrupo = "+idgrupo+" AND idsocio = "+idsocio+" AND tipo = '"+tb_sopar.getDato1()+"'";
		System.out.println("Consulta :"+ consulta);
		int size = jdbc.query(consulta,new BeanPropertyRowMapper<>(Sopar.class)).size();
		if(size > 0) {
			bandera = true;
		}
		return bandera;
	}

	@Override
	public void cambio_codificacion(String curp, Integer idorigen, Integer idgrupo, Integer idsocio) {
		otrosDao.update_encoding(curp,idorigen,idgrupo,idsocio);		
	}

	
	
	

}
