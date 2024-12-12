package pl.pollub.zpj.DTOs;

import lombok.*;
import pl.pollub.zpj.models.Role;
import pl.pollub.zpj.models.User;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO{
final private  @NonNull String name;
final private @NonNull boolean isActive;
final private @NonNull Role role;

    public UserDTO(User user){
        this.isActive = user.isActive();
        this.name = user.getName();
        this.role = user.getRole();
    }
    public User toEntity(){
        return new User(1,this.getName(),null,null,this.isActive, this.getRole());
    }
}
