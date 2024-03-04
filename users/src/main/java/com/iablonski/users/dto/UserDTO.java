package com.iablonski.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iablonski.users.domain.Role;

import java.util.UUID;

public record UserDTO(UUID id,
                      String email,
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                      String password,
                      String username,
                      Role role) {
}