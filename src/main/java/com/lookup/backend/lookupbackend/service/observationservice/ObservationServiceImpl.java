package com.lookup.backend.lookupbackend.service.observationservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationServiceImpl implements ObservationService{

    @Autowired
    private ObservationRepository observationRepository;


    @Override
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    @Override
    public Observation getObservationbyId(Long id) {
        if(observationRepository.existsById(id)){
          return observationRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException("couldn't find this observation");
        }
    }


    @Override
    public Observation getObservationByNearEarthObject(Long nearEarthObjectID) {
        if (observationRepository.existsByNearEarthObject_Id(nearEarthObjectID)){
            return getObservationByNearEarthObject(nearEarthObjectID);
        } else {
            throw new RecordNotFoundException("couldn't find this observation");
        }
    }

    @Override
    public void save(Observation observation) {
        observationRepository.save(observation);
    }

    @Override
    public void deleteById(Long id) {
        if (observationRepository.existsById(id)){
            observationRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No observation with id " + id);
        }
    }

    @Override
    public void updateObservation(Long observationId, Observation observation) {
        if (observationRepository.existsById(observationId)){
            Observation observationToPatch = observationRepository.getOne(observationId);
            observationToPatch.setNearEarthObject(observation.getNearEarthObject());
            observationToPatch.setPictureDescription(observation.getPictureDescription());
            observationToPatch.setVotes(observation.getVotes());
            observationToPatch.setTextDescription(observation.getTextDescription());
            observationRepository.save(observationToPatch);
        } else {
            throw new RecordNotFoundException("No observation with id " + observationId);
        }
    }
}
