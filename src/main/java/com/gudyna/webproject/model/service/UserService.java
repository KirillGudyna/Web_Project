package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.form.ResponseUserData;

/**
 * Interface UserService for adding and authorizing of the user
 */
public interface UserService {

    /**
     * Add User in DB
     * @param registrationData
     * @return added user's data
     * @throws ServiceException in case of DB errors during adding user
     */
    ResponseUserData addUser(RequestUserRegistrationData registrationData) throws ServiceException;

    /**
     * Check data with data in DB and if user founded return user data
     * @param email of the user
     * @param password of the password
     * @param isDoctor shows if user should be login as doctor
     * @return user's data
     * @throws ServiceException in case of DB errors during authorizing user
     */
    ResponseUserData authorizeUser(String email, String password, boolean isDoctor) throws ServiceException;
}
