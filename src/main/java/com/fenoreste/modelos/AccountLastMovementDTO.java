package com.fenoreste.modelos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountLastMovementDTO {
	
	private int MovementId;
    private String AccountBankIdentifier;
    private String MovementDate;
    private String Description;   
    private Double Amount;  
    @JsonSetter("isDebit")//propiedad para que respete el nombre en el response
    private boolean isDebit;
    private Double Balance;
    private int MovementTypeId;
    private String TypeDescription;
    private String CheckId;
    private String VoucherId;
    
}
