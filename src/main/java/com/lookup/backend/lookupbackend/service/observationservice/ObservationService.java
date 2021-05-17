package com.lookup.backend.lookupbackend.service.observationservice;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;

import java.util.List;

public interface ObservationService {


    public List<Observation> getAllObservations();
    public Observation getObservationbyId(Long id);
    public Observation getObservationByNearEarthObject(Long nearEarthObjectID);
    public void save(Observation observation);
    public void deleteById(Long id);
    public void updateObservation(Long observationId, Observation observation);
}
