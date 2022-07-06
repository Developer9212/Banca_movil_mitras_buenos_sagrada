package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductosDTO {

	private String clientBankIdentifier;
    private String productBankIdentifier;
    private String productNumber;
    private int productStatusId;
    private String productTypeId;
    private String productAlias;
    private String canTransact;
    private String currencyId;

     
}
