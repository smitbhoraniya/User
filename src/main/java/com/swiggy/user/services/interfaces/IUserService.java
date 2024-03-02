package com.swiggy.user.services.interfaces;

import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;

public interface IUserService {
    UserResponseModel register(UserRequestModel user);
}
