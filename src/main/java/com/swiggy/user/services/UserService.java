package com.swiggy.user.services;

import com.swiggy.user.enums.Role;
import com.swiggy.user.exceptions.UserAlreadyExistException;
import com.swiggy.user.exceptions.UserNotFoundException;
import com.swiggy.user.models.User;
import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;
import com.swiggy.user.repositories.UserRepository;
import com.swiggy.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserResponseModel register(UserRequestModel user, Set<Role> roles) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UserAlreadyExistException("Username taken. Please try with another username.");
        User userToSave = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), roles);
        User createdUser = userRepository.save(userToSave);

        return new UserResponseModel(createdUser.getId(), createdUser.getUsername());
    }

    @Override
    public UserResponseModel fetchDeliveryPartner() {
        User user = userRepository.findByRoles(Collections.singleton(Role.DELIVERY_PARTNER)).orElseThrow(() -> new UserNotFoundException("No delivery partner available."));
        return new UserResponseModel(user.getId(), user.getUsername());
    }
}
