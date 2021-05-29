package com.lookup.backend.lookupbackend.model.observationmodel;

import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FileModel pictureDescription;

    @Column(unique = true)
    private String pictureDescriptionURL;

    @Column
    @Enumerated(EnumType.STRING)
    private NearEarthObjectType type;

    @Column
    private String textDescription;

    @Column
    private long votes;

    public void addFile(FileModel fileModel){
        this.pictureDescription = fileModel;
    }
}
