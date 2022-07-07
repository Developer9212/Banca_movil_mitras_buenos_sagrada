package com.fenoreste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.TercerosRepository;
import com.fenoreste.entity.ProductoTercero;
import com.fenoreste.modelos.BackendOperationResultDTO;

@Service
public class TerceroServiceImpl implements ITercerosService {

	@Autowired
	TercerosRepository tercerosDao;
	@Override
	public ProductoTercero findTerceroByCuenta(String cuentaTercero) {
		// TODO Auto-generated method stub
		return tercerosDao.findTerceroByCuenta(cuentaTercero);
	}
	@Override
	public void guardar(ProductoTercero tercero) {
		tercerosDao.saveAndFlush(tercero);		
	}
	@Override
	public BackendOperationResultDTO sendSmsToken(String clientBankIdentifier, String numero, String token) {
		
		return null;
	}
	
	

}
