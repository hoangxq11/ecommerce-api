package com.demo.repository;

import com.demo.model.Account;
import com.demo.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
    Boolean existsByAccountAndProductBill_Id(Account account, Integer productBillId);
}
