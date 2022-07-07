package com.fenoreste.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.fenoreste.entity.Auxiliares_d;
import com.fenoreste.modelos.AuxiliaresdDTO;

@Service
public interface IAuxiliares_dService {

	public List<Auxiliares_d> findAuxiliares_dByOpa(Integer idorigenp, Integer idproducto, Integer idauxiliar);
	public List<Auxiliares_d> findAuxiliares_dByOpaFecha(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date fechaI, Date fechaF);
	public Double findSaldoUltimas24Horas(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha);
	public Double findSaldoUltimas48Horas(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha);
	public Double findSaldoMasDe48Horas(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha);
	public Auxiliares_d findUltimoRegistro(Integer idorigenp, Integer idproducto, Integer idauxiliar);
	public List<Object[]> findUltimos5Movimientos(Integer idorigenp, Integer idproducto, Integer idauxiliar);	
	public List<Object[]> findMovimientosFechasDesc(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date FInicio,Date FFinal,Pageable pageable);
	public List<Object[]> findMovimientosFechasAsc(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date FInicio,Date FFinal,Pageable pageable);
	
	public  Double montoPesos(Integer idorigen, Integer idgrupo,Integer idsocio,Date fecha);
	public  Double montoUdis(Integer idorigen,Integer idgrupo,Integer idsocio,String periodo);	
	public Auxiliares_d findByTransaccion(Integer transaccion);
	public List<Auxiliares_d> findAuxiliares_dByOpaAndFechaAndPoliza(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date fecha,String poliza);
	
		
}
