package com.fenoreste.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

public interface IPagosService {

	public List<Object[]> findPagosByOpa(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date FechaInicio,Date FechaFinal,Pageable page);
	
}