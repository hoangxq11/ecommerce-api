package com.demo.service.impl;

import com.demo.repository.AccountRepository;
import com.demo.repository.ProfileRepository;
import com.demo.service.AccountService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.AccountDto;
import com.demo.web.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<ProfileDto> getAllAccountProfiles() {
        return profileRepository.findAll()
                .stream()
                .filter(e -> {
                    var authorities = e.getAccount().getAuthorities().stream().map(i -> i.getName())
                            .collect(Collectors.toList());
                    return authorities.contains("ROLE_CUSTOMER");
                })
                .map(profile -> {
                    var profileDto = mappingHelper.map(profile, ProfileDto.class);
                    profileDto.setAccountDto(mappingHelper.map(profile.getAccount(), AccountDto.class));
                    return profileDto;
                }).collect(Collectors.toList());
    }
}
