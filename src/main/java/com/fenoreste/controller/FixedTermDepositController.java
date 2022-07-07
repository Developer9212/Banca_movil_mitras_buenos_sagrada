package com.fenoreste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenoreste.modelos.DetallesInversionDTO;
import com.fenoreste.modelos.opaDTO;
import com.fenoreste.service.FixedTermDepositServiceImpl;
import com.fenoreste.util.HerramientasUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/fixedTermDeposit" })
public class FixedTermDepositController {
   
	@Autowired
	FixedTermDepositServiceImpl fixedTermDepositImpl;
	@Autowired
	HerramientasUtil utileria;
	
	@GetMapping(value = "/details/{productBankIdentifier}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public DetallesInversionDTO fixedTermController(@PathVariable String productBankIdentifier) {
		opaDTO opa = utileria.opa(productBankIdentifier);
		return fixedTermDepositImpl.fixedTermDeposit(opa.getIdorigenp(),opa.getIdproducto(),opa.getIdauxiliar());
		
	}
	 
}
