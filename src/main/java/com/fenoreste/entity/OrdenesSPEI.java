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
@Table(name = "ordenes_spei")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrdenesSPEI implements Serializable {

    @Id
    @Column(name = "idorden")
    private Integer idorden;
    @Column(name = "institucioncontraparte")
    private Integer institucioncontraparte;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "claverastreo")
    private String claverastreo;
    @Column(name = "institucionoperante")
    private String institucionoperante;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "tipopago")
    private Integer tipopago;
    @Column(name = "tipocuentaordenante")
    private Integer tipocuentaordenante;
    @Column(name = "nombreordenante")
    private String nombreordenante;
    @Column(name = "cuentaordenante")
    private String cuentaordenante;
    @Column(name = "rfccurpordenante")
    private String rfccurpordenante;
    @Column(name = "tipocuentabeneficiario")
    private Integer tipocuentabeneficiario;
    @Column(name = "nombrebeneficiario")
    private String nombrebeneficiario;
    @Column(name = "cuentabeneficiario")
    private String cuentabeneficiario;
    @Column(name = "rfccurpbeneficiario")
    private String rfccurpbeneficiario;
    @Column(name = "conceptopago")
    private String conceptopago;
    @Column(name = "referencianumerica")
    private Integer referencianumerica;
    @Column(name = "estatus1")
    private String estatus1;
    @Column(name = "estatus2")
    private String estatus2;
    @Column(name = "estatus3")
    private String estatus3;
    @Column(name = "estatusfinal")
    private String estatusfinal;
    
    private static final long serialVersionUID = 1L;    
}
