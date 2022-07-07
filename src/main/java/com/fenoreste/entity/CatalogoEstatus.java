/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="catalogo_status_bankingly")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CatalogoEstatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusdb")
    private Integer statusdb;
    @Column(name="productstatusid")
    private Integer productstatusid;
    @Column(name="description")
    private String description;
    @Column(name="descripcion")
    private String descripcion;

    
}
