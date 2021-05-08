package com.lookup.backend.lookupbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "usertable")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;


    @Column(nullable = false, unique = true)
    private String emailAdress;


    @Column(nullable = false)
    private String passWord;

}
