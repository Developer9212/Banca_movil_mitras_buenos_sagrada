package com.fenoreste.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuxiliaresPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "idusuario")
    private Integer idusuario;
    @Column(name = "sesion")
    private String sesion;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "idorigenp", nullable = false)
    private Integer idorigenp;
    @Column(name = "idproducto",nullable = false)
    private Integer idproducto;
    @Column(name = "idauxiliar" , nullable = false)
    private Integer idauxiliar;
    

    public AuxiliaresPK() {
    }
    
   
}
