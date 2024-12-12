package pl.pollub.zpj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.models.User;
import pl.pollub.zpj.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int updateUser(int id, User user) {
        return userRepository.update(id, user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public void deactivateUser(int id) {
        userRepository.deactivate(id);
    }
}