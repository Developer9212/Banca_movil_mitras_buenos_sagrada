package com.fenoreste.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

import com.fenoreste.entity.Tablas;
import com.fenoreste.entity.productos_tercero_activacion;
import com.fenoreste.modelos.BackendOperationResultDTO;
import com.fenoreste.modelos.DocumentId;
import com.fenoreste.modelos.RequestDataOrdenPagoDTO;
import com.fenoreste.modelos.TransactionToOwnAccountsDTO;
import com.fenoreste.modelos.VaucherDTO;
import com.fenoreste.modelos.ogsDTO;
import com.fenoreste.modelos.opaDTO;
import com.fenoreste.service.IOtrosService;
import com.fenoreste.service.ITablasService;
import com.fenoreste.service.ITercerosActivacionService;
import com.fenoreste.service.TransactionsServiceSpring;
import com.fenoreste.util.HerramientasUtil;
import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
@CrossOrigin(origins = "")
@RequestMapping({ "/Transaction" })
public class TransactionController {
	
	@Autowired
	TransactionsServiceSpring transactionService;
	
	@Autowired
	ITablasService tbService;
	
	@Autowired
	IOtrosService otrosService;
	
	@Autowired
	ITercerosActivacionService activacionTercerosService;
	
	@Autowired
	private HerramientasUtil util;
	
	@PostMapping(value="/Insert",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> insertTransaction(@RequestBody String cadena) {		
		System.out.println("Cadena insert transaction:"+cadena);
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
		}
		
		
		
		    BackendOperationResultDTO backendOperationResult = new BackendOperationResultDTO();
	        backendOperationResult.setBackendCode("2");
	        backendOperationResult.setBackendMessage("Error en transaccion");
	        backendOperationResult.setBackendReference(null);
	        backendOperationResult.setIntegrationProperties("{}");
	        backendOperationResult.setError(true);
	        backendOperationResult.setTransactionIdenty("0");

	        JsonObject response_json_principal = new JsonObject();

	        /*================================================================
	                Obtenemos el request y lo pasamos a DTO
	        =================================================================*/
	        TransactionToOwnAccountsDTO dto = new TransactionToOwnAccountsDTO();
	        JsonObject response_json_secundario = new JsonObject();
	        JsonObject response_json_3 = new JsonObject();
	        String username = "";
	        String valueDate = "";
	        try {
	        	JSONObject jsonRecibido = new JSONObject(cadena);
	            JSONObject insertTransaction = jsonRecibido.getJSONObject("inserTransactionInput");
	            JSONObject destinationDocumentId = insertTransaction.getJSONObject("destinationDocumentId");
	            username = jsonRecibido.getString("UserName");
	            valueDate = insertTransaction.getString("valueDate");
	            DocumentId dto1 = new DocumentId();
	            dto1.setDocumentNumber(destinationDocumentId.getString("documentNumber"));
	            dto1.setDocumentType(destinationDocumentId.getString("documentType"));

	            DocumentId dto2 = new DocumentId();
	            dto1.setDocumentNumber(destinationDocumentId.getString("documentNumber"));
	            dto1.setDocumentType(destinationDocumentId.getString("documentType"));
	          
	            DocumentId dto3 = new DocumentId();
	            dto1.setDocumentNumber(destinationDocumentId.getString("documentNumber"));
	            dto1.setDocumentType(destinationDocumentId.getString("documentType"));

	            dto.setSubTransactionTypeId(Integer.parseInt(insertTransaction.getString("subTransactionTypeId")));
	            dto.setCurrencyId(insertTransaction.getString("currencyId"));
	            dto.setValueDate(insertTransaction.getString("valueDate"));
	            dto.setTransactionTypeId(insertTransaction.getInt("transactionTypeId"));
	            dto.setTransactionStatusId(insertTransaction.getInt("transactionStatusId"));
	            dto.setClientBankIdentifier(insertTransaction.getString("clientBankIdentifier"));
	            dto.setDebitProductBankIdentifier(insertTransaction.getString("debitProductBankIdentifier"));
	            dto.setDebitProductTypeId(insertTransaction.getInt("debitProductTypeId"));
	            dto.setDebitCurrencyId(insertTransaction.getString("debitCurrencyId"));
	            dto.setCreditProductBankIdentifier(insertTransaction.getString("creditProductBankIdentifier"));
	            dto.setCreditProductTypeId(insertTransaction.getInt("creditProductTypeId"));
	            dto.setCreditCurrencyId(insertTransaction.getString("creditCurrencyId"));
	            dto.setAmount(insertTransaction.getDouble("amount"));
	            dto.setNotifyTo(insertTransaction.getString("notifyTo"));
	            dto.setNotificationChannelId(insertTransaction.getInt("notificationChannelId"));
	            dto.setTransactionId(insertTransaction.getInt("transactionId"));
	            dto.setDestinationDocumentId(dto1);
	            dto.setDestinationName(insertTransaction.getString("destinationName"));
	            dto.setDestinationBank(insertTransaction.getString("destinationBank"));
	            dto.setDescription(insertTransaction.getString("description"));
	            dto.setBankRoutingNumber(insertTransaction.getString("bankRoutingNumber"));
	            dto.setSourceName(insertTransaction.getString("sourceName"));
	            dto.setSourceBank(insertTransaction.getString("sourceBank"));
	            dto.setSourceDocumentId(dto2);
	            dto.setRegulationAmountExceeded(insertTransaction.getBoolean("regulationAmountExceeded"));
	            dto.setSourceFunds(insertTransaction.getString("sourceFunds"));
	            dto.setDestinationFunds(insertTransaction.getString("destinationFunds"));
	            dto.setUserDocumentId(dto3);
	            dto.setTransactionCost(insertTransaction.getDouble("transactionCost"));
	            dto.setTransactionCostCurrencyId(insertTransaction.getString("transactionCostCurrencyId"));
	            dto.setExchangeRate(insertTransaction.getDouble("exchangeRate"));
	            dto.setCountryIntermediaryInstitution(insertTransaction.getString("countryIntermediaryInstitution"));
	            dto.setRouteNumberIntermediaryInstitution("{}");
	            dto.setIntegrationParameters("{}");
	        } catch (Exception e) {
	        	System.out.println("Error :"+e.getMessage());
	            backendOperationResult.setBackendCode("2");
	            backendOperationResult.setBackendMessage(e.getMessage());
	            response_json_3.put("integrationProperties", backendOperationResult.getIntegrationProperties());
	            response_json_3.put("backendCode", backendOperationResult.getBackendCode());
	            response_json_3.put("backendMessage", backendOperationResult.getBackendMessage());
	            response_json_3.put("backendReference", null);
	            response_json_3.put("isError", backendOperationResult.isError());
	            response_json_3.put("transactionIdenty", backendOperationResult.getTransactionIdenty());

	            response_json_secundario.put("backendOperationResult", response_json_3);
	            response_json_principal.put("InsertTransactionResult", response_json_secundario);
	            return ResponseEntity.status(400).body(response_json_principal);
	        }
	        
	        ogsDTO ogs = util.ogs(dto.getClientBankIdentifier()); 
			if(otrosService.validaciones_sopar_bloqueo(ogs.getIdorigen(),ogs.getIdgrupo(),ogs.getIdsocio())) {
				JsonObject json_bloqueo=new JsonObject();
				json_bloqueo.put("Error", "SOCIO BLOQUEADO");
				return ResponseEntity.status(400).body(json_bloqueo);
			}

	        /*======================================================================
	                Si el request que nos llego es el correcto procedemos
	          ======================================================================*/	        
	        try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	        	Date fechaTr = null;
	        	if(valueDate.replace(" ", "").contains("p.m")) {
	        		fechaTr = sdf.parse(valueDate);
	        		int hora = fechaTr.getHours()+12;
	        		fechaTr.setHours(hora);
	        		System.out.println("La fecha de la transaccion es :"+fechaTr);
	        	}else {
	        		fechaTr = sdf.parse(valueDate);
	        	}
	        	

	 	        TimeUnit time = TimeUnit.DAYS; 
	 	        TimeUnit timeHora = TimeUnit.HOURS; 
	 	        TimeUnit timeMinutos = TimeUnit.MINUTES;
	 	        TimeUnit timeSegundos = TimeUnit.SECONDS;
	 	       
	 	       productos_tercero_activacion terceroActivado = null;
	 	                	  
           	   long diff = 0; 
           	   long diffrence = 0;
	 	       long diffrenceHour = 0;
	 	       long diffrenceMinutos = 0;
	 	       long diffrenceSegundos = 0; 
	 	       Tablas tb_time = null;	 	        
	 	       
	        	opaDTO opa = util.opa(dto.getCreditProductBankIdentifier());
	            //Si subtransactionType es 1 y transactionType es 1: El tipo de transaccion es es entre mis cuentas
	            if (dto.getSubTransactionTypeId() == 1 && dto.getTransactionTypeId() == 1) {
	                backendOperationResult = transactionService.transferencias(dto, 1, null);
	            }
	            //Si subtransactionType es 2 y transactionType es 1: El tipo de transaccion es a terceros
	            if (dto.getSubTransactionTypeId() == 2 && dto.getTransactionTypeId() == 1) {
	               terceroActivado = activacionTercerosService.findTerceroByOpa(opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar(),username);
	               System.out.println("El tercero es:"+terceroActivado);
	               diff = fechaTr.getTime()-terceroActivado.getFecharegistro().getTime() ;
	               diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	  	 	       diffrenceHour = timeHora.convert(diff, TimeUnit.MILLISECONDS);
	  	 	       diffrenceMinutos = timeMinutos.convert(diff, TimeUnit.MILLISECONDS);
	  	 	       diffrenceSegundos = timeSegundos.convert(diff, TimeUnit.MILLISECONDS);
	  	 	       tb_time = tbService.findIdtablaAndIdelemento("bankingly_banca_movil","timer_tercero_transaccion");	
		 	       System.out.println("La tabla es:"+tb_time);
		 	       System.out.println("El total de dias del registro tercero es:"+diff+" el total de horas es:"+diffrenceHour+" el total de minutos es:"+diffrenceSegundos);
	            	if(diffrenceMinutos>Integer.parseInt(tb_time.getDato1())) {	            		
	            		backendOperationResult = transactionService.transferencias(dto, 2, null);
	            	}else {
	            		System.out.println("si");
	            		backendOperationResult.setBackendMessage("EL DESTINATARIO ESTARA ACTIVO EN:"+(Integer.parseInt(tb_time.getDato1())-diffrenceMinutos)+" Minutos");
	            	}
	                
	            }
	            //Si subtransactionType es 9 y transactionType es 6: El tipo de transaccion es es un pago a prestamos 
	            if (dto.getSubTransactionTypeId() == 9 && dto.getTransactionTypeId() == 6) {
	                backendOperationResult = transactionService.transferencias(dto, 3, null);
	            }
	            //Si es un pago a prestamo tercero
	            if (dto.getSubTransactionTypeId() == 10 && dto.getTransactionTypeId() == 6) {
	            	System.out.println("Si entro en pago a prestamo de tercero");
	                terceroActivado = activacionTercerosService.findTerceroByOpa(opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar(),username);
		            System.out.println("El tercero es:"+terceroActivado);
		            diff = fechaTr.getTime()-terceroActivado.getFecharegistro().getTime() ;
		            diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		  	 	    diffrenceHour = timeHora.convert(diff, TimeUnit.MILLISECONDS);
		  	 	    diffrenceMinutos = timeMinutos.convert(diff, TimeUnit.MILLISECONDS);
		  	 	    diffrenceSegundos = timeSegundos.convert(diff, TimeUnit.MILLISECONDS);
		  	 	    tb_time = tbService.findIdtablaAndIdelemento("bankingly_banca_movil","timer_tercero_transaccion");	
			 	    System.out.println("La tabla es:"+tb_time);
			 	    System.out.println("El total de dias del registro tercero es:"+diff+" el total de horas es:"+diffrenceHour+" el total de minutos es:"+diffrenceSegundos);
		            if(diffrenceMinutos>Integer.parseInt(tb_time.getDato1())) {	            		
		            		backendOperationResult = transactionService.transferencias(dto, 4, null);
		            }else {
		            	System.out.println("si");
		            	backendOperationResult.setBackendMessage("EL DESTINATARIO ESTARA ACTIVO EN:"+(Integer.parseInt(tb_time.getDato1())-diffrenceMinutos)+" Minutos");
		            }
	            }
	            //Si es una trasnferencia SPEI
	            if (dto.getSubTransactionTypeId() == 3 && dto.getTransactionTypeId() == 1) {
	                //Consumimos mis servicios de SPEI que tengo en otro proyecto(CSN0)
	                RequestDataOrdenPagoDTO ordenReque = new RequestDataOrdenPagoDTO();
	                ordenReque.setClienteClabe(dto.getDebitProductBankIdentifier());//Opa origen como cuenta clabe en el metodo spei se busca la clave
	                ordenReque.setConceptoPago(dto.getDescription());
	                ordenReque.setCuentaBeneficiario(dto.getCreditProductBankIdentifier());//La clabe del beneficiario
	                ordenReque.setInstitucionContraparte(dto.getDestinationBank());
	                ordenReque.setMonto(dto.getAmount());
	                ordenReque.setNombreBeneficiario(dto.getDestinationName());
	                ordenReque.setRfcCurpBeneficiario(dto.getDestinationDocumentId().getDocumentNumber());
	                ordenReque.setOrdernante(dto.getClientBankIdentifier());

	                backendOperationResult = transactionService.transferencias(dto, 5, ordenReque);
	            }

	            response_json_3.put("integrationProperties",null);
	            response_json_3.put("backendCode", backendOperationResult.getBackendCode());
	            response_json_3.put("backendMessage", backendOperationResult.getBackendMessage());
	            response_json_3.put("backendReference", null);
	            response_json_3.put("isError", backendOperationResult.isError());
	            response_json_3.put("transactionIdenty", backendOperationResult.getTransactionIdenty());

	            response_json_secundario.put("backendOperationResult", response_json_3);
	            response_json_principal.put("InsertTransactionResult", response_json_secundario);
	           

	        } catch (Exception e) {
	        	System.out.println("Error es :"+e.getMessage());
	            backendOperationResult.setBackendMessage(e.getMessage());
	            response_json_3.put("integrationProperties", null);
	            response_json_3.put("backendCode", backendOperationResult.getBackendCode());
	            response_json_3.put("backendMessage", backendOperationResult.getBackendMessage());
	            response_json_3.put("backendReference", null);
	            response_json_3.put("isError", backendOperationResult.isError());
	            response_json_3.put("transactionIdenty", backendOperationResult.getTransactionIdenty());

	            response_json_secundario.put("backendOperationResult", response_json_3);
	            response_json_principal.put("InsertTransactionResult", response_json_secundario);
	            
	            
	            return ResponseEntity.status(500).body(response_json_principal);
	        }
	        return ResponseEntity.status(200).body(response_json_principal);
	    }
	
	@PostMapping(value="/Voucher",consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	 public VaucherDTO voucher(@RequestBody String cadena) {
	        System.out.println("Entrando:"+cadena);
	        String idTransaccion = "";	       
	       JsonObject jsonMessage = new JsonObject();
	       VaucherDTO voucher = null;
	        try {
	        	JSONObject request = new JSONObject(cadena);
	        	idTransaccion = request.getString("transactionVoucherIdentifier");
	            voucher = transactionService.vaucher(idTransaccion);
	            System.out.println("bits:"+voucher.getProductBankStatementFile().toString());
	        } catch (Exception e){
	            //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonMessage).build();
	        }
	        return voucher;
	    }


	
}