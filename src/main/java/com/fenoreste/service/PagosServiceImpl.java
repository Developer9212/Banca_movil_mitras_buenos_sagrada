package com.fenoreste.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.PagosRepository;
import com.fenoreste.entity.Pagos;

@Service
public class PagosServiceImpl implements IPagosService{
	
	@Autowired
	PagosRepository pagosDao;
	@Autowired
	JdbcTemplate jdbc;

	/*@Override
	public void deleteByOpa(Integer idorigenp, Integer idproducto, Integer idauxiliar) {
		 String sql = "DELETE FROM amortizaciones_cubiertas_abonos WHERE idorigenp =? AND idproducto = ? and idauxiliar =?";
	     Object[] args = new Object[] {idorigenp,idproducto,idauxiliar};
	     int updated = jdbc.update(sql, args);
	     System.out.println("Registros eliminados :"+updated);
	}*/


	@Override
	public List<Object[]> findPagosByOpa(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date FechaInicio,Date FechaFinal, Pageable page) {
		// TODO Auto-generated method stub
		return pagosDao.findPagosByOpa(idorigenp, idproducto, idauxiliar, FechaInicio,FechaFinal, page);
	}

	}
