/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author wilmer
 */
@Entity
@Table(name = "productos_terceros_bankingly")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoTercero implements Serializable {

	@Id
	@Column(name = "clientbankidentifiers")
	private String clientBankIdentifiers;
	@Column(name = "thirdpartyproductnumber")
	private String thirdPartyProductNumber;
	@Column(name = "thirdpartyproductbankidentifier")
	private String thirdPartyProductBankIdentifier;
	@Column(name = "alias")
	private String alias;
	@Column(name = "currencyid")
	private String currencyId;
	@Column(name = "transactionsubtype")
	private Integer transactionSubType;
	@Column(name = "thirdpartyproducttype")
	private Integer thirdPartyProductType;
	@Column(name = "producttype")
	private Integer productType;
	@Column(name = "ownername")
	private String ownerName;
	@Column(name = "ownercountryid")
	private String ownerCountryId;
	@Column(name = "owneremail")
	private String ownerEmail;
	@Column(name = "ownercity")
	private String ownerCity;
	@Column(name = "owneraddress")
	private String ownerAddress;
	@Column(name = "ownerdocumentid_integrationproperties ")
	private String ownerDocumentId_integrationProperties;
	@Column(name = "ownerdocumentid_documentnumber")
	private String ownerDocumentId_documentNumber;
	@Column(name = "ownerdocumentid_documenttype")
	private String ownerDocumentId_documentType;
	@Column(name = "ownerphonenumber")
	private String ownerPhoneNumber;
	@Column(name = "bank_bankid")
	private Integer bank_bankId;
	@Column(name = "bank_description")
	private String bank_description;
	@Column(name = "bank_routingcode")
	private String bank_routingCode;
	@Column(name = "bank_countryid")
	private String bank_countryId;
	@Column(name = "bank_headquartersaddress")
	private String bank_headQuartersAddress;
	@Column(name = "correspondentbank_bankid")
	private Integer correspondentBank_bankId;
	@Column(name = "correspondentbank_description")
	private String correspondentBank_description;
	@Column(name = "correspondentbank_routingcode")
	private String correspondentBank_routingCode;
	@Column(name = "correspondentbank_countryid")
	private String correspondentBank_countryId;
	@Column(name = "correspondentbank_headquartersaddress ")
	private String correspondentBank_headQuartersAddress;
	@Column(name = "userdocumentid_integrationproperties")
	private String userDocumentId_integrationProperties;
	@Column(name = "userdocumentid_documentnumber")
	private String userDocumentId_documentNumber;
	@Column(name = "userdocumentid_documenttype")
	private String userDocumentId_documentType;
	
	private static final long serialVersionUID = 1L;
}
