package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBankStatementDTO {
	
    private String productBankIdentifier;
    private String productBankStatementDate;
    private String ProductBankStatementId;
    private int ProductType;
    
}
