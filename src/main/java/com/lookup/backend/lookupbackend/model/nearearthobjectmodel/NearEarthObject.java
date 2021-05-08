package com.lookup.backend.lookupbackend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="NearEarthObject")
@Getter
@Setter
@NoArgsConstructor
public class NearEarthObject {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Enumerated

}
