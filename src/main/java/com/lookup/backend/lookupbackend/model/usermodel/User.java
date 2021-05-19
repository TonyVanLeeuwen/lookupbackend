package com.lookup.backend.lookupbackend.model.usermodel;

import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "usertable")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Authority> authorities;

    @Column(nullable = false, unique = true)
    private String emailAdress;

    @Column(nullable = false)
    private String passWord;

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

}
