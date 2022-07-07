package com.fenoreste.dao;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fenoreste.entity.Colonia;

public interface ColoniasRepository  extends CrudRepository<Colonia,Integer>{

	@Query(value = "SELECT * FROM colonias WHERE idcolonia = ?1", nativeQuery = true)
	Colonia findColoniaById(Integer id);
	
	@Query(value = "SELECT idcolonia,"
			     + "(CASE WHEN lower(nombre) LIKE '%ñ%' THEN sai_convierte_caracteres_especiales_iso88591_utf8(nombre) END) as nombre,"
			     + "idmunicipio,codigopostal FROM colonias WHERE idcolonia = ?1", nativeQuery = true)
	Colonia findColoniaByIdConverted(Integer id);
	
	    
}
