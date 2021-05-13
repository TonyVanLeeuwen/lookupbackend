package com.lookup.backend.lookupbackend.model.nearearthobjectmodel;


import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="nearearthobject")
@NoArgsConstructor
public class NearEarthObject {

    @Id
    @Column
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column
    private NearEarthObjectType type;

    @OneToMany(mappedBy = "nearEarthObject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations;

}
