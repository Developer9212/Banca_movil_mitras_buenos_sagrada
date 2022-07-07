package com.fenoreste.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenoreste.dao.AuxiliaresRepository;
import com.fenoreste.dao.PersonaRepository;
import com.fenoreste.dao.ProductosRepository;
import com.fenoreste.entity.Auxiliares;
import com.fenoreste.entity.Persona;
import com.fenoreste.entity.Productos;
import com.fenoreste.entity.TiposCuentaBankingly;
import com.fenoreste.modelos.CatalogFixedTermDepositDTO;
import com.fenoreste.modelos.DetallesInversionDTO;
import com.fenoreste.modelos.DocumentId;
import com.fenoreste.modelos.FixedTermDepositBeneficiaryDTO;


@Service
public class FixedTermDepositServiceImpl implements FixedTermDepositService {

	@Autowired
	AuxiliaresRepository auxiliaresDao;
	
	@Autowired
	IPersonaService personaService; 
	
	@Autowired
	ProductosRepository productosDao;
	
	@Autowired
    ISaiFuncionesService funcionesService;
	
	@Autowired
	ITiposCuentaBankinglyService tiposCuentaBankinglyService;
	
	@Override
	public DetallesInversionDTO fixedTermDeposit(Integer idorigenp, Integer idproducto, Integer idauxiliar) {
		Auxiliares auxiliar = auxiliaresDao.findAuxiliarByOpa(idorigenp,idproducto,idauxiliar);
		Persona persona = personaService.findByOGS(auxiliar.getIdorigen(),auxiliar.getIdgrupo(),auxiliar.getIdsocio());
		DetallesInversionDTO fixedTermDeposit = new DetallesInversionDTO();
		FixedTermDepositBeneficiaryDTO datosBeneficiario = new FixedTermDepositBeneficiaryDTO();
	    DocumentId documentId = new DocumentId();
	    CatalogFixedTermDepositDTO catalog = new CatalogFixedTermDepositDTO();
	    Productos producto = productosDao.findProductoById(auxiliar.getIdproducto());
	    
	    fixedTermDeposit.setCdpName(producto.getNombre());
	    fixedTermDeposit.setCdpNumber(producto.getIdproducto().toString());
	    fixedTermDeposit.setCurrentBalance(auxiliar.getSaldo().doubleValue());
	    fixedTermDeposit.setDueDate(intervalo(auxiliar.getFechaactivacion(),auxiliar.getPlazo()));
	    
	    //Para obtener el interes al dia actual corremos sai_auxiliar
	    String sai_auxiliar = funcionesService.sai_auxiliar(idorigenp, idproducto, idauxiliar);
	    String[] atributos_sai_auxiliar = sai_auxiliar.split("\\|");
	    List datos_sai_auxiliar = Arrays.asList(atributos_sai_auxiliar);
	    fixedTermDeposit.setInterestEarned(Double.parseDouble(datos_sai_auxiliar.get(1).toString()));
	    fixedTermDeposit.setInterestPaid(0.0);
	    fixedTermDeposit.setInterestPayingAccount(producto.getCuentaintord());
	    fixedTermDeposit.setOriginalAmount(auxiliar.getSaldo().doubleValue());
	    fixedTermDeposit.setProductBankIdentifier(String.format("%06d",auxiliar.getIdorigenp())+""+String.format("%05d",auxiliar.getIdproducto())+""+String.format("%08d",auxiliar.getIdauxiliar()));
        fixedTermDeposit.setRate(auxiliar.getTasaio().doubleValue());
        fixedTermDeposit.setStartDate(auxiliar.getFechaactivacion());
        fixedTermDeposit.setTerm(String.valueOf(auxiliar.getPlazo()));
        fixedTermDeposit.setDebitProductBankIdentifier(null);//PReguntar----------
        TiposCuentaBankingly tipo_cuenta_bankingly = tiposCuentaBankinglyService.findTipoCuentaById(auxiliar.getIdproducto());
        fixedTermDeposit.setFixedTermDepositType(tipo_cuenta_bankingly.getProductTypeId());
        
        catalog.setCode("3");//Preguntar
        catalog.setLanguajeId(null);
        catalog.setDescription("Ventanilla");
        
        fixedTermDeposit.setPaymentMethod(catalog);
        fixedTermDeposit.getInterestCreditProductBankIdentifier();//Preguntar a que cuenta se depositan los intereses generados
        fixedTermDeposit.getDepositCreditProductBankIdentifier();//Preguntar a donde va el pago de la inversion
        List<FixedTermDepositBeneficiaryDTO>lista_beneficiarios = new ArrayList<>();        
        
	    documentId.setDocumentNumber(persona.getCurp());
	    documentId.setDocumentType("3");
	    
	    datosBeneficiario.setDocumentId(documentId);
	    datosBeneficiario.setName(persona.getNombre()+" "+persona.getAppaterno()+" "+persona.getApmaterno());
	    datosBeneficiario.setPorcentage(auxiliar.getTasaio().intValue());
	    
	    lista_beneficiarios.add(datosBeneficiario);
	    
	    fixedTermDeposit.setFixedTermDepositBeneficiaries(lista_beneficiarios);
	    
		
		return fixedTermDeposit;
	}
	
	public String intervalo(Date fecha,int numeroDias) {		
	 	Calendar cal = Calendar.getInstance();
	 	cal.setTime(fecha);
	 	cal.add(Calendar.DAY_OF_YEAR, numeroDias);	 	
	 	SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
	 	String date = null;
	 	date = d.format(cal.getTime());
	 	
	return date;
}

}
