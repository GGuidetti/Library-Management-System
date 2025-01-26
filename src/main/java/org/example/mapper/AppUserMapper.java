package org.example.mapper;

import org.example.dto.AppUserDTO;
import org.example.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public static AppUserDTO toDTO(AppUser appUser) {
        if (appUser == null) {
            return null;
        }

        return new AppUserDTO(
                appUser.getId(),
                appUser.getName(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getRegisterDate(),
                appUser.getPhone()
        );
    }

    public static AppUser toEntity(AppUserDTO appUserDTO) {
        if (appUserDTO == null) {
            return null;
        }

        AppUser appUser = new AppUser();
        appUser.setId(appUserDTO.getId());
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setRegisterDate(appUserDTO.getRegisterDate());
        appUser.setPhone(appUserDTO.getPhone());

        return appUser;
    }
}