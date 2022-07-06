package com.fenoreste.entity;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "transferencias_bankingly")
@Cacheable(false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transferencias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_transfers_bankingly")
    @SequenceGenerator(name = "sec_transfers_bankingly", sequenceName = "sec_transfers_bankingly" , allocationSize = 1)
    private int idtransaction;
    private Integer subtransactiontypeid;
    private String currencyid;
    private String valuedate;
    private Integer transactiontypeid;
    private Integer transactionstatusid;
    private String clientbankidentifier;
    private String debitproductbankidentifier;
    private Integer debitproducttypeid;
    private String debitcurrencyid;
    private String creditproductbankidentifier;
    private Integer creditproducttypeid;
    private String creditcurrencyid;
    private Double amount;
    private String notifyto;
    private Integer notificationchannelid;
    private BigDecimal transactionid;
    private String destinationname;
    private String destinationbank;
    private String description;
    private String bankroutingnumber;
    private String sourcename;
    private String sourcebank;
    private boolean regulationamountexceeded;
    private String sourcefunds;
    private String destinationfunds;
    private Double transactioncost;
    private String transactioncostcurrencyid;
    private Double exchangerate;
    private Date fechaejecucion;
    private String destinationdocumentid_documentnumber;
    private String destinationdocumentid_documenttype;
    private String sourcedocumentid_documentnumber;
    private String sourcedocumentid_documenttype;
    private String userdocumentid_documentnumber;
    private String userdocumentid_documenttype;
   
    private static final long serialVersionUID = 1L;
}
