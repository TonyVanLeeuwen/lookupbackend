package com.lookup.backend.lookupbackend.service.nearearthobjectservice;

import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObject;
import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObjectType;
import com.lookup.backend.lookupbackend.repository.NearEarthObjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearEarthObjectServiceImpl implements NearEarthObjectService{

private NearEarthObjectRepository nearEarthObjectRepository;

    @Override
    public List<NearEarthObject> getAllNearEarthObjects() {
        return nearEarthObjectRepository.findAll();
    }

    @Override
    public NearEarthObject getNearEarthObjectById(Long id) {
        return nearEarthObjectRepository.getOne(id);
    }

    @Override
    public NearEarthObject getNearEarthObjectByType(NearEarthObjectType type) {
        return nearEarthObjectRepository.findNearEarthObjectByType(type);
    }
}
