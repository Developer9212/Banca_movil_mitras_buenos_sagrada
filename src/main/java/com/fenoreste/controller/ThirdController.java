package com.fenoreste.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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

import com.fenoreste.entity.Origenes;
import com.fenoreste.entity.productos_tercero_activacion;
import com.fenoreste.modelos.BackendOperationResultDTO;
import com.fenoreste.modelos.Bank;
import com.fenoreste.modelos.ThirdPartyProductDTO;
import com.fenoreste.modelos.UserDocumentIdDTO;
import com.fenoreste.modelos.opaDTO;
import com.fenoreste.service.IOrigenesService;
import com.fenoreste.service.IOtrosService;
import com.fenoreste.service.ITercerosActivacionService;
import com.fenoreste.service.TerceroServiceSpring;
import com.fenoreste.util.HerramientasUtil;
import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/Third" })
public class ThirdController {

	@Autowired
	TerceroServiceSpring tercerosService;
	
	@Autowired
	IOtrosService otrosService;
	@Autowired
	ITercerosActivacionService activacionTercerosService;
	
	@Autowired
	IOrigenesService origenesService;
	
	@Autowired
	private HerramientasUtil util;
	
	@PostMapping(value = "/Party/Account/ProductOwnerAndCurrency", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity altaTerceros(@RequestBody String cadena) {	
		System.out.println("Entro a productOwnerAndCurrency:"+cadena);
		if(!otrosService.servicios_activos()) {
			JsonObject json_horario=new JsonObject();
			json_horario.put("Error", "VERIFIQUE SU HORARIO DE ACTIVIDAD FECHA,HORA O CONTACTE A SU PROVEEEDOR");
			return ResponseEntity.status(400).body(json_horario);
		}

		JsonObject third = new JsonObject();
		
		try {
			JSONObject jsonRequest = new JSONObject(cadena);
			String productNumber_ = "";
			Integer productTypeId_ = 0;
			Integer thirdPartyProductType_ = 0;
			String username = "";
			// validamos que nuestro request este bien formado
			productNumber_ = jsonRequest.getString("productNumber");
			productTypeId_ = jsonRequest.getInt("productTypeId");
			username = jsonRequest.getString("UserName");
			// Otengo el objeto de productOwnerDocumentId
			JSONObject productOwnerDocumentId = jsonRequest.getJSONObject("productOwnerDocumentId");
			String documentNumber_ = productOwnerDocumentId.getString("documentNumber");
			String documentType_ = productOwnerDocumentId.getString("documentType");
			thirdPartyProductType_ = jsonRequest.getInt("thirdPartyProductType");
		

		UserDocumentIdDTO documento = new UserDocumentIdDTO();
		ThirdPartyProductDTO dto = null;
		if(verificaRegistroTercero(productNumber_,username)) {
			dto = tercerosService.cosultaProductosTerceros(productNumber_.trim(), productTypeId_, documento,thirdPartyProductType_);		  
		}	
		third.put("productOwnerAndCurrency", dto);
		} catch (Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		return ResponseEntity.status(200).body(third);

	}
	
	public boolean verificaRegistroTercero(String opaTercero,String usuario) {
		boolean bandera = false;
		try {
			Date hoy = new Date();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String hy= sdf.format(hoy);
			LocalDateTime localDate = LocalDateTime.parse(hy, dtf);
			Timestamp fecha_transferencia = Timestamp.valueOf(localDate);	
			//Primero busco el tercero
			opaDTO opa = util.opa(opaTercero.trim());
			productos_tercero_activacion tercero = activacionTercerosService.findTerceroByOpa(opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar(),usuario);
			if(tercero != null) {
				//Si el tercero existe realizo un update a la hora de activacion
				activacionTercerosService.modificar(fecha_transferencia, opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar(),usuario);
			}else {
				activacionTercerosService.save(opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar(), fecha_transferencia, usuario);
			}
			bandera = true;
		} catch (Exception e) {
			System.out.println("Error al guardar registros para el tercero:"+opaTercero+" y el error podria ser:"+e.getMessage());		
		}		
		return bandera;
	}

}
