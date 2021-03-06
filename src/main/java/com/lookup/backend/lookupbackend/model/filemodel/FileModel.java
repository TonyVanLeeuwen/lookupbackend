package com.lookup.backend.lookupbackend.model.filemodel;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table()
public class FileModel {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String name;

    @Column
    private String contentType;

    @Column
    private Long size;

    @Lob
    private byte[] data;

    @OneToOne
    private Observation observation;
}
