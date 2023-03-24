package com.demo.web.dto;

import com.demo.model.Authority;
import com.demo.model.Rank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountDto {
    private Integer id;
    private String username;
    private String email;
    private Set<Authority> authorities = new HashSet<Authority>();
    private Rank rank;
}
