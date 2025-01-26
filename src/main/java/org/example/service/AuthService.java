package org.example.service;

import org.example.dto.AppUserDTO;
import org.example.mapper.AppUserMapper;
import org.example.model.AppUser;
import org.example.repo.IAppUserRepository;
import org.example.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final IAppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserMapper appUserMapper;

    public AuthService(IAppUserRepository appUserRepository, PasswordEncoder passwordEncoder, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserMapper = appUserMapper;
    }

    public AppUserDTO authenticate(String email, String password) {
        AppUser user = appUserRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        AppUserDTO userDTO = appUserMapper.toDTO(user);

        return userDTO;
    }
}
