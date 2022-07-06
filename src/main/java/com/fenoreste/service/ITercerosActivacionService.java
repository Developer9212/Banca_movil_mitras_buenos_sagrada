package com.fenoreste.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fenoreste.entity.productos_tercero_activacion;

@Service
public interface ITercerosActivacionService {

	public productos_tercero_activacion findTerceroByOpa(Integer idorigenp, Integer idproducto, Integer idauxiliar,String usuario);
	public void save(Integer idorigenp, Integer idproducto, Integer idauxiliar,Timestamp date,String usuario);
	public void modificar(Timestamp fecha,Integer idorigenp,Integer idproducto,Integer idauxiliar,String usuario);
	public void eliminar(Integer idorigenp,Integer idproducto,Integer idauxiliar,String usuario);
}
