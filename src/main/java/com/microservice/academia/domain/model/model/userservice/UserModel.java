package com.microservice.academia.domain.model.model.userservice;

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
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Long code;
    private Long typeUserId;
    private String typeUserName;
}
