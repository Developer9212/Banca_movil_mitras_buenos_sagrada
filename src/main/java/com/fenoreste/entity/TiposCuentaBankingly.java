package com.fenoreste.entity;

import java.io.Serializable;

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

@Entity
@Table(name = "tipos_cuenta_bankingly")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TiposCuentaBankingly implements Serializable{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idproducto;    
    @Column(name="producttypename")    
    private String productTypeName;
    @Column(name="descripcion")    
    private String descripcion;
    @Column(name="producttypeid")
    private Integer productTypeId;
    
    private static final long serialVersionUID = 1L;
    
}
