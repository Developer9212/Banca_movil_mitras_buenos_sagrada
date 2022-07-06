package com.fenoreste.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fenoreste.entity.Auxiliares;
import com.fenoreste.entity.Auxiliares_d;
import com.fenoreste.entity.FoliosTarjeta;
import com.fenoreste.entity.Origenes;
import com.fenoreste.entity.Productos;
import com.fenoreste.entity.Tablas;
import com.fenoreste.modelos.AccountDetailsDTO;
import com.fenoreste.modelos.AccountLastMovementDTO;
import com.fenoreste.modelos.opaDTO;
import com.fenoreste.util.HerramientasUtil;

@Service
public class AccountServiceSpring {

	@Autowired
	HerramientasUtil util;
	@Autowired
	IAuxiliaresService auxiliaresService;
	@Autowired
	IAuxiliares_dService auxiliares_dService;
	@Autowired
	IProductosService productosService;
	@Autowired
	IOrigenesService origeneseService;
	@Autowired
	ITablasService tablasService;
	@Autowired
	IFoliosTarjetasService foliosService;
	@Autowired
	ISaiFuncionesService funcionesService;

	public AccountDetailsDTO getAccountDetails(String accountId) {
		opaDTO opa = util.opa(accountId);
		AccountDetailsDTO cuenta = new AccountDetailsDTO();
		String fechaOrigen = origeneseService.fechaTrabajo();
		try {
			Auxiliares aux = auxiliaresService.findAuxiliaresByOPA(opa.getIdorigenp(), opa.getIdproducto(),
					opa.getIdauxiliar());
			Productos prod = productosService.findProductoById(aux.getIdproducto());
			// Obtenemos saldo segun las horas pasadas
			double saldo24 = 0.0, saldo48 = 0.0, saldo_mas_de_48_horas = 0.0, saldo_promedio_mensual = 0.0;
			double arreglo_saldos[] = null;

			if (origeneseService.findMatrizOrigen().getNombre().toUpperCase().replace(" ", "").contains("SANNICOLAS")) {
				System.out.println("Entro a csn");
				// Busco la tabla para saber cual es el producto tdd
				Tablas tb_producto_tdd = tablasService.findTabla("bankingly_banca_movil", "producto_tdd");
				if (opa.getIdproducto() == Integer.parseInt(tb_producto_tdd.getDato1())) {
					FoliosTarjeta folio_tdd = foliosService.findFolioByOpa(opa.getIdorigenp(), opa.getIdproducto(),
							opa.getIdauxiliar());
					String saldo_tdd = "";// consumosService.getBalanceQuery(folio_tdd.getIdtarjeta());
					JSONObject json_saldo = new JSONObject(saldo_tdd);
					saldo24 = Double.parseDouble(json_saldo.getString("availableAmount"));
					saldo48 = Double.parseDouble(json_saldo.getString("availableAmount"));
					saldo_mas_de_48_horas = Double.parseDouble(json_saldo.getString("availableAmount"));
				} else {
					arreglo_saldos = getSaldoAuxiliaresD(opa.getIdorigenp(), opa.getIdproducto(), opa.getIdauxiliar(),
							substractDate(1, fechaOrigen), substractDate(1, fechaOrigen));
					saldo24 = arreglo_saldos[0];
					saldo48 = arreglo_saldos[1];
					saldo_mas_de_48_horas = arreglo_saldos[2];
				}
			} else {
				arreglo_saldos = getSaldoAuxiliaresD(opa.getIdorigenp(), opa.getIdproducto(), opa.getIdauxiliar(),
						substractDate(1, fechaOrigen), substractDate(2, fechaOrigen));
				saldo24 = arreglo_saldos[0];
				saldo48 = arreglo_saldos[1];
				saldo_mas_de_48_horas = arreglo_saldos[2];
			}

			// Movimient
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha_final_saldo_promedio = sdf.parse(fechaOrigen);
			List<Auxiliares_d> listaMov = auxiliares_dService.findAuxiliares_dByOpaFecha(opa.getIdorigenp(),
					opa.getIdproducto(), opa.getIdauxiliar(), subtractIntervalMonth(fechaOrigen),
					fecha_final_saldo_promedio);
			Origenes origen = origeneseService.findOrigenById(opa.getIdorigenp());
			String calculo_promedio = funcionesService.sai_calcula_saldo_promedio_diario(opa.getIdorigenp(),
					opa.getIdproducto(), opa.getIdauxiliar(), subtractIntervalMonth(fechaOrigen),
					fecha_final_saldo_promedio);
			cuenta.setAccountBankIdentifier(accountId);
			cuenta.setAccountOfficerName(prod.getNombre());
			cuenta.setAccountAvailableBalance(aux.getSaldo().doubleValue());
			cuenta.setAccountBalance24Hrs(saldo24);
			cuenta.setAccountBalance48Hrs(saldo48);
			cuenta.setAccountBalance48MoreHrs(saldo_mas_de_48_horas);
			cuenta.setMonthlyAverageBalance(Double.parseDouble(calculo_promedio.replace(",", "")));
			cuenta.setPendingChecks(0);
			cuenta.setChecksToReleaseToday(0);
			cuenta.setChecksToReleaseTomorrow(0);
			cuenta.setCancelledChecks(0);
			cuenta.setCertifiedChecks(0);
			cuenta.setRejectedChecks(0);
			cuenta.setBlockedAmount(0);
			cuenta.setMovementsOfTheMonth(listaMov.size());
			cuenta.setChecksDrawn(0);
			cuenta.setOverdrafts(0.0);
			cuenta.setProductBranchName(prod.getNombre());
			cuenta.setProductOwnerName(origen.getNombre());
			cuenta.setShowCurrentAccountChecksInformation(false);// Se oculta cuadro de cheques
			cuenta.setAccountCountableBalance(0.0);

		} catch (Exception e) {
			System.out.println("Error en GetAccountDetails:" + e.getMessage());
		}
		return cuenta;

	}

	public List<AccountLastMovementDTO> getAccountLast5Movements(String accountId) {
		opaDTO opa = util.opa(accountId);
		String Description = "";
		List<AccountLastMovementDTO> Last5Movements = new ArrayList<>();
		try {
			int movementTypeId = 0;
			List<Object[]> ListaAuxiliaresD = auxiliares_dService.findUltimos5Movimientos(opa.getIdorigenp(),
					opa.getIdproducto(), opa.getIdauxiliar());
			boolean isDebito = false;
			for (Object[] obj : ListaAuxiliaresD) {
				AccountLastMovementDTO cuenta = new AccountLastMovementDTO();
				Productos producto = productosService.findProductoById(opa.getIdproducto());
				if (Integer.parseInt(obj[1].toString()) == 0) {
					movementTypeId = 2;
					Description = "retiro";
					isDebito = true;
				} else {
					movementTypeId = 3;
					Description = "Deposito";
					isDebito = false;
				}
				double monto = Double.parseDouble(obj[2].toString()) + Double.parseDouble(obj[3].toString())
						+ Double.parseDouble(obj[4].toString()) + Double.parseDouble(obj[5].toString())
						+ Double.parseDouble(obj[6].toString());

				DecimalFormat formato1 = new DecimalFormat("#.00");
				cuenta.setAccountBankIdentifier(accountId);
				cuenta.setMovementId(Integer.parseInt(obj[7].toString()));
				cuenta.setDescription(producto.getNombre());
				cuenta.setAmount(Double.parseDouble(formato1.format(monto)));
				cuenta.setBalance(Double.parseDouble(obj[8].toString()));
				cuenta.setMovementTypeId(movementTypeId);
				cuenta.setTypeDescription(Description);
				cuenta.setCheckId(null);
				cuenta.setVoucherId(obj[7].toString());
				cuenta.setDebit(isDebito);
				cuenta.setMovementDate(obj[0].toString());
				Last5Movements.add(cuenta);

			}

		} catch (Exception e) {
			System.out.println("Error en GetAccountLast5Movements:" + e.getMessage());
		}
		return Last5Movements;
	}

	public List<AccountLastMovementDTO> getAccountMovements(String productBankIdentifier, String dateFromFilter,
			String dateToFilter, int pageSize, int pageStartIndex, String orderBy, int channel) {
		String Description = "";
		List<AccountLastMovementDTO> ListaDTO = new ArrayList<>();
		
		opaDTO opa = util.opa(productBankIdentifier);
		List<Object[]> Lista_Movs = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date finicial = sdf.parse(dateFromFilter);
			Date ffinal = sdf.parse(dateToFilter);
			switch (channel) {
			case 1:
				switch (orderBy.toUpperCase().trim().replace(" ", "")) {
				case "MOVEMENTDATEASC":
					// Busqueda por rango de fechas
					if (!dateFromFilter.equals("") && !dateToFilter.equals("")) {
						finicial = sdf.parse(dateFromFilter);
						ffinal = sdf.parse(dateToFilter);
						// Corro el query buscando por rango de fechas ordenado por fecha ASC
						Lista_Movs = auxiliares_dService.findMovimientosFechasAsc(opa.getIdorigenp(),
								opa.getIdproducto(), opa.getIdauxiliar(), finicial, ffinal,
								PageRequest.of(pageStartIndex, pageSize));
					}
					break;
				case "MOVEMENTDATEDESC":
					// Busqueda por rango de fechas
					if (!dateFromFilter.equals("") && !dateToFilter.equals("")) {
						finicial = sdf.parse(dateFromFilter);
						ffinal = sdf.parse(dateToFilter);
						// Corro el query buscando por rango de fechas ordenado por fecha ASC
						Lista_Movs = auxiliares_dService.findMovimientosFechasDesc(opa.getIdorigenp(),
								opa.getIdproducto(), opa.getIdauxiliar(), finicial, ffinal,
								PageRequest.of(pageStartIndex, pageSize));
					}
					break;
				case "MOVEMENTDATE":
					// Busqueda por rango de fechas
					if (!dateFromFilter.equals("") && !dateToFilter.equals("")) {
						finicial = sdf.parse(dateFromFilter);
						ffinal = sdf.parse(dateToFilter);
						// Corro el query buscando por rango de fechas ordenado por fecha ASC
						Lista_Movs = auxiliares_dService.findMovimientosFechasDesc(opa.getIdorigenp(),
								opa.getIdproducto(), opa.getIdauxiliar(), finicial, ffinal, null);
					}
					break;
				}
				break;
			case 5:
				Lista_Movs = auxiliares_dService.findMovimientosFechasDesc(opa.getIdorigenp(), opa.getIdproducto(),
						opa.getIdauxiliar(), finicial, ffinal, null);
				break;
			}

			int movementTypeId = 0;
			boolean isDebit = false;
			for (Object[] obj : Lista_Movs) {
				AccountLastMovementDTO cuenta = new AccountLastMovementDTO();
				Productos producto = productosService.findProductoById(opa.getIdproducto());
				if (Integer.parseInt(obj[1].toString()) == 0) {
					movementTypeId = 2;
					Description = "retiro";
					isDebit = true;
				} else {
					movementTypeId = 3;
					Description = "Deposito";
					isDebit = false;
				}
				double monto = Double.parseDouble(obj[2].toString()) + Double.parseDouble(obj[3].toString())
						+ Double.parseDouble(obj[4].toString()) + Double.parseDouble(obj[5].toString())
						+ Double.parseDouble(obj[6].toString());

				DecimalFormat formato1 = new DecimalFormat("#.00");
				cuenta.setAccountBankIdentifier(productBankIdentifier);
				cuenta.setMovementId(Integer.parseInt(obj[7].toString()));
				cuenta.setDescription(producto.getNombre());
				cuenta.setAmount(Double.parseDouble(formato1.format(monto)));
				cuenta.setBalance(Double.parseDouble(obj[8].toString()));
				cuenta.setMovementTypeId(movementTypeId);
				cuenta.setTypeDescription(Description);
				cuenta.setCheckId(null);
				cuenta.setVoucherId(obj[7].toString());
				cuenta.setDebit(isDebit);
				cuenta.setMovementDate(obj[0].toString());
				ListaDTO.add(cuenta);
			}

		} catch (Exception e) {
			System.out.println("Error en obtener movimientos:" + e.getMessage());
		}
		return ListaDTO;
	}

	// Metodo que no ayuda a obtener saldo por rango de fechas, se utilizo en el
	// metodo de getAccountDetails
	public double[] getSaldoAuxiliaresD(int o, int p, int a, Date fecha_hace_24_horas, Date fecha_hace_48_horas) {
		double saldos[] = new double[3];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// Si hay fecha de inicio
			Double saldo_ultimas_24_hora = auxiliares_dService.findSaldoUltimas24Horas(o, p, a, fecha_hace_24_horas);
			Double saldo_ultimas_48_hora = auxiliares_dService.findSaldoUltimas48Horas(o, p, a, fecha_hace_48_horas);
			Double saldo_mas_48_horas = auxiliares_dService.findSaldoMasDe48Horas(o, p, a, fecha_hace_48_horas);

			saldos[0] = saldo_ultimas_24_hora;
			saldos[1] = saldo_ultimas_48_hora;
			saldos[2] = saldo_mas_48_horas;
		} catch (Exception e) {
			System.out.println("Error en obtener  saldo auxiliares_d:" + e.getMessage());
		}
		return saldos;
	}

	public static Date substractDate(int numeroDias, String fecha) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		// Si vamos a usar la fecha en tiempo real date=fechaActual
		// date = fechaActual;
		try {
			date = d.parse(fecha);
		} catch (ParseException ex) {
			System.out.println("Error al parsear fecha:" + ex.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -numeroDias);
		return cal.getTime();
	}

	public static Date subtractIntervalMonth(String fecha) {
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = d.parse(fecha);
		} catch (ParseException ex) {
			System.out.println("Error al parsear fecha:" + ex.getMessage());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public int contadorMovs(String opa, String fechaInicio, String fechaFinal) {
		List<Object[]> lista = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		opaDTO opa_v = util.opa(opa);
		try {
			Date fInicio = sdf.parse(fechaInicio);
			Date fFinal = sdf.parse(fechaFinal);
			lista = auxiliares_dService.findMovimientosFechasDesc(opa_v.getIdorigenp(), opa_v.getIdproducto(),
					opa_v.getIdauxiliar(), fInicio, fFinal, null);
		} catch (Exception e) {
			System.out.println("Error al obtener total de registros:" + e.getMessage());
		}
		return lista.size();
	}
}
