package com.demo.web.dto.request;

import com.demo.model.ProductDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailCriteria {
    private String categoryName;
    private Double minPrice;
    private Double maxPrice;
    private String color;
    private String supplier;
    private String size;
    private String material;
    private String keyWord;

    public Specification<ProductDetail> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(categoryName)) {
                predicates.add(criteriaBuilder.equal(root.get("product").get("category").get("name"), categoryName));
            }
            if (Objects.nonNull(minPrice)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (Objects.nonNull(maxPrice)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (Objects.nonNull(color)) {
                predicates.add(criteriaBuilder.equal(root.get("color").get("name"), color));
            }
            if (Objects.nonNull(supplier)) {
                predicates.add(criteriaBuilder.equal(root.get("product").get("supplier").get("name"), supplier));
            }
            if (Objects.nonNull(size)) {
                predicates.add(criteriaBuilder.equal(root.get("size").get("name"), size));
            }
            if (Objects.nonNull(material)) {
                predicates.add(criteriaBuilder.equal(root.get("product").get("material").get("name"), material));
            }
            if (Objects.nonNull(keyWord)) {
                predicates.add(criteriaBuilder.like(root.get("product").get("name"), "%" + keyWord + "%"));
                predicates.add(criteriaBuilder.like(root.get("product").get("category").get("name"), "%" + keyWord + "%"));
                predicates.add(criteriaBuilder.like(root.get("product").get("description"), "%" + keyWord + "%"));
                predicates.add(criteriaBuilder.like(root.get("product").get("supplier").get("name"), "%" + keyWord + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
