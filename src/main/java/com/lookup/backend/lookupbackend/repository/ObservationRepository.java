package com.lookup.backend.lookupbackend.repository;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    boolean existsByType(String nearEarthObjectType);
    Set<Observation> getObservationByType(String nearEarthObjectType);
}
