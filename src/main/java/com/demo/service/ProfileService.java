package com.demo.service;

import com.demo.model.Profile;
import com.demo.web.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto getCustomerProfile();
    ProfileDto getAdminProfile();

    List<ProfileDto> getStaff();
}
