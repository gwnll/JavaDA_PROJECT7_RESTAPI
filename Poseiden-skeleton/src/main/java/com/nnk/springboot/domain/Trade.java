package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trade_generator")
    @SequenceGenerator(name = "trade_generator", sequenceName = "trade_id_sequence")
    private int id;

    @Column(name = "account")
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Column(name = "type")
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "buy_quantity")
    @NotNull(message = "Buy quantity is mandatory")
    private double buyQuantity;

    @Column(name = "sell_quantity")
    private double sellQuantity;

    @Column(name = "buy_price")
    private double buyPrice;

    @Column(name = "sell_price")
    private double sellPrice;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "trade_date")
    private LocalDateTime tradeDate;

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

    public Trade() {

    }

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

}
