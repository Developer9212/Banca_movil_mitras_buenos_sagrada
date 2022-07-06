package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDataOrdenPagoDTO {

	private String clienteClabe;
    private Double monto;
    private String institucionContraparte;
    private String nombreBeneficiario;
    private String rfcCurpBeneficiario;
    private String conceptoPago;
    private String cuentaBeneficiario;
    private String ordernante;
    
}
