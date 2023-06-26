package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.User.UserModel;
import com.microservice.academia.domain.model.ports.api.services.UserAcademyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserModelUseCase {
    private final UserAcademyService userAcademyService;

    public UserModel action(Long idUser) {
        return userAcademyService.getUserById(idUser);
    }
}
