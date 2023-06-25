package com.microservice.academia.domain.model.model.User;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserModel {
    private Long idUser;
    private String name;
    private String lastName;
    private String email;
    private Long code;
}
