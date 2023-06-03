package com.demo.web.dto.request;

import com.demo.model.Bill;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class StatisticalCriteria {
    private Date startDate;
    private Date endDate;
    private String status;

    public Specification<Bill> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(startDate)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("paymentTime"), startDate));
            }
            if (Objects.nonNull(endDate)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("paymentTime"), endDate));
            }
            if (Objects.nonNull(status)) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
