package com.fenoreste.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;



@Service
public interface ISaiFuncionesService {

	public String sai_auxiliar(Integer idorigenp,Integer idproducto,Integer idauxiliar);
	public String sai_estado_cuenta_ahorros(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fechaInicio,Date fechaFinal);
	public String sai_estado_cuenta_prestamos(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fechaInicio,Date fechaFinal);
	public String sai_estado_cuenta_inversiones(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fechaInicio,Date fechaFinal);
	public String sai_calcula_saldo_promedio_diario(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fechaInicio,Date fechaFinal);
	public String sai_prestamo_cuanto(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fecha,Integer tipoamortizacion,String sai);
	public String sai_prestamo_adelanto_intereses(Integer idorigenp,Integer idproducto,Integer idauxiliar,String sai);
	public String sai_aplica_transaccion(Date fecha,Integer idusuario,String sesion,String referencia);
	public String sai_limite_adelanto(Integer idorigenp,Integer idproducto,Integer idauxiliar,Double amount);
	public String sai_detalle_transaccion_aplicada(Date fecha,Integer idusuario,String sesion,String referencia);
	public String sai_termina_transaccion(Date fecha,Integer idusuario,String sesion,String referencia);
	public String monto_liquidacion_prestamo(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fecha);
	public String findPagosFuncion(Integer idorigenp,Integer idproducto,Integer idauxiliar);
	public List<Object[]> findamortizacionesSaicoopTodasIdAmortizacion(String texto,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);
	public List<Object[]> findamortizacionesSaicoopActivasIdAmortizacion(String texto,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);
	public List<Object[]> findamortizacionesSaicoopActivas(String amortizaciones,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);
	public List<Object[]> findamortizacionesSaicoopVencidosIdAmortizacion(String amortizaciones,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);
	public List<Object[]> findamortizacionesSaicoopVencidos(String amortizaciones,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);
	public List<Object[]> findamortizacionesSaicoopPagadasIdAmortizacion(String amortizaciones,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);
	public List<Object[]> findamortizacionesSaicoopPagadas(String amortizaciones,Integer idorigenp,Integer idproducto,Integer idauxiliar,String fechaTrabajo,BigDecimal ivaIo,BigDecimal imTotal,String proximoPago,Pageable page);

	public String findIvaSegunSucursal(Integer idSucursal,Integer tipoAmortizacion,Integer idProducto);
	
}
