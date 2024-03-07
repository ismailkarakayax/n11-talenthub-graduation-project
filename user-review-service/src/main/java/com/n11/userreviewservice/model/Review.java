package com.n11.userreviewservice.model;

import com.n11.userreviewservice.model.base.BaseEntity;
import com.n11.userreviewservice.model.enums.Rate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "restourant_id")
    @NotNull
    private Long restaurantId;

    @Column(name="rate")
    @NotNull
    private Rate rate;

    @Column(name = "comment",length = 150)
    private String comment;



}
