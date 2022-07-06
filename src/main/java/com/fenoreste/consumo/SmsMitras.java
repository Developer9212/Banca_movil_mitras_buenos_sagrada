package com.fenoreste.consumo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenoreste.entity.Persona;
import com.fenoreste.entity.Tablas;
import com.fenoreste.modelos.BackendOperationResultDTO;
import com.fenoreste.modelos.ogsDTO;
import com.fenoreste.service.IPersonaService;
import com.fenoreste.service.ITablasService;
import com.fenoreste.util.HerramientasUtil;


@Service
public class SmsMitras {
	
	@Autowired
	ITablasService tablasService;
	@Autowired
	HerramientasUtil util;
	@Autowired
	IPersonaService personaService;
	
	
	@Autowired 
	private ConsumosHTTPServiceImpl consumoService;
	
	
	public String sendSMSCuentaPropia(String numero,String productoOrigen,Double monto,String fechayHora,String autorizacionOrigen,String productoDestino) {		 
		Tablas tb_minimo_sms = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","monto_minimo_sms");
		String respuesta_servicio_sms  = "";
	     try {
	    	 if(monto >= Integer.parseInt(tb_minimo_sms.getDato1())) {
	    	 Tablas tb_datos_envio_sms = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","datos_envio_sms");	
	    	 JSONObject peticion = new JSONObject();	    
		     Tablas tb_contenido_cuenta_propia = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","sms_retiro_cuenta_propia");
		     String contenido_sms = tb_contenido_cuenta_propia.getDato2().replace("@productoOrigen@",productoOrigen)
						 													.replace("@monto@",String.valueOf(monto))
						 													.replace("@fechayHora@",fechayHora)
						 													.replace("@productoDestino@",productoDestino)
						 													.replace("@autorizacionOrigen@", autorizacionOrigen);
		     peticion.put("message",contenido_sms);
		     peticion.put("numbers",numero);
		     peticion.put("country_code","52");
			 respuesta_servicio_sms = consumoService.sendSmsMitras(tb_datos_envio_sms.getDato1(),tb_datos_envio_sms.getDato2(),peticion.toString());	
	      }
		} catch (Exception e) {
			JSONObject error = new JSONObject();
			try {
				error.put("Error", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return error.toString();
		}
							 													
		return respuesta_servicio_sms;
	}
	
	
	public String sendSMSCuentaTercero(String numero,String productoOrigen,Double monto,String fechayHora,String autorizacionOrigen,String productoDestino) {		 
		String respuesta_servicio_sms  = "";
		Tablas tb_minimo_sms = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","monto_minimo_sms");
	     try {
	    	 if(monto >= Integer.parseInt(tb_minimo_sms.getDato1())) {
	    	 Tablas tb_datos_envio_sms = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","datos_envio_sms");	
	    	 JSONObject peticion = new JSONObject();	    
		     Tablas tb_contenido_cuenta_propia = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","sms_retiro_cuenta_tercero");
		     String contenido_sms = tb_contenido_cuenta_propia.getDato2().replace("@productoOrigen@",productoOrigen)
						 													.replace("@monto@",String.valueOf(monto))
						 													.replace("@fechayHora@",fechayHora)
						 													.replace("@productoDestino@",productoDestino)
						 													.replace("@autorizacionOrigen@", autorizacionOrigen);
		     peticion.put("message",contenido_sms);
		     peticion.put("numbers",numero);
		     peticion.put("country_code","52");
			 respuesta_servicio_sms = consumoService.sendSmsMitras(tb_datos_envio_sms.getDato1(),tb_datos_envio_sms.getDato2(),peticion.toString());	
	      }
		} catch (Exception e) {
			JSONObject error = new JSONObject();
			try {
				error.put("Error", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}return error.toString();
		}
							 													
	return respuesta_servicio_sms;
	}
	
	public BackendOperationResultDTO sendSmsToken(String clientBankIdentifier,String numero,String token){
		BackendOperationResultDTO dto = new BackendOperationResultDTO();
        dto.setError(true);
        dto.setBackendMessage("Error");
        dto.setBackendReference(null);
        dto.setBackendCode("2");
        dto.setIntegrationProperties("");
        dto.setTransactionIdenty(null);
        
        try {
            //Busco a la persona para comparar que los numeros sea el mismo
            ogsDTO ogs = util.ogs(clientBankIdentifier);
            Persona p = personaService.findByOGS(ogs.getIdorigen(),ogs.getIdgrupo(),ogs.getIdsocio());
            if(p.getCelular().trim().equals(numero.trim())){  
            	 Tablas tb_datos_envio_sms = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","datos_envio_sms");	
    	    	 JSONObject peticion = new JSONObject();	    
    		     Tablas tb_contenido_sms_token = tablasService.findIdtablaAndIdelemento("bankingly_banca_movil","sms_token");
    		     String contenido_sms = tb_contenido_sms_token.getDato2().replace("@token@",token);
    		     peticion.put("message",contenido_sms);
    		     peticion.put("numbers",numero);
    		     peticion.put("country_code","52");
    			 String respuesta_servicio_sms = consumoService.sendSmsMitras(tb_datos_envio_sms.getDato1(),tb_datos_envio_sms.getDato2(),peticion.toString());
    			 dto.setError(false);///Si falla volver a metodo normal sin usar lombok
                 dto.setBackendMessage("Token enviado");
                 dto.setBackendCode("1"); 
               
            }else{
                dto.setBackendMessage("El numero no coincide con nuestros registros");
            }
            
        } catch (Exception e) {
            System.out.println("Error al enviar sms token : "+e.getMessage());
        }
        return dto;
    }
	

}
