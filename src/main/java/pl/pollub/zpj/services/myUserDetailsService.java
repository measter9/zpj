package pl.pollub.zpj.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.repository.UserHibernateRepository;

import java.util.Optional;
@Service
@AllArgsConstructor
public class myUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserHibernateRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<pl.pollub.zpj.models.User> user = userRepository.findByName(username);
        if(user.isPresent()){
            var userObj = user.get();

            if(userObj.isActive()){
                UserDetails userDetails =  User.builder()
                        .username(userObj.getName())
                        .password(userObj.getPassword())
                        .roles("ADMIN")
                        .build();
                return userDetails;
            }else{
                UserDetails userDetails =  User.builder()
                        .username(userObj.getName())
                        .password(userObj.getPassword())
                        .roles("USER")
                        .build();
                return userDetails;
            }


        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
