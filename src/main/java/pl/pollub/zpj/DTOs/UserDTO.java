package pl.pollub.zpj.DTOs;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.pollub.zpj.models.Order;
import pl.pollub.zpj.models.Role;
import pl.pollub.zpj.models.User;

@RequiredArgsConstructor
@Getter
@ToString
public class UserDTO {
final private  @NonNull String name;
final private @NonNull boolean isActive;
final private @NonNull Role role;

    public UserDTO(User user) {
        this.isActive = user.isActive();
        this.name = user.getName();
        this.role = user.getRole();
    }
    public User toEntity(){
        return new User(1,this.getName(),this.isActive, this.getRole());
    }
}
