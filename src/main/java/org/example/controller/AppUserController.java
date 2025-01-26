package org.example.controller;

import org.example.dto.AppUserDTO;
import org.example.service.AppUserService;
import org.example.service.AuthService;
import org.example.utils.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app-users")
public class AppUserController {

    private final AppUserService appUserService;
    private final AuthService authService;

    public AppUserController(AppUserService appUserService, AuthService authService) {
        this.appUserService = appUserService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        return ResponseEntity.ok(appUserService.getAllAppUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(appUserService.getAppUserById(id));
    }

    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUserDTO AppUserDto) {
        return ResponseEntity.ok(appUserService.createAppUser(AppUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody AppUserDTO AppUserDto) {
        return ResponseEntity.ok(appUserService.updateAppUser(id, AppUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AppUserDTO> login(@RequestBody LoginRequest loginRequest) {
        AppUserDTO user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(user);
    }
}
