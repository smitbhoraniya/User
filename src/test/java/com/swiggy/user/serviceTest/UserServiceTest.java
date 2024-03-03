package com.swiggy.user.serviceTest;

import com.swiggy.user.enums.Role;
import com.swiggy.user.models.User;
import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;
import com.swiggy.user.repositories.UserRepository;
import com.swiggy.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private Authentication authentication;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SecurityContext securityContext;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void createUserSuccessfully() {
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(new User( "user", "encodedPassword"));
        UserRequestModel userRequestModel = new UserRequestModel("user", "password");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.CUSTOMER);

        UserResponseModel savedUser = userService.register(userRequestModel, roles);

        UserResponseModel expected = new UserResponseModel(null, "user");
        assertEquals(expected, savedUser);
        verify(userRepository, times(1)).findByUsername("user");
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(any());
    }
}
