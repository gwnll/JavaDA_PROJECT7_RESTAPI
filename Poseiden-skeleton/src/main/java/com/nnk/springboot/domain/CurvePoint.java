package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curvepoint_generator")
    @SequenceGenerator(name = "curvepoint_generator", sequenceName = "curvepoint_id_sequence")
    private int id;

    @Column(name = "curve_id")
    @NotNull(message = "must not be null")
    private int curveId;

    @Column(name = "as_of_date")
    private LocalDateTime asOfDate;

    @Column(name = "term")
    private double term;

    @Column(name = "value")
    private double value;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public CurvePoint() {

    }

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
