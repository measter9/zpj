package pl.pollub.zpj.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pollub.zpj.models.User;
import pl.pollub.zpj.models.Role;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user) {
        if (user.getId() == 0) {
            String sql = "INSERT INTO users (name, isActive, role) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, user.getName(), user.isActive(), user.getRole().name());
        } else {
            String sql = "UPDATE users SET name = ?, isActive = ?, role = ? WHERE id = ?";
            jdbcTemplate.update(sql, user.getName(), user.isActive(), user.getRole().name(), user.getId());
        }
        return user;
    }

    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setActive(rs.getBoolean("isActive"));
            u.setRole(Role.valueOf(rs.getString("role")));
            return u;
        }, id);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setActive(rs.getBoolean("isActive"));
            u.setRole(Role.valueOf(rs.getString("role")));
            return u;
        });
    }
    public int update(int id, User user) {
        String sql = "UPDATE users SET name = ?, isActive = ?, role = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.isActive(), user.getRole().toString(), id);
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deactivate(int id) {
        String sql = "UPDATE users SET isActive = false WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}