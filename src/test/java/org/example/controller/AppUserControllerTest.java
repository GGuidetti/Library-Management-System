package org.example.controller;

import org.example.dto.AppUserDTO;
import org.example.service.AppUserService;
import org.example.service.AuthService;
import org.example.utils.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AppUserControllerTest {

    @Mock
    private AppUserService appUserService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AppUserController appUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<AppUserDTO> mockUsers = List.of(
                new AppUserDTO(1L, "gustavo guidetti", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890"),
                new AppUserDTO(2L, "Jane guidetti", "jane.guidetti@gmail.com", "password", new Date(), "0987654321")
        );

        when(appUserService.getAllAppUsers()).thenReturn(mockUsers);

        ResponseEntity<List<AppUserDTO>> response = appUserController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUsers, response.getBody());
    }

    @Test
    void getUserById() {
        Long userId = 1L;
        AppUserDTO mockUser = new AppUserDTO(userId, "gustavo guidetti", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890");

        when(appUserService.getAppUserById(userId)).thenReturn(mockUser);

        ResponseEntity<AppUserDTO> response = appUserController.getUserById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUser, response.getBody());
    }

    @Test
    void createUser() {
        AppUserDTO mockUser = new AppUserDTO(null, "gustavo guidetti", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890");
        AppUserDTO savedUser = new AppUserDTO(1L, "gustavo guidetti", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890");

        when(appUserService.createAppUser(mockUser)).thenReturn(savedUser);

        ResponseEntity<AppUserDTO> response = appUserController.createUser(mockUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    void updateUser() {
        Long userId = 1L;
        AppUserDTO mockUser = new AppUserDTO(userId, "gustavo guidetti Updated", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890");
        AppUserDTO updatedUser = new AppUserDTO(userId, "gustavo guidetti Updated", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890");

        when(appUserService.updateAppUser(userId, mockUser)).thenReturn(updatedUser);

        ResponseEntity<AppUserDTO> response = appUserController.updateUser(userId, mockUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        doNothing().when(appUserService).deleteAppUser(userId);

        ResponseEntity<Void> response = appUserController.deleteUser(userId);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest("gustavo.guidetti@gmail.com", "password");
        AppUserDTO mockUser = new AppUserDTO(1L, "gustavo guidetti", "gustavo.guidetti@gmail.com", "password", new Date(), "1234567890");

        when(authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(mockUser);

        ResponseEntity<AppUserDTO> response = appUserController.login(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUser, response.getBody());
    }
}
