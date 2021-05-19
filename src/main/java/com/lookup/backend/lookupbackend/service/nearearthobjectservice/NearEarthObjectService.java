package com.lookup.backend.lookupbackend.service.nearearthobjectservice;

import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObject;
import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObjectType;

import java.util.List;

public interface NearEarthObjectService {

    public List<NearEarthObject> getAllNearEarthObjects();
    public NearEarthObject getNearEarthObjectById(Long id);
    public NearEarthObject getNearEarthObjectByType(NearEarthObjectType Type);
}
