package com.demo.web.dto.request;

import com.demo.model.Bill;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class BillCriteria {
    private String keyWord;
    private String status;

    public Specification<Bill> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(status)) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (Objects.nonNull(keyWord)) {
                var predicate1 = criteriaBuilder.like(root.get("paymentMethod"), "%" + keyWord + "%");
                var predicate2 = criteriaBuilder.like(root.get("descriptionPay"), "%" + keyWord + "%");
                var predicate3 = criteriaBuilder.like(root.get("address").get("phoneNumber"), "%" + keyWord + "%");
                var predicate4 = criteriaBuilder.like(root.get("account").get("username"), "%" + keyWord + "%");
                var predicate5 = criteriaBuilder.like(root.get("account").get("email"), "%" + keyWord + "%");
                var predicate6 = criteriaBuilder.like(root.get("account").get("profile").get("fullName"), "%" + keyWord + "%");
                var predicate7 = criteriaBuilder.like(root.get("account").get("profile").get("phoneNumber"), "%" + keyWord + "%");
                predicates.add(criteriaBuilder.or(predicate1, predicate2, predicate3, predicate4, predicate5, predicate6, predicate7));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
