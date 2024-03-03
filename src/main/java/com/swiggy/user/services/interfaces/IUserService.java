package com.swiggy.user.services.interfaces;

import com.swiggy.user.enums.Role;
import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;

import java.util.Set;

public interface IUserService {
    UserResponseModel register(UserRequestModel user, Set<Role> roles);
    UserResponseModel fetchDeliveryPartner();
}
