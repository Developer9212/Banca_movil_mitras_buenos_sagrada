/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelos;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Elliot
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionToOwnAccountsDTO {

	private int subTransactionTypeId;
	private String currencyId;
	private String valueDate;
	private int transactionTypeId;
	private int transactionStatusId;
	private String clientBankIdentifier;
	private String debitProductBankIdentifier;
	private int debitProductTypeId;
	private String debitCurrencyId;
	private String creditProductBankIdentifier;
	private int creditProductTypeId;
	private String creditCurrencyId;
	private Double amount;
	private String notifyTo;
	private int notificationChannelId;
	private int transactionId;
	private DocumentId destinationDocumentId;
	private String destinationName;
	private String destinationBank;
	private String description;
	private String bankRoutingNumber;
	private String sourceName;
	private String sourceBank;
	private DocumentId sourceDocumentId;
	private boolean regulationAmountExceeded;
	private String sourceFunds;
	private String destinationFunds;
	private DocumentId userDocumentId;
	private Double transactionCost;
	private String transactionCostCurrencyId;
	private Double exchangeRate;
	private String countryIntermediaryInstitution;
	private String intermediaryInstitution;
	private String routeNumberIntermediaryInstitution;
	private String integrationParameters;

}
