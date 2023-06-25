package com.microservice.academia.domain.model.ports.api.services;

import com.microservice.academia.domain.model.model.User.UserModel;

public interface UserAcademyService {
    UserModel getUserById(Long idUser);
}
