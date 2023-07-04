package com.microservice.academia.domain.usecase.userapi;

import com.microservice.academia.domain.model.model.userservice.UserModel;
import com.microservice.academia.domain.model.ports.api.userapi.UserAcademyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserModelUseCase {
    private final UserAcademyService userAcademyService;

    public UserModel action(Long idUser) {
        return userAcademyService.getUserById(idUser);
    }
}
