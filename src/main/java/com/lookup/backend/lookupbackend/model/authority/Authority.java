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
@IdClass(com.lookup.backend.lookupbackend.model.authority.Authoritykey.class)
@Table(name = "authority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable {

    public Authority(String username, String authority){
        this.username = username;
        this.authority = authority;
    }

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> user = new HashSet<>();
}
