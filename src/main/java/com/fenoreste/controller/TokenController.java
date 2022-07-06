package com.fenoreste.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenoreste.consumo.ConsumosHTTPServiceImpl;
import com.fenoreste.consumo.SmsMitras;
import com.fenoreste.modelos.BackendOperationResultDTO;
import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/Authentication" })
public class TokenController {
	
	@Autowired
	private SmsMitras serviciosSmsMitras;
	
	@PostMapping(value = "/SendSmsToken", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity sendSmsToken(@RequestBody String cadena) {
		    String numero = "";
	        String token = "";
	        String clientBankIdentifier = "";
	        JsonObject jsonResponse = new JsonObject();
	        try {
	           	JSONObject request = new JSONObject(cadena);	  	       
	            JSONObject input = request.getJSONObject("input");
	            JSONArray lista = input.getJSONArray("clientBankIdentifiers");
	            clientBankIdentifier = lista.getJSONObject(0).getString("value");
	            numero = input.getString("phoneNumber");
	            token = input.getString("token");
	        } catch (Exception e) {
	            return ResponseEntity.status(400).body(e.getMessage());
	        }

	        try {	            
	            BackendOperationResultDTO resultado = serviciosSmsMitras.sendSmsToken(clientBankIdentifier, numero, token);
	            JsonObject backendOperationResult = new JsonObject();
	            backendOperationResult.put("backendOperationResult",resultado);
	            jsonResponse.put("response", backendOperationResult);
	        } catch (Exception e) {
	            System.out.println("Error al enviar token en servicio:"+e.getMessage());
	        }

	        return ResponseEntity.status(200).body(jsonResponse);
	    }

}
