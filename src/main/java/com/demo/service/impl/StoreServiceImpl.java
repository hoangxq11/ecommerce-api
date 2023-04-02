package com.demo.service.impl;

import com.demo.repository.StoreRepository;
import com.demo.service.StoreService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProfileDto;
<<<<<<< HEAD
import com.demo.web.dto.StoreDto;
=======
>>>>>>> fe19187b42306f21e3065b11c7446f58f6d07ca1
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    private final MappingHelper mappingHelper;
    private final StoreRepository storeRepository;

    @Override
<<<<<<< HEAD
    public StoreDto getStoreProfile(Integer id){
        return storeRepository.getStoreById(id)
                .map(store -> {
                    var storeDto = mappingHelper.map(store, StoreDto.class);
=======
    public ProfileDto getStoreProfile(Integer id){
        return storeRepository.getStoreById(id)
                .map(store -> {
                    var storeDto = mappingHelper.map(store, ProfileDto.class);
>>>>>>> fe19187b42306f21e3065b11c7446f58f6d07ca1
                    ResponseEntity.ok(store);
                    return storeDto;
                })
                .orElseThrow(() -> new RuntimeException("Not found profile with id: " + id));
    }
}
