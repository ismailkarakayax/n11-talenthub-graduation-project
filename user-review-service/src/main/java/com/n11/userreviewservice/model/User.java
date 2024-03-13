package com.n11.userreviewservice.model;

import com.n11.userreviewservice.model.base.BaseEntity;
import com.n11.userreviewservice.model.enums.Gender;
import com.n11.userreviewservice.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",length = 100)
    @NotNull
    private String name;
    @Column(name="surname",length = 100)
    @NotNull
    private String surname;
    @Column(name="birth_date")
    @NotNull
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
    @Column(name="email")
    @Email
    private String email;
    @Column(name = "latitude")
    @NotNull(message = "Latitude cannot be null")
    @DecimalMin(value = "-90", inclusive = true, message = "Latitude must be at least -90")
    @DecimalMax(value = "90", inclusive = true, message = "Latitude must be at most 90")
    private Double latitude;
    @Column(name = "longitude")
    @NotNull(message = "Longitude cannot be null")
    @DecimalMin(value = "-180", inclusive = true, message = "Longitude must be at least -180")
    @DecimalMax(value = "180", inclusive = true, message = "Longitude must be at most 180")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Review> reviewList;


}
