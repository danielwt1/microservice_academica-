package com.microservice.academia.infrastructure.drivenadapters.userservice.feignClient;

import com.microservice.academia.domain.model.model.userservice.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-MOCK-API", url = "${external.mock.api.base-url}")
public interface UserModelFeignClient {
    @GetMapping(value = "/users/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserModel findUserById(@PathVariable("idUser") Long idProduct);
}
