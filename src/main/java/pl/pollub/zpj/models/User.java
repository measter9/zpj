package pl.pollub.zpj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Getter(AccessLevel.PUBLIC)
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    @NonNull @Setter
    @Id
    @GeneratedValue
    private int id;
    @NonNull @Setter
    private String name;
    @NonNull @Setter
    private String email;
    @NonNull @Setter
    private String password;
    @Setter
    @JsonProperty("isActive")
    private boolean isActive;
    @Setter
    private Role role;
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String userString) {
        Pattern pattern = Pattern.compile("User\\{id=(\\d*), name='(.*)', isActive=(true|false), role=(CUSTOMER|EMPLOYEE|ADMIN)}");
        Matcher matcher = pattern.matcher(userString);
        if (matcher.find()) {
            this.id = Integer.valueOf(matcher.group(1));
            this.name = matcher.group(2);
            this.isActive = Boolean.valueOf(matcher.group(3));
            this.role = Role.valueOf(matcher.group(4));
        } else {
            System.out.println("Deserialization failed");
        }
    }

    public User(int i, String username) {
        this.id = i;
        this.name = username;
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
