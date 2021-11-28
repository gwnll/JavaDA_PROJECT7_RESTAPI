package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_generator")
    @SequenceGenerator(name = "rating_generator", sequenceName = "rating_id_sequence")
    private int id;

    @Column(name = "moodys_rating")
    @NotBlank(message = "MoodysRating is mandatory")
    private String moodysRating;

    @Column(name = "sand_p_rating")
    @NotBlank(message = "SandPRating is mandatory")
    private String sandPRating;

    @Column(name = "fitch_rating")
    @NotBlank(message = "FitchRating is mandatory")
    private String fitchRating;

    @Column(name = "order_number")
    private int orderNumber;

    public Rating() {

    }

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}
