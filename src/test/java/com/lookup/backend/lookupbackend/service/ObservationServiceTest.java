package com.lookup.backend.lookupbackend.service;

import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.repository.ObservationRepository;
import com.lookup.backend.lookupbackend.service.observationservice.ObservationService;
import com.lookup.backend.lookupbackend.service.observationservice.ObservationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObservationServiceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private final ObservationService observationService = new ObservationServiceImpl();

    @Mock
    private ObservationRepository observationRepository;
    private final Observation Observation = new Observation();
    private ResponseEntity ResponseEntity;

    @Test
    void observationServiceNotNull() {
        Assertions.assertNotNull(observationService);
    }

    @Test
    void observationRepositoryNotNull() {
        Assertions.assertNotNull(observationRepository);
    }

    @Test
    void getAllObservationsRequestShouldReturnSetOfObservations() {
        Observation observationOne = new Observation();
        Observation observationTwo = new Observation();
        observationRepository.save(observationOne);
        observationRepository.save(observationTwo);
        when(observationService.getAllObservations()).thenReturn(List.of(Observation));

        List<Observation> observations = observationService.getAllObservations();

        Assertions.assertNotNull(observations);
        Assertions.assertFalse(observations.isEmpty());
    }

    @Test
    void getObservationsSortedByVotesShouldReturnListOfObservations() {
        Observation observationOne = new Observation();
        Observation observationTwo = new Observation();
        observationRepository.save(observationOne);
        observationRepository.save(observationTwo);
        when(observationService.getObservationsSortedByVotes(true)).thenReturn(List.of(Observation));

        List<Observation> observations = observationService.getObservationsSortedByVotes(true);

        Assertions.assertNotNull(observations);
        Assertions.assertFalse(observations.isEmpty());
    }

    @Test
    void getObservationByIdShouldReturnBadRequestIfObservationDoesNotExist() {
        Observation observation = new Observation();
        observationRepository.save(observation);

        ResponseEntity observationOne = observationService.getObservationbyId(observation.getId());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, observationOne.getStatusCode());
    }

}
