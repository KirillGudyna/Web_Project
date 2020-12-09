package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.form.ResponseUserData;

public interface UserService {

    ResponseUserData addUser(RequestUserRegistrationData registrationData) throws ServiceException;

    ResponseUserData authorizeUser(String email, String password, boolean isDoctor) throws ServiceException;
}
