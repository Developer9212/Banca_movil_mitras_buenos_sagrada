package com.fenoreste.modelos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuxiliaresdDTO {
	    private Integer idorigenp;
	    private Integer idproducto;
	    private Integer idauxiliar;
	    private Short cargoabono;
	    private BigDecimal monto;
	    private BigDecimal montoio;
	    private BigDecimal montoim;
	    private BigDecimal montoiva;
	    private Integer idorigenc;
	    private String periodo;
	    private Short idtipo;
	    private Integer idpoliza;
	    private Short tipomov;
	    private BigDecimal saldoec;
	    private Integer transaccion;
	    private BigDecimal montoivaim;
	    private BigDecimal efectivo;
	    private int diasvencidos;
	    private BigDecimal montovencido;
	    private Integer ticket;
	    private BigDecimal montoidnc;
	    private BigDecimal montoieco;
	    private BigDecimal montoidncm;
	    private BigDecimal montoiecom;

	  
	    
}
