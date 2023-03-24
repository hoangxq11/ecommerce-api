package com.demo.service.impl;

import com.demo.repository.ProfileRepository;
import com.demo.service.ProfileService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.AccountDto;
import com.demo.web.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final MappingHelper mappingHelper;

    @Override
    public ProfileDto getCustomerProfile() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return profileRepository.findByAccount_Username(username)
                .map(profile -> {
                    var profileDto = mappingHelper.map(profile, ProfileDto.class);
                    profileDto.setAccountDto(mappingHelper.map(profile.getAccount(), AccountDto.class));
                    return profileDto;
                })
                .orElseThrow(() -> new RuntimeException("Not found profile with account: " + username));
    }
}
