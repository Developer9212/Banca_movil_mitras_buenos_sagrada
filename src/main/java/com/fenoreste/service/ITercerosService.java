package com.fenoreste.service;

import org.springframework.stereotype.Service;

import com.fenoreste.entity.ProductoTercero;
import com.fenoreste.modelos.BackendOperationResultDTO;

@Service
public interface ITercerosService {

	public ProductoTercero findTerceroByCuenta(String cuentaTercero); 
	
	public void guardar(ProductoTercero terero);
	
	public BackendOperationResultDTO sendSmsToken(String clientBankIdentifier, String numero,String token);
}
