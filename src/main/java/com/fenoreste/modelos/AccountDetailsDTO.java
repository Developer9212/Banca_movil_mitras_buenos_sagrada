package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDTO {

	private String AccountBankIdentifier;
    private String AccountOfficerName;
    private Double AccountCountableBalance;
    private Double AccountAvailableBalance;
    private Double AccountBalance24Hrs;
    private Double AccountBalance48Hrs;
    private Double AccountBalance48MoreHrs;
    private Double MonthlyAverageBalance;
    private int PendingChecks;
    private int ChecksToReleaseToday;
    private int ChecksToReleaseTomorrow;
    private int CancelledChecks;
    private int CertifiedChecks;
    private int RejectedChecks;
    private int BlockedAmount;
    private double MovementsOfTheMonth;
    private int ChecksDrawn;
    private Double Overdrafts;
    private String ProductBranchName;
    private String ProductOwnerName;
    private Boolean ShowCurrentAccountChecksInformation;

}
