package com.lookup.backend.lookupbackend.repository;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    public Observation getObservationByNearEarthObject_Id(Long nearEarthObjectId);
    public boolean existsByNearEarthObject_Id(Long nearEarthObjectId);
}
