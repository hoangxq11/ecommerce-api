package com.demo.service.impl;

import com.demo.model.ShippingService;
import com.demo.repository.ShippingServiceRepository;
import com.demo.service.ShippingLayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingLayerServiceImpl implements ShippingLayerService {
    private final ShippingServiceRepository shippingServiceRepository;

    @Override
    public List<ShippingService> getAllShippingService() {
        return shippingServiceRepository.findAll();
    }
}
