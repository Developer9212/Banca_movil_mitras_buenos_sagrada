package com.fenoreste.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.Auxiliares_dRepository;
import com.fenoreste.entity.Auxiliares;
import com.fenoreste.entity.Auxiliares_d;
import com.fenoreste.modelos.AuxiliaresdDTO;

@Service
public class Auxiliares_dServiceImpl implements IAuxiliares_dService{

	@Autowired
	Auxiliares_dRepository auxiliares_dDao;
	
	@Autowired
	JdbcTemplate jdbc;
	
	
	@Override
	public List<Auxiliares_d> findAuxiliares_dByOpa(Integer idorigenp, Integer  idproducto, Integer  idauxiliar) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findAuxiliares_dByOpa(idorigenp, idproducto, idauxiliar);
	}

	@Override
	public List<Auxiliares_d> findAuxiliares_dByOpaFecha(Integer  idorigenp, Integer  idproducto, Integer  idauxiliar, Date fechaI,
			Date fechaF) {
		return auxiliares_dDao.findAuxiliares_dByOpaFecha(idorigenp, idproducto, idauxiliar, fechaI, fechaF);
	}

	@Override
	public Double findSaldoUltimas24Horas(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findSaldoUltimas24Horas(idorigenp, idproducto, idauxiliar, fecha);
	}

	@Override
	public Double findSaldoUltimas48Horas(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findSaldoUltimas48Horas(idorigenp, idproducto, idauxiliar, fecha);
	}

	@Override
	public Auxiliares_d findUltimoRegistro(Integer idorigenp, Integer idproducto, Integer idauxiliar) {
		// TODO Auto-generated method stub
		int size =jdbc.query("SELECT * FROM auxiliares_d WHERE idorigenp="+idorigenp+" AND idproducto="+idproducto+" AND idauxiliar="+idauxiliar+" ORDER BY fecha DESC limit 1",new BeanPropertyRowMapper<>(Auxiliares_d.class)).size();
		Auxiliares_d ad=null;
		if(size>0) {
			ad = jdbc.query("SELECT * FROM auxiliares_d WHERE idorigenp="+idorigenp+" AND idproducto="+idproducto+" AND idauxiliar="+idauxiliar+" ORDER BY fecha DESC limit 1",new BeanPropertyRowMapper<>(Auxiliares_d.class)).get(0);
		}
		return ad;//auxiliares_dDao.findUltimoRegistro(idorigenp, idproducto, idauxiliar);
	}

	@Override
	public Double findSaldoMasDe48Horas(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findSaldoMasDe48Horas(idorigenp, idproducto, idauxiliar, fecha);
	}

	@Override
	public List<Object[]> findUltimos5Movimientos(Integer idorigenp, Integer idproducto, Integer idauxiliar) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findUltimos5Movimientos(idorigenp, idproducto, idauxiliar);
	}

	@Override
	public List<Object[]> findMovimientosFechasAsc(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date FInicio, Date FFinal, Pageable pageable) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findMovimientosFechasAsc(idorigenp, idproducto, idauxiliar, FInicio, FFinal, pageable);
	}
	
	@Override
	public List<Object[]> findMovimientosFechasDesc(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date FInicio, Date FFinal, Pageable pageable) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findMovimientosFechasDesc(idorigenp, idproducto, idauxiliar, FInicio, FFinal, pageable);
	}

	@Override
	public Auxiliares_d findByTransaccion(Integer transaccion) {
		String consulta = "SELECT * FROM auxiliares_d WHERE transaccion="+transaccion;
		int size = jdbc.query(consulta, new BeanPropertyRowMapper<>(Auxiliares_d.class)).size();
		
		System.out.println("Consulta : "+ consulta);
		Auxiliares_d ad = null;
		if(size > 0) {
			ad = jdbc.query("SELECT * FROM auxiliares_d WHERE transaccion="+transaccion, new BeanPropertyRowMapper<>(Auxiliares_d.class)).get(0);
		}
	     return ad;//auxiliares_dDao.findMovimientosByIdTransaccion(transaccion);
	}

	@Override
	public List<Auxiliares_d> findAuxiliares_dByOpaAndFechaAndPoliza(Integer idorigenp, Integer idproducto,
			Integer idauxiliar, Date fecha, String poliza) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.findAuxiliares_dByOpaAndFechaAndPoliza(idorigenp, idproducto, idauxiliar, fecha, poliza);
	}

	@Override
	public Double montoPesos(Integer idorigen, Integer idgrupo, Integer idsocio, Date fecha) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.montoPesos(idorigen, idgrupo, idsocio, fecha);
	}

	@Override
	public Double montoUdis(Integer idorigen, Integer idgrupo, Integer idsocio, String periodo) {
		// TODO Auto-generated method stub
		return auxiliares_dDao.montoUdis(idorigen, idgrupo, idsocio, periodo);
	}
	
	
}