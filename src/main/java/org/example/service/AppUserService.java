package org.example.service;

import org.example.dto.AppUserDTO;
import org.example.repo.IAppUserRepository;
import org.example.model.AppUser;
import org.example.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private IAppUserRepository appUserRepository;


    private PasswordEncoder passwordEncoder;

    public AppUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUserDTO> getAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream()
                .map(appUser -> new AppUserDTO(appUser.getId(), appUser.getName(), appUser.getEmail(), appUser.getPassword(),appUser.getRegisterDate(), appUser.getPhone()))
                .collect(Collectors.toList());
    }

    public AppUserDTO getAppUserById(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        return new AppUserDTO(appUser.getId(),  appUser.getName(), appUser.getEmail(), appUser.getPassword(),appUser.getRegisterDate(), appUser.getPhone());
    }

    public AppUser getAppUserByName(String name) {
        return appUserRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o username: " + name));
    }

    public AppUserDTO createAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        appUser.setRegisterDate(appUserDTO.getRegisterDate());
        appUser.setPhone(appUserDTO.getPhone());

        AppUser savedAppUser = appUserRepository.save(appUser);
        return new AppUserDTO(savedAppUser.getId(), savedAppUser.getName(), savedAppUser.getEmail(), savedAppUser.getPassword(),savedAppUser.getRegisterDate(), savedAppUser.getPhone());
    }

    public AppUserDTO updateAppUser(Long id, AppUserDTO appUserDTO) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        appUser.setRegisterDate(appUserDTO.getRegisterDate());
        appUser.setPhone(appUserDTO.getPhone());

        AppUser updatedAppUser = appUserRepository.save(appUser);
        return new AppUserDTO(updatedAppUser.getId(), updatedAppUser.getName(), updatedAppUser.getEmail(), updatedAppUser.getPassword(),updatedAppUser.getRegisterDate(), updatedAppUser.getPhone());
    }


    public void deleteAppUser(Long id) {
        if (!appUserRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        appUserRepository.deleteById(id);
    }
}
