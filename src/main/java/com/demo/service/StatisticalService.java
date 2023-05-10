package com.demo.service;

import com.demo.web.dto.ProfileDto;

import java.util.List;
import java.util.Map;

public interface StatisticalService {
    List<List<Integer>> salesByYear(String year);

    Map<String, Integer> bestCustomer(String year);
}
