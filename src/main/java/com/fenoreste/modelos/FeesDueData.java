package com.fenoreste.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elliot
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeesDueData {

    private Double FeesDueInterestAmount;
    private Double FeesDueOthersAmount;
    private Double FeesDueOverdueAmount;
    private Double FeesDuePrincipalAmount;
    private Double FeesDueTotalAmount;

    

}

