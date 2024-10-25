package pl.pollub.zpj.models;

import lombok.*;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Role{
    CUSTOMER,
    ADMIN,
    EMPLOYEE
}
@Getter(AccessLevel.PUBLIC)
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @Setter
    private boolean isActive;
    @Setter
    private Role role;

    public User(String userString){
        Pattern pattern = Pattern.compile("User\\{id=(\\d*), name='(.*)', isActive=(true|false), role=(CUSTOMER|EMPLOYEE|ADMIN)}");
        Matcher matcher = pattern.matcher(userString);
        if(matcher.find()){
            this.id = Integer.valueOf(matcher.group(1));
            this.name = matcher.group(2);
            this.isActive = Boolean.valueOf(matcher.group(3));
            this.role = Role.valueOf(matcher.group(4));

        }else{
            System.out.println("niudana deserializacja");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                '}';
    }
}
