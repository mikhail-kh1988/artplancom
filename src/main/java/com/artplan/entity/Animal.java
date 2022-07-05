package com.artplan.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(unique = true)
    private String name;

    private char sex;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TypeAnimal type;
}
