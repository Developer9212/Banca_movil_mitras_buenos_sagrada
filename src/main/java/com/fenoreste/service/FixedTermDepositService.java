package com.fenoreste.service;

import org.springframework.stereotype.Service;

import com.fenoreste.modelos.DetallesInversionDTO;

@Service
public interface FixedTermDepositService {
	
	 public DetallesInversionDTO fixedTermDeposit(Integer idorigenp,Integer idproducto,Integer idauxiliar);
	 
	 
}
