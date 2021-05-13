package com.lookup.backend.lookupbackend.model.usermodel;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations;

    @Column(nullable = false, unique = true)
    private String emailAdress;

    @Column(nullable = false)
    private String passWord;

}
