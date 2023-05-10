package com.demo.web.dto.request;

import com.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCriteria {
    private String categoryName;
    private String supplier;
    private String keyWord;

    public Specification<Product> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(categoryName)) {
                predicates.add(criteriaBuilder.like(root.get("category").get("name"), "%" + categoryName + "%"));
            }
            if (Objects.nonNull(supplier)) {
                predicates.add(criteriaBuilder.like(root.get("supplier").get("name"), "%" + supplier + "%"));
            }
            if (Objects.nonNull(keyWord)) {
                var predicate1 = criteriaBuilder.like(root.get("name"), "%" + keyWord + "%");
                var predicate2 = criteriaBuilder.like(root.get("category").get("name"), "%" + keyWord + "%");
                var predicate3 = criteriaBuilder.like(root.get("description"), "%" + keyWord + "%");
                var predicate4 = criteriaBuilder.like(root.get("supplier").get("name"), "%" + keyWord + "%");
                predicates.add(criteriaBuilder.or(predicate1, predicate2, predicate3, predicate4));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
