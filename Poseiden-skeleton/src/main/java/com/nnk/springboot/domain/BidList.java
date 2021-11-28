package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @Column(name = "bid_list_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bidlist_generator")
    @SequenceGenerator(name = "bidlist_generator", sequenceName = "bidlist_id_sequence")
    private int bidListId;

    @Column(name = "account")
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Column(name = "type")
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "bid_quantity")
    private double bidQuantity;

    @Column(name = "ask_quantity")
    private double askQuantity;

    @Column(name = "bid")
    private double bid;

    @Column(name = "ask")
    private double ask;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bid_list_date")
    private LocalDateTime bidListDate;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "book")
    private String book;

    @Column(name = "creation_name")
    private String creationName;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "revision_name")
    private String revisionName;

    @Column(name = "revision_date")
    private LocalDateTime revisionDate;

    @Column(name = "deal_name")
    private String dealName;

    @Column(name = "deal_type")
    private String dealType;

    @Column(name = "source_list_id")
    private String sourceListId;

    @Column(name = "side")
    private String side;

    public BidList() {

    }

    public BidList(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
