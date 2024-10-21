package pl.pollub.zpj;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Role{
    CUSTOMER,
    ADMIN,
    EMPLOYEE
}

public class User implements Serializable {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter @Setter
    private boolean isActive;
    @Getter @Setter
    private Role role;

    public User(int id, String name){
        this.id = id;
        this.name = name;
        this.isActive = true;
        this.role = Role.CUSTOMER;
    }

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
