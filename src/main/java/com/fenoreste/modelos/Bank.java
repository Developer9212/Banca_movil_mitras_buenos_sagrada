package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

	 private Integer bankId;
	 private String description;
	 private String routingCode;
	 private String countryId;
	 private String headQuartersAddress;

	    
}
