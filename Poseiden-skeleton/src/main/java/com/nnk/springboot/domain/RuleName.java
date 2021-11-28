package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rulename_generator")
    @SequenceGenerator(name = "rulename_generator", sequenceName = "rulename_id_sequence")
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "json")
    @NotBlank(message = "json is mandatory")
    private String json;

    @Column(name = "template")
    @NotBlank(message = "Template is mandatory")
    private String template;

    @Column(name = "sql_str")
    @NotBlank(message = "sql is mandatory")
    private String sqlStr;

    @Column(name = "sql_part")
    @NotBlank(message = "sql part is mandatory")
    private String sqlPart;

    public RuleName() {

    }

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

}
