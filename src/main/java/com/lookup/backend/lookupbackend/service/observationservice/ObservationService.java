package com.lookup.backend.lookupbackend.service.observationservice;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ObservationService {
    public List<Observation> getAllObservations();
    public ResponseEntity<Object> getObservationbyId(Long id);
    public Long getVotes(Long id);
    public Set<Observation> getObservationByNearEarthObject(String nearEarthObjectID);
    public List<Observation> getObservationsSortedByVotes(Boolean descending);
    public void save(Observation observation);
    public void deleteById(Long id);
    public void updateObservation(Long observationId, Observation observation);
}
