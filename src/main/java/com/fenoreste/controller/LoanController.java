package com.fenoreste.controller;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenoreste.entity.Auxiliares;
import com.fenoreste.modelos.LoanDTO;
import com.fenoreste.modelos.LoanFee;
import com.fenoreste.modelos.LoanPayment;
import com.fenoreste.modelos.LoanRate;
import com.fenoreste.modelos.opaDTO;
import com.fenoreste.service.IAmortizacionesService;
import com.fenoreste.service.IAuxiliaresService;
import com.fenoreste.service.IAuxiliares_dService;
import com.fenoreste.service.IOrigenesService;
import com.fenoreste.service.IOtrosService;
import com.fenoreste.service.IPagosService;
import com.fenoreste.service.LoanServiceSpring;
import com.fenoreste.util.HerramientasUtil;
import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
@CrossOrigin(origins = "")
@RequestMapping({ "/Loan" })
public class LoanController {

	@Autowired
	LoanServiceSpring loanService;
	@Autowired 
	IAmortizacionesService amortizacionesService;
	
	@Autowired
	IOtrosService otrosService;
	
	@Autowired
	IPagosService pagosService;
	
	@Autowired
	IOrigenesService origenesService;
	
	@Autowired
	IAuxiliaresService auxiliaresService;
	
	@Autowired
	HerramientasUtil util;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getLoan(@RequestBody String cadena) {
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
		}
		
		System.out.println("Cadena get loan:" + cadena);
		String productBankIdentifier = "";
		JsonObject Error = new JsonObject();

		try {
			JSONObject jsonRecibido = new JSONObject(cadena);
			productBankIdentifier = jsonRecibido.getString("productBankIdentifier");
		} catch (Exception e) {
			Error.put("Error", "Error en parametros JSON:" + e.getMessage());
			return ResponseEntity.status(500).body(Error);
		}

		int count = 0;

		LoanDTO loan = loanService.Loan(productBankIdentifier);
		JsonObject j = new JsonObject();
		j.put("Loan", loan);
		return ResponseEntity.status(200).body(j);
	}

	@PostMapping(value = "/Fee", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getLoanFee(@RequestBody String cadena) {
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
		}
		String productBankIdentifier = "";
		JsonObject Error = new JsonObject();
		int feeNumber = 0;
		try {
			JSONObject jsonRecibido = new JSONObject(cadena);
			productBankIdentifier = jsonRecibido.getString("productBankIdentifier");
			feeNumber = jsonRecibido.getInt("feeNumber");
		    LoanFee loan = loanService.LoanFee(productBankIdentifier, feeNumber);
		    JsonObject j = new JsonObject();
		j.put("Fee", loan);
		return ResponseEntity.status(200).body(j);
		} catch(Exception e) {
		   System.out.println("Error en metodo loan Fee:"+e.getMessage());
		   Error.put("Error",e.getMessage());
		   return ResponseEntity.status(500).body(e.getMessage());
	    }
		
	}

	@PostMapping(value = "/Fees", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity getLoanFees(@RequestBody String cadena) {	
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
		}
		String productBankIdentifier = "";
		int feesStatus = 0, pageSize = 0, pageStartIndex = 0;
		JsonObject j = new JsonObject();
		try {
			    String order = "";
			    JSONObject jsonRecibido = new JSONObject(cadena);
			    System.out.println("Cadena Fees result data: "+jsonRecibido);
				productBankIdentifier = jsonRecibido.getString("productBankIdentifier");
				feesStatus = jsonRecibido.getInt("feesStatus");
				int ChannelId = jsonRecibido.getInt("ChannelId");
				JSONObject paging = jsonRecibido.getJSONObject("paging");
				if(!paging.toString().contains("null")) {
					pageSize = paging.getInt("pageSize");
					pageStartIndex = paging.getInt("pageStartIndex");
					if(String.valueOf(pageStartIndex).length() == 2) {
						pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,1));
					}else if(String.valueOf(pageStartIndex).length() == 3) {
						pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,2));
					}else if(String.valueOf(pageStartIndex).length() == 4) {
						pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,3));
					}else if(String.valueOf(pageStartIndex).length() == 5) {
						pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,4));
					}else if(String.valueOf(pageStartIndex).length() == 6) {
						pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,5));
					}
					order = paging.getString("orderByField");
				}
				opaDTO opa=util.opa(productBankIdentifier);					
				List<LoanFee> loan = loanService.LoanFees(productBankIdentifier, feesStatus, pageSize, pageStartIndex, order,ChannelId);
				int feesCount = loanService.LoanFees(productBankIdentifier, feesStatus, pageSize, pageStartIndex, order,5).size();			
				j.put("Fees", loan);
				j.put("LoanFeesCount",feesCount);
	
			
		} catch (Exception e) {
			System.out.println("Error en metodo loan Fees ws:" + e.getMessage());
		}
		return ResponseEntity.status(200).body(j);
	}

	@PostMapping(value = "/Rates", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity getLoanRates(@RequestBody String cadena) {	
	   System.out.println("Entrando a rates con:"+cadena);
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
	  	 }
		String productBankIdentifier = "";
		JsonObject Error = new JsonObject();
		int pageSize = 0, pageStartIndex = 0;		
		JsonObject j = new JsonObject();
		    try {
		    	JSONObject jsonRecibido = new JSONObject(cadena);
				productBankIdentifier = jsonRecibido.getString("productBankIdentifier");
				JSONObject paging = jsonRecibido.getJSONObject("paging");
				if(!paging.toString().toLowerCase().contains("null")) {
					pageSize = paging.getInt("pageSize");
					pageStartIndex = paging.getInt("pageStartIndex");
				}
    			List<LoanRate> loanRate = loanService.LoanRates(productBankIdentifier, pageSize, pageStartIndex);   			
				j.put("Rates", loanRate);
				j.put("LoanRatesCount", loanRate.size());
				 System.out.println("saliendo en rates con json:"+j);
				return ResponseEntity.status(200).body(j);
				
			} catch (Exception e) {
				System.out.println("Error en metodo loan rates:"+e.getMessage());
				Error.put("Error", e.getMessage());
				return ResponseEntity.status(500).body(Error);
			}
		   
		    
	}
	
	@PostMapping(value = "/Payments", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity getLoanPayments(@RequestBody String cadena) {	
		System.out.println("Entrando a loanPayments con cadena:"+cadena);
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
		}		
		String productBankIdentifier = "";
		JsonObject Error = new JsonObject();
		int pageSize = 0, pageStartIndex = 0,channelId = 0;
		try {
			JSONObject jsonRecibido = new JSONObject(cadena);
			productBankIdentifier = jsonRecibido.getString("productBankIdentifier");
			JSONObject paging = jsonRecibido.getJSONObject("paging");
			channelId = jsonRecibido.getInt("ChannelId");
			if(!paging.toString().contains("null")) {
				pageSize = paging.getInt("pageSize");
				pageStartIndex = paging.getInt("pageStartIndex");
				if(String.valueOf(pageStartIndex).length() == 2) {
					pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,1));
				}else if(String.valueOf(pageStartIndex).length() == 3) {
					pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,2));
				}else if(String.valueOf(pageStartIndex).length() == 4) {
					pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,3));
				}else if(String.valueOf(pageStartIndex).length() == 5) {
					pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,4));
				}else if(String.valueOf(pageStartIndex).length() == 6) {
					pageStartIndex=Integer.parseInt(String.valueOf(pageStartIndex).substring(0,5));
				}
			}		
		} catch (Exception e) {
			Error.put("Error", "Error en parametros JSON:" + e.getMessage());
			return ResponseEntity.status(400).body(Error);
		}

		    int count = loanService.loanPayments(productBankIdentifier, pageStartIndex, pageSize,5).size();
			List<LoanPayment> ListPayment = loanService.loanPayments(productBankIdentifier, pageStartIndex,pageSize,channelId);
			//obtengo una lista de pagos para obtener el total
			opaDTO opa = util.opa(productBankIdentifier);
		    Auxiliares a = auxiliaresService.AuxiliarByOpa(opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar());
						
			JsonObject j = new JsonObject();
			j.put("Payments", ListPayment);		
			j.put("LoanPaymentsCount",count);
			return ResponseEntity.status(200).body(j);
		
	}
}
