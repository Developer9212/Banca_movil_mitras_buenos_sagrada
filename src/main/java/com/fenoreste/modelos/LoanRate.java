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
public class LoanRate {

    private int id;
    private String initialDate;
    private Double rate;
    
}
