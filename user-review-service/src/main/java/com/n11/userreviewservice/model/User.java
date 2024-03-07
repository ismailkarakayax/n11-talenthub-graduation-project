package com.n11.userreviewservice.model;

import com.n11.userreviewservice.model.base.BaseEntity;
import com.n11.userreviewservice.model.enums.Gender;
import com.n11.userreviewservice.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
    @Column(name="name",length = 20)
    @NotNull
    private String name;
    @Column(name="surname",length = 100)
    @NotNull
    private String surname;
    @Column(name="birth_date")
    @NotNull
    private LocalDate birthDate;
    @Column(name="email")
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    @NotNull
    private Status status;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Review> reviewList;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Address> addressList;



}
