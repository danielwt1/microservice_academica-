package com.microservice.academia.infrastructure.drivenadapters.userservice.services;

import com.microservice.academia.domain.model.model.userservice.UserModel;
import com.microservice.academia.domain.model.ports.api.userapi.UserAcademyService;
import com.microservice.academia.infrastructure.drivenadapters.userservice.feignClient.UserModelFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserModelServicesImpl implements UserAcademyService {
    private final UserModelFeignClient userModelFeignClient;

    @Override
    public UserModel getUserById(Long idUser) {
        return userModelFeignClient.findUserById(idUser);
    }
}
