package com.demo.service.impl;

import com.demo.model.Account;
import com.demo.model.Bill;
import com.demo.repository.*;
import com.demo.service.StatisticalService;
import com.demo.service.utils.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    private final BillRepository billRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Override
    public List<List<Integer>> salesByYear(String year) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sales = new ArrayList<>(Collections.nCopies(12, 0));
        List<Integer> imports = new ArrayList<>(Collections.nCopies(12, 0));

        billRepository.findByPaymentTimeByYear(year).forEach(e -> {
            String paymentTime = e.getPaymentTime().toString();
            String[] word = paymentTime.split("-");
            if (word[0].equals(year)) {
                int month = Integer.parseInt(word[1]);
                sales.set(month - 1, sales.get(month - 1) + 1);
            }
        });
        res.add(sales);

        productRepository.findByCreatedAtByYear(year).forEach(e -> {
            String createdAt = e.getCreatedAt().toString();
            String[] word = createdAt.split("-");
            if (word[0].equals(year)) {
                var productDetails = productDetailRepository.findByProduct_Id(e.getId());
                int month = Integer.parseInt(word[1]);
                imports.set(month - 1, imports.get(month - 1) + productDetails.size());
            }
        });
        res.add(imports);
        return res;
    }

    @Override
    public Map<String, Integer> bestCustomer(String year) {

        Map<Account, List<Bill>> accountGrouped = billRepository.findByPaymentTimeByYear(year)
                .stream().collect(Collectors.groupingBy(Bill::getAccount));

        List<Map.Entry<Account, List<Bill>>> list = new ArrayList<>(accountGrouped.entrySet());
        list.sort(Map.Entry.comparingByValue((b1, b2) -> {
            int count1, count2 = 0;
            count1 = b1.stream().mapToInt(e -> e.getProductBills().size()).sum();
            count2 = b2.stream().mapToInt(e -> e.getProductBills().size()).sum();
            return Integer.compare(count2, count1);
        }));

        Map<String, Integer> res = new LinkedHashMap<>();
        for (Map.Entry<Account, List<Bill>> entry : list) {
            if (res.size() > 5) break;
            int count = entry.getValue().stream().mapToInt(e -> e.getProductBills().size()).sum();
            res.put(entry.getKey().getProfile().getFullName() + "-" + entry.getKey().getProfile().getPhoneNumber(), count);
        }

        return res;
    }
}
