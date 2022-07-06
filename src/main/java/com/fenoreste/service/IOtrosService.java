package com.fenoreste.service;

import org.springframework.stereotype.Service;

import com.fenoreste.entity.FoliosTarjeta;

@Service
public interface IOtrosService {

	public Integer count_sopar(Integer idorigen,Integer idgrupo,Integer idsocio,String tipo);
	
	public String sesion();
	public boolean servicios_activos();
	public boolean validaciones_sopar_bloqueo(Integer idorigen,Integer idgrupo,Integer idsocio);
	public boolean validaciones_sopar_cancelacion(Integer idorigen,Integer idgrupo,Integer idsocio);
	public void cambio_codificacion(String curp,Integer idorigen,Integer idgrupo,Integer idsocio);
}
