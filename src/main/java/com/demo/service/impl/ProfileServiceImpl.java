package com.demo.service.impl;

import com.demo.model.Profile;
import com.demo.repository.ProfileRepository;
import com.demo.service.ProfileService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.AccountDto;
import com.demo.web.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ProfileDto getAdminProfile() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return profileRepository.findByAccount_Username(username)
                .map(profile -> {
                    var profileDto = mappingHelper.map(profile, ProfileDto.class);
                    profileDto.setAccountDto(mappingHelper.map(profile.getAccount(), AccountDto.class));
                    return profileDto;
                })
                .orElseThrow(() -> new RuntimeException("Not found profile with account: " + username));
    }

    @Override
    public List<ProfileDto> getStaff(){
        List<Profile> listProfile = profileRepository.findAll();
        List<ProfileDto> res = new ArrayList<>();
        for(Profile profile : listProfile){
            var profileDto = mappingHelper.map(profile, ProfileDto.class);
            profileDto.setAccountDto(mappingHelper.map(profile.getAccount(), AccountDto.class));
            res.add(profileDto);
        }
        return res;
    }

}
