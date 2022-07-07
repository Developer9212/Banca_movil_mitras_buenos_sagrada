package com.fenoreste.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenoreste.modelos.AccountDetailsDTO;
import com.fenoreste.modelos.AccountLastMovementDTO;
import com.fenoreste.service.AccountServiceSpring;
import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/Account" })
public class AccountController {

	@Autowired
	AccountServiceSpring accountService;

	@PostMapping(value = "/Details", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity details(@RequestBody String cadena) {
		String accountId = "";
		JsonObject Json_De_Error = new JsonObject();
		AccountDetailsDTO cuenta_dto=null;
		try {
		    JSONObject jsonRecibido = new JSONObject(cadena);
			accountId = jsonRecibido.getString("productBankIdentifier");
			cuenta_dto = accountService.getAccountDetails(accountId);
		} catch (Exception e) {
			System.out.println("Error al obtener detalles de cuenta:"+e.getMessage());
		}		
		return ResponseEntity.ok(cuenta_dto);
	}

	@PostMapping(value = "/Last5Movements", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> last5Movements(@RequestBody String cadena) {		
		JsonObject Json_De_Error = new JsonObject();
		JsonObject cuentasJson = null;
        try {
        	JSONObject jsonRecibido = new JSONObject(cadena);
    		String accountId = jsonRecibido.getString("productBankIdentifier");

    		List<AccountLastMovementDTO> last5Movements = new ArrayList<>();

    		last5Movements = accountService.getAccountLast5Movements(accountId);
    		cuentasJson = new JsonObject();
    		cuentasJson.put("Last5Movements", last5Movements);
		} catch (Exception e) {
			System.out.println("Error al obtener los ultimos 5 movimientos:"+e.getMessage());
		}
		
		return ResponseEntity.ok(cuentasJson);
	}
	
	
	@PostMapping(value = "/Movements", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> Movements(@RequestBody String cadena) {
        System.out.println("Cadena en movements:"+cadena);
		String ProductBankIdentifier = "";
        String DateFromFilter = null;
        String DateToFilter = null;
        int PageSize = 0;
        int PageStartIndex = 0;        
        JsonObject Error=new JsonObject();
        String orderBy="";
        JsonObject response = new JsonObject();
        int ChannelId = 0;
        try {
       
        JSONObject jsonRecibido = new JSONObject(cadena);
        ProductBankIdentifier = jsonRecibido.getString("productBankIdentifier");
        DateFromFilter = jsonRecibido.getString("dateFromFilter");
        DateToFilter = jsonRecibido.getString("dateToFilter");
        JSONObject json = jsonRecibido.getJSONObject("paging");
        ChannelId = jsonRecibido.getInt("ChannelId");
        if(ChannelId == 1) {
        	if(json.toString().toLowerCase().contains("null")) {
        		//Buscamos que dato es null
        		String cadenas[]=json.toString().split(",");
        		for(int i=0;i<cadenas.length;i++) {
        			String linea = cadenas[i];
        			if(!linea.toLowerCase().contains("null")) {
        				if(linea.contains("orderByField")) {
        					orderBy=json.getString("orderByField");
        				}else if(linea.contains("pageSize")) {
        					PageSize = json.getInt("pageSize");
        				}else if(linea.contains("pageSize")) {
        					PageStartIndex = json.getInt("pageStartIndex");
        				}
        			}
        		}
        	}else {
        		orderBy=json.getString("orderByField");
        		PageSize = json.getInt("pageSize");
        		PageStartIndex = json.getInt("pageStartIndex");
        		if(String.valueOf(PageStartIndex).length() == 2) {
					PageStartIndex=Integer.parseInt(String.valueOf(PageStartIndex).substring(0,1));
				}else if(String.valueOf(PageStartIndex).length() == 3) {
					PageStartIndex=Integer.parseInt(String.valueOf(PageStartIndex).substring(0,2));
				}else if(String.valueOf(PageStartIndex).length() == 4) {
					PageStartIndex=Integer.parseInt(String.valueOf(PageStartIndex).substring(0,3));
				}else if(String.valueOf(PageStartIndex).length() == 5) {
					PageStartIndex=Integer.parseInt(String.valueOf(PageStartIndex).substring(0,4));
				}else if(String.valueOf(PageStartIndex).length() == 6) {
					PageStartIndex=Integer.parseInt(String.valueOf(PageStartIndex).substring(0,5));
				}
        	 }
             
           }
       
        List<AccountLastMovementDTO> MiListaDTO=accountService.getAccountMovements(ProductBankIdentifier, DateFromFilter, DateToFilter, PageSize, PageStartIndex, orderBy,ChannelId);
        response.put("MovementsCount",accountService.contadorMovs(ProductBankIdentifier, DateFromFilter, DateToFilter));
        response.put("Movements", MiListaDTO);
        }catch (Exception e) {
	     System.out.println("Error el metodo para buscar movimientos:"+e.getMessage());
		}
        return ResponseEntity.ok(response);
	}
	
	
}