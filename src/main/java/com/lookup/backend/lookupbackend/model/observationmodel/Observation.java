package com.lookup.backend.lookupbackend.model.observationmodel;


import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObject;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "Observation")
@Getter
@Setter
@NoArgsConstructor
public class Observation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @JoinColumn(name = "fileModel_id")
    @OneToOne(fetch = FetchType.LAZY)
    private FileModel pictureDescription;

    @ManyToOne
    @JoinColumn(name = "nearearthObject_id")
    private NearEarthObject nearEarthObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String textDescription;

    @Column
    private long votes;

}
