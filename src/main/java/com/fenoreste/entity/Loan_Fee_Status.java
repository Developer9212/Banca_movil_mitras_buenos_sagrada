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
 * @author Elliot
 */
@Entity
@Table(name="loan_fee_statusB")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Loan_Fee_Status implements Serializable{
    
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="description")
    private String description;
    @Column(name="descripcion")
    private String descripcion;
    
    private static final long serialVersionUID = 1L;    
    
}
