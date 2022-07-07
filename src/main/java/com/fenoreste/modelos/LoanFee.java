/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelos;

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
public class LoanFee {

	private Double capitalBalance;
	private Integer feeNumber;
	private Double principalAmount;
	private String dueDate;
	private Double interestAmount;
	private Double overdueAmount;
	private Integer feeStatusId;
	private Double othersAmount;
	private Double totalAmount;

}
