package com.n11.userreviewservice.model;

import com.n11.userreviewservice.model.base.BaseEntity;
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
@Table(name="addresses")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 20)
    @NotNull
    private String name;

    @Column(name = "city",length = 40)
    @NotNull
    private String city;

    @Column(name = "longitude")
    @NotNull
    private Double longitude;

    @Column(name = "latitude")
    @NotNull
    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

}
