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

import org.springframework.cache.annotation.CacheEvict;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author Elliot
 */
@Entity
@Table(name = "tablas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tablas implements Serializable {    
	
	@Id
    @Column(name="idtabla")
    private String idtabla;
    @Column(name="idelemento")
    private String idelemento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dato1")
    private String dato1;
    @Column(name = "dato2")
    private String dato2;
    @Column(name = "dato3")
    private String dato3;
    @Column(name = "dato4")
    private String dato4;
    @Column(name = "dato5")
    private String dato5;
    @Column(name = "tipo")
    private short tipo;

	private static final long serialVersionUID = 1L;

}
