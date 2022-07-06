package com.fenoreste.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos_terceros_activacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class productos_tercero_activacion implements Serializable {

	@Id
	@Column(name = "idorigenpt")
	private Integer idorigenpt;
	@Column(name = "idproductot")
	private Integer idproductot;
	@Column(name = "idauxiliart")
	private Integer idauxiliart;
	@Column(name = "fecharegistro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecharegistro;
	@Column(name = "usuariobanca")
	private String usuariobanca;

	private static final long serialVersionUID = 1L;
	

}
