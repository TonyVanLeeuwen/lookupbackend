package com.lookup.backend.lookupbackend.service.observationservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ObservationServiceImpl implements ObservationService{

    @Autowired
    private ObservationRepository observationRepository;

    @Override
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> getObservationbyId(Long id) {
        if(observationRepository.existsById(id)){
          return new ResponseEntity<>(observationRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This Observation could not be found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Long getVotes(Long id) {
        if(observationRepository.existsById(id)){
            return observationRepository.getOne(id).getVotes();
        } else {
            throw new RecordNotFoundException("Couldn't find observation with id " + id);
        }
    }

    @Override
    public Set<Observation> getObservationByNearEarthObject(String nearEarthObjectID) {
        if (observationRepository.existsByType(nearEarthObjectID)){
            return observationRepository.getObservationByType(nearEarthObjectID);
        } else {
            throw new RecordNotFoundException("couldn't find this observation");
        }
    }

    @Override
    public List<Observation> getObservationsSortedByVotes(Boolean descending) {
        if (descending){
            return observationRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
        } else {
            return observationRepository.findAll(Sort.by(Sort.Direction.ASC, "votes"));
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
            observationToPatch.setType(observation.getType());
            observationToPatch.setPictureDescription(observation.getPictureDescription());
            observationToPatch.setVotes(observation.getVotes());
            observationToPatch.setTextDescription(observation.getTextDescription());
            observationRepository.save(observationToPatch);
        } else {
            throw new RecordNotFoundException("No observation with id " + observationId);
        }
    }
}
