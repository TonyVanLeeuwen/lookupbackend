package com.lookup.backend.lookupbackend.model.authority;


import com.lookup.backend.lookupbackend.model.usermodel.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
