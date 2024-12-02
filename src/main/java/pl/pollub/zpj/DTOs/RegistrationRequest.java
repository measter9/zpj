package pl.pollub.zpj.DTOs;

import lombok.Getter;

@Getter
public class RegistrationRequest {
    public String username;
    public String password;
    public String email;
}
