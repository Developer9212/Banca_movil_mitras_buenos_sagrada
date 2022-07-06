package com.fenoreste.modelos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogFixedTermDepositDTO implements Serializable {

	private String Code;
	private Integer LanguajeId;
	private String Description;
    
	private static final long serialVersionUID = 1L;

}
