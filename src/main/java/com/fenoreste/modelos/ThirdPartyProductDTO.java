package com.fenoreste.modelos;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyProductDTO {

    private ArrayList<String> clientBankIdentifiers;
    private String thirdPartyProductNumber;
    private String thirdPartyProductBankIdentifier;
    private String alias;
    private String currencyId;
    private Integer transactionSubType;
    private Integer thirdPartyProductType;
    private Integer productType;
    private String ownerName;
    private String  ownerCountryId;
    private String ownerEmail;
    private String ownerCity;
    private String ownerAddress;
    private UserDocumentIdDTO ownerDocumentId;
    private String ownerPhoneNumber;
    private Bank bank;
    private Bank correspondentBank;
    private UserDocumentIdDTO userDocumentId;
    
}
