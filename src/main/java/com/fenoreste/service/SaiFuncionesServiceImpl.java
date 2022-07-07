package com.fenoreste.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.saiFuncionesRepository;

@Service
public class SaiFuncionesServiceImpl implements ISaiFuncionesService{

	@Autowired
	saiFuncionesRepository saiFuncionesDao;
	
	@Override
	public String sai_auxiliar(Integer idorigenp, Integer idproducto, Integer idauxiliar) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_auxiliar(idorigenp, idproducto, idauxiliar);
	}

	@Override
	public String sai_estado_cuenta_ahorros(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fechaInicio,
			Date fechaFinal) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_estado_cuenta_ahorros(idorigenp, idproducto, idauxiliar, fechaInicio, fechaFinal);
	}

	@Override
	public String sai_estado_cuenta_prestamos(Integer idorigenp, Integer idproducto, Integer idauxiliar,
			Date fechaInicio, Date fechaFinal) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_estado_cuenta_prestamos(idorigenp, idproducto, idauxiliar, fechaInicio, fechaFinal);
	}

	@Override
	public String sai_estado_cuenta_inversiones(Integer idorigenp, Integer idproducto, Integer idauxiliar,
			Date fechaInicio, Date fechaFinal) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_estado_cuenta_inversiones(idorigenp, idproducto, idauxiliar, fechaInicio, fechaFinal);
	}

	@Override
	public String sai_calcula_saldo_promedio_diario(Integer idorigenp, Integer idproducto, Integer idauxiliar,
			Date fechaInicio, Date fechaFinal) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_calcula_saldo_promedio_diario(idorigenp, idproducto, idauxiliar, fechaInicio, fechaFinal);
	}

	@Override
	public String sai_prestamo_cuanto(Integer idorigenp, Integer idproducto, Integer idauxiliar,Date fecha,Integer tipoamortizacion, String sai) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_prestamo_cuanto(idorigenp, idproducto, idauxiliar,fecha,tipoamortizacion, sai);
	}

	@Override
	public String sai_prestamo_adelanto_intereses(Integer idorigenp, Integer idproducto, Integer idauxiliar,String sai) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_prestamo_adelanto_interes(idorigenp, idproducto, idauxiliar,sai);
	}

	@Override
	public String sai_aplica_transaccion(Date fecha, Integer idusuario, String sesion, String referencia) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_procesa_transaccion(fecha, idusuario, sesion, referencia);
	}

	@Override
	public String sai_limite_adelanto(Integer idorigenp, Integer idproducto, Integer idauxiliar, Double amount) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_limite_adelanto(idorigenp, idproducto, idauxiliar, amount);
	}

	@Override
	public String sai_detalle_transaccion_aplicada(Date fecha, Integer idusuario, String sesion, String referencia) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_detalle_transaccion_aplicada(fecha, idusuario, sesion, referencia);
	}

	@Override
	public String sai_termina_transaccion(Date fecha, Integer idusuario, String sesion, String referencia) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.sai_termina_transaccion(fecha, idusuario, sesion, referencia);
	}

	@Override
	public String monto_liquidacion_prestamo(Integer idorigenp, Integer idproducto, Integer idauxiliar, Date fecha) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.monto_liquidacion_prestamo(idorigenp, idproducto, idauxiliar, fecha);
	}

	@Override
	public String findPagosFuncion(Integer idorigenp, Integer idproducto, Integer idauxiliar) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findPagosFuncion(idorigenp, idproducto, idauxiliar);
	}

	@Override
	public List<Object[]> findamortizacionesSaicoopTodasIdAmortizacion(String texto,Integer idorigenp, Integer idproducto, Integer idauxiliar,String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal, String proximoPago,Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopTodasIdAmortizacion(texto,idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}
	
	@Override
	public List<Object[]> findamortizacionesSaicoopActivasIdAmortizacion(String texto,Integer idorigenp, Integer idproducto, Integer idauxiliar,String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal, String proximoPago,Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopActivasIdAmortizacion(texto,idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}
	
	@Override
	public List<Object[]> findamortizacionesSaicoopActivas(String texto,Integer idorigenp, Integer idproducto, Integer idauxiliar,String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal, String proximoPago,Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopActivas(texto,idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}


	@Override
	public String findIvaSegunSucursal(Integer idSucursal, Integer tipoAmortizacion, Integer idProducto) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findIvaSegunSucursal(idSucursal, tipoAmortizacion, idProducto);
	}

	@Override
	public List<Object[]> findamortizacionesSaicoopVencidosIdAmortizacion(String amortizaciones, Integer idorigenp,
			Integer idproducto, Integer idauxiliar, String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal,
			String proximoPago, Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopVencidosIdAmortizacion(amortizaciones, idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}

	@Override
	public List<Object[]> findamortizacionesSaicoopVencidos(String amortizaciones, Integer idorigenp,
			Integer idproducto, Integer idauxiliar, String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal,
			String proximoPago, Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopVencidos(amortizaciones, idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}

	@Override
	public List<Object[]> findamortizacionesSaicoopPagadasIdAmortizacion(String amortizaciones, Integer idorigenp,
			Integer idproducto, Integer idauxiliar, String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal,
			String proximoPago, Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopPagadasIdAmortizacion(amortizaciones, idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}

	@Override
	public List<Object[]> findamortizacionesSaicoopPagadas(String amortizaciones, Integer idorigenp, Integer idproducto,
			Integer idauxiliar, String fechaTrabajo, BigDecimal ivaIo, BigDecimal imTotal, String proximoPago,
			Pageable page) {
		// TODO Auto-generated method stub
		return saiFuncionesDao.findamortizacionesSaicoopPagadas(amortizaciones, idorigenp, idproducto, idauxiliar, fechaTrabajo, ivaIo, imTotal, proximoPago, page);
	}


	

}
