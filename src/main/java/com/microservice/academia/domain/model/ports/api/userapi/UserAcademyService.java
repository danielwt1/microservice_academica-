package com.microservice.academia.domain.model.ports.api.userapi;

import com.microservice.academia.domain.model.model.userservice.UserModel;

public interface UserAcademyService {
    UserModel getUserById(Long idUser);
}
