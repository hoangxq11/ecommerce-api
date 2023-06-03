package com.demo.service;

import com.demo.web.dto.ProductDto;
import com.demo.web.dto.ProfileDto;
import com.demo.web.dto.request.StatisticalCriteria;
import com.demo.web.dto.response.CustomerStatisticalRes;
import com.demo.web.dto.response.GroupByStatusBillRes;
import com.demo.web.dto.response.ProductStatisticalRes;

import java.util.List;
import java.util.Map;

public interface StatisticalService {
    List<List<Integer>> salesByYear(String year);

    Map<String, Integer> bestCustomer(String year);

    Map<ProductDto, Integer> bestProduct(String year);

    GroupByStatusBillRes billStatus();

    Map<ProductStatisticalRes, Integer> bestSalesProduct(StatisticalCriteria statisticalCriteria);

    Map<CustomerStatisticalRes, Integer> bestCustomerByStatus(StatisticalCriteria statisticalCriteria);
}
