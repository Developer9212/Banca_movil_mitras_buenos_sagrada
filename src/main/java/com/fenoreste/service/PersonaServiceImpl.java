package com.fenoreste.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenoreste.dao.PersonaRepository;
import com.fenoreste.entity.Persona;


@Service
public class PersonaServiceImpl implements IPersonaService {
    
	@Autowired
	PersonaRepository personaDao;
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	IOtrosService otrosService;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(Long id) {
		return personaDao.findById(id).orElse(null);
	}
	
	@Override
	public Persona findByOGS(Integer idorigen, Integer idgrupo, Integer idsocio) {
		Persona p = null;
		String consulta = " SELECT * FROM personas WHERE idorigen="+idorigen+" AND idgrupo = "+idgrupo+" AND idsocio = "+idsocio;
		try {			
			int tamaño = jdbc.query(consulta,new BeanPropertyRowMapper<>(Persona.class)).size();
			if(tamaño > 0) {
		       p = jdbc.query(consulta,new BeanPropertyRowMapper<>(Persona.class)).get(0);		
			}			
		} catch (Exception e) {
           System.out.println("Error al buscar la persona por ogs:"+e.getMessage());
           if(e.getMessage().toLowerCase().replace(" ","").contains("invalidcharacterdatawasfound")) { 
        	   System.out.println("Convirtiendo caractere de iso a utf8");
        	  consulta= "SELECT * FROM convertir_datos_persona('',"+idorigen+","+idgrupo+","+idsocio+")";			   
			  p = jdbc.query(consulta,new BeanPropertyRowMapper<>(Persona.class)).get(0);
			  System.out.println("Convertido:"+p.getNombre()+","+p.getAppaterno()+","+p.getApmaterno());
			  return p;			 
			}           
		}
		return p;
	}
	
	@Override
	public List<String>findByOGS2(Integer idorigen, Integer idgrupo, Integer idsocio) {		
		return personaDao.findByOGS2(idorigen, idgrupo, idsocio);
	}

	@Override
	public Persona save(Persona persona) {
		return personaDao.save(persona);
	}

	@Override
	public void deleteById(Long id) {
		personaDao.deleteById(id);		
	}

	
	@Override
	public Persona findPersonaMatriculacion(String nombre,String apellidos,String curp,String email,String celular,String telefono) {
	return personaDao.findPersonaMatriculacion(nombre,apellidos,curp,email,celular,telefono);
	}
	
	@Override
	public Persona findPersonaMatriculacionJdbc(String nombre,String apellidos,String curp,String email,String celular,String telefono) {
		Persona p = null;
		String consulta = "";
		try {
		consulta = "SELECT * FROM personas p WHERE "
		            + "  replace(upper(p.curp),' ','')='" + curp.replace(" ", "").trim().toUpperCase() + "'"
		            + " AND UPPER(REPLACE(p.nombre,' ',''))='"+nombre.toUpperCase().replace(" ", "").trim() + "'"
		            + " AND UPPER(replace(appaterno,' ',''))||''||UPPER(replace(p.apmaterno,' ','')) LIKE ('%"+apellidos.toUpperCase().trim().replace(" ", "").replace("Ñ","%").toUpperCase() + "%')"
		            + " AND (CASE WHEN email IS NULL THEN '' ELSE trim(upper(email)) END)='" + email.toUpperCase().trim() + "'"
		            + " AND (CASE WHEN celular IS NULL THEN '' ELSE trim(celular) END)='" + celular.trim() + "' AND idgrupo=10 AND estatus=true ORDER BY fechaingreso desc LIMIT 1";
			
			int size = jdbc.query(consulta,new BeanPropertyRowMapper<>(Persona.class)).size();
		   if(size > 0) {
    		    p = jdbc.query(consulta,new BeanPropertyRowMapper<>(Persona.class)).get(0);
	      }
		  System.out.println("Consulta para buscar personas :"+consulta);	
		} catch (Exception e) {
			System.out.println("Error en spring busqueda persona :"+e.getMessage());
			if(e.getMessage().toLowerCase().replace(" ","").contains("invalidcharacterdatawasfound")) { 
			   System.out.println("Consultando datos iso");		
			   consulta= "SELECT * FROM convertir_datos_persona('"+curp+"',0,0,0) p WHERE "
			            + "  replace(upper(p.curp),' ','')='" + curp.replace(" ", "").trim().toUpperCase() + "'"
			            + " AND UPPER(REPLACE(p.nombre,' ',''))='"+nombre.toUpperCase().replace(" ", "").trim() + "'"
			            + " AND UPPER(replace(appaterno,' ',''))||''||UPPER(replace(p.apmaterno,' ','')) LIKE ('%"+apellidos.toUpperCase().trim().replace(" ", "").replace("Ñ","%").toUpperCase() + "%')"
			            + " AND (CASE WHEN email IS NULL THEN '' ELSE trim(upper(email)) END)='" + email.toUpperCase().trim() + "'"
			            + " AND (CASE WHEN celular IS NULL THEN '' ELSE trim(celular) END)='" + celular.trim() + "' AND idgrupo=10 AND estatus=true ORDER BY fechaingreso desc LIMIT 1";
			   
			   p = jdbc.query(consulta,new BeanPropertyRowMapper<>(Persona.class)).get(0);
			   System.out.println("La persona es :"+p.getNombre()+" "+p.getAppaterno()+" "+p.getApmaterno());
			   return p;
			}
		}

	    return p;
	}
	
	
}
