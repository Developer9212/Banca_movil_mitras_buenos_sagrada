package com.fenoreste.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallesInversionDTO implements Serializable{
	
	private String                         CdpName                             ;
	private String                         CdpNumber                           ;
	private Double                         CurrentBalance                      ;
	private String                         DueDate                             ;
	private Double                         InterestEarned                      ;
	private Double                         InterestPaid                        ;
	private String                         InterestPayingAccount               ;
	private Double                         OriginalAmount                      ;
	private String                         ProductBankIdentifier               ;
	private Double                         Rate                                ;
	private Date                           RenewalDate                         ;
	private Date                           StartDate                           ;
	private String                         Term                                ;
	private String                         DebitProductBankIdentifier          ;
	private Integer                        FixedTermDepositType                ;
	private CatalogFixedTermDepositDTO     PaymentMethod                       ;
	private Double                         TotalInterestAmount                 ;
	private CatalogFixedTermDepositDTO     RenewalType                         ;
	private String                         InterestCreditProductBankIdentifier ;
	private String                         DepositCreditProductBankIdentifier  ;
	private List<FixedTermDepositBeneficiaryDTO> FixedTermDepositBeneficiaries ;

	private static final long serialVersionUID = 1L;

	
}
