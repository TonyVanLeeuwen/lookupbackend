package com.lookup.backend.lookupbackend.repository;

import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NearEarthObjectRepository extends JpaRepository<NearEarthObject, Long> {
}
