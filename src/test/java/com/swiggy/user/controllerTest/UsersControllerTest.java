package com.swiggy.user.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.user.enums.Role;
import com.swiggy.user.models.User;
import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;
import com.swiggy.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        reset(userService);
    }

    @Test
    void createUserSuccessfully() throws Exception {
        UserRequestModel userRequestModel = new UserRequestModel("user", "password");
        UserResponseModel userResponseModel = new UserResponseModel(1L, "user");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.CUSTOMER);

        when(userService.register(userRequestModel, roles)).thenReturn(userResponseModel);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestModel)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).register(userRequestModel, roles);
    }

}
