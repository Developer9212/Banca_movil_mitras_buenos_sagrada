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

@Entity
@Table(name = "banca_movil_usuarios_bankingly")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuariosBancaMovil implements Serializable {

	    @Id
	    @Column(name="idorigen")
	    private Integer idorigen;
	    @Column(name="idgrupo")
	    private Integer idgrupo;
	    @Column(name="idsocio")
	    private Integer idsocio; 
	    @Column(name="alias_usuario")
	    private String alias_usuario;
	    @Column(name="idorigenp")
	    private int idorigenp;
	    @Column(name = "idproducto")
	    private int idproducto;
	    @Column(name="idauxiliar")
	    private int idauxiliar;
	    @Column(name="estatus")
	    private boolean estatus;
        
	    private static final long serialVersionUID = 1L;
}
