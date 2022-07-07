package com.fenoreste.modelos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductConsolidatedPositionDTO {

	   private String clientBankIdentifier;
	      private String productBankIdentifier;
	      private String productTypeId;
	      private String productAlias;
	      private String productNumber;
	      private Integer localCurrencyId;
	      private Double localBalance;
	      private Integer internationalCurrencyId;
	      private Double internationalBalance;
	      private Double rate;
	      private String expirationDate;
	      private Integer paidFees;
	      private Integer term;
	      private String nextFeeDueDate;
	      private String productOwnerName;
	      private String productBranchName;
	      private Integer canTransact;
	      private Integer subsidiaryId;
	      private String subsidiaryName;
	      private Integer backendId;
	    
}
