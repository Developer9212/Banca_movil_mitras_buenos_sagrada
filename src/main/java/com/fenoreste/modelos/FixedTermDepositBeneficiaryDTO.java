package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixedTermDepositBeneficiaryDTO{
	
	private DocumentId DocumentId;
	private String Name;
	private Integer Porcentage;
	
	
	private static final long serialVersionUID = 1L;
}
