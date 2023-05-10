package com.demo.service;

import com.demo.web.dto.BillDto;
import com.demo.web.dto.request.BillCriteria;
import com.demo.web.dto.request.BillReq;
import com.demo.web.dto.request.BillStatusUpdateReq;

import java.util.List;

public interface BillService {
    void createBillByCurrentAccount(BillReq billReq);

    List<BillDto> getBillsOfCurrentAccount();

    BillDto getBillById(Integer billId);

    List<BillDto> getCustomBills(BillCriteria billCriteria);

    void updateBillStatus(BillStatusUpdateReq billStatusUpdateReq);
}
