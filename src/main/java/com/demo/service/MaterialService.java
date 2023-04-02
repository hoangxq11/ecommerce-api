package com.demo.service;

import com.demo.web.dto.MaterialDto;

public interface MaterialService {

    void addMaterial(MaterialDto materialDto);

    void removeMaterial(Integer materialId);

    void updateMaterial(Integer materialId);
}
