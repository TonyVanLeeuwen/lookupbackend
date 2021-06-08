package com.lookup.backend.lookupbackend.model.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Authoritykey.class)
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable {

    @Id
    @Column
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;


}
