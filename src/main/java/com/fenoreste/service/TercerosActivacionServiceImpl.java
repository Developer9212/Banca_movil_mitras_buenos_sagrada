package com.fenoreste.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.RegistroTercerosRepository;
import com.fenoreste.entity.productos_tercero_activacion;

@Service
public class TercerosActivacionServiceImpl implements ITercerosActivacionService {
	
	@Autowired
	RegistroTercerosRepository tercerosActivacionDao;
	@Override
	public productos_tercero_activacion findTerceroByOpa(Integer idorigenp, Integer idproducto, Integer idauxiliar,
			String usuario) {
		// TODO Auto-generated method stub
		return tercerosActivacionDao.findTerceroByOpa(idorigenp, idproducto, idauxiliar, usuario);
	}

	@Override
	public void save(Integer idorigenp, Integer idproducto, Integer idauxiliar, Timestamp date, String usuario) {
		tercerosActivacionDao.save(idorigenp, idproducto, idauxiliar, date, usuario);		
	}

	@Override
	public void modificar(Timestamp fecha, Integer idorigenp, Integer idproducto, Integer idauxiliar,String usuario) {
		tercerosActivacionDao.modificar(fecha, idorigenp, idproducto, idauxiliar,usuario);
	}

	@Override
	public void eliminar(Integer idorigenp, Integer idproducto, Integer idauxiliar, String usuario) {
		tercerosActivacionDao.eliminar(idorigenp, idproducto, idauxiliar, usuario);		
	}

}
