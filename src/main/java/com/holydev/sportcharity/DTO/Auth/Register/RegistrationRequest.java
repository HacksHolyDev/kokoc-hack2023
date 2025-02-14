package com.holydev.sportcharity.DTO.Auth.Register;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String fio;
    private String email;
    private String password;

}
