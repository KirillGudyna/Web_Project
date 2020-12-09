package com.gudyna.webproject.model.dao;

import com.gudyna.webproject.exception.DaoException;
import com.gudyna.webproject.model.entity.UserData;
import com.gudyna.webproject.request.form.RequestUserRegistrationData;
import com.gudyna.webproject.response.form.ResponseUserData;

public interface UserDao {
    ResponseUserData addUserAsPatient(RequestUserRegistrationData data) throws DaoException;

    ResponseUserData addUserAsDoctor(RequestUserRegistrationData data) throws DaoException;

    UserData getUserByEmail(String email) throws DaoException;

    UserData getUserById(int id) throws DaoException;

    UserData updateUser(UserData userData) throws DaoException;
}
