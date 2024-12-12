package pl.pollub.zpj.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class RegistrationRequest {
    public String username;
    public String password;
    public String email;
}
