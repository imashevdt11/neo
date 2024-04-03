package com.imashevdt11.store.dtos.auth;

import com.imashevdt11.store.entities.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String username;

    private String email;

    private String password;

    private Role role;
}
