package com.gudyna.webproject.model.service;

import com.gudyna.webproject.exception.ServiceException;
import com.gudyna.webproject.response.form.ResponseUserData;

import java.util.List;

public interface DoctorService {
    List<ResponseUserData> getAllDoctors() throws ServiceException;
}
