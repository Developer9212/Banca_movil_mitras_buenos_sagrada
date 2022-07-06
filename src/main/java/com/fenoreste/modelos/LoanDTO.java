package com.fenoreste.modelos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

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
public class LoanDTO {

    private String AccountBankIdentifier;
    private Double CurrentBalance;
    private Double CurrentRate;
    private int FeesDue;
    private FeesDueData FeesDueData;
    private int LoanStatusId;
    private LoanFee NextFee;
    private Double OriginalAmount;
    private int OverdueDays;
    private int PaidFees;
    private Double PayoffBalance;
    private Double PrepaymentAmount;
    private String ProductBankIdentifier;
    private int term;
    private boolean showPrincipalInformation;
    private List<LoanFee> loanFeesResult;
    private List<LoanRate> loanRateResult;
    private List<LoanPayment> loanPaymentsResult;
}
