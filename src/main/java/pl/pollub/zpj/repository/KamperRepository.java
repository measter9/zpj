package pl.pollub.zpj.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pollub.zpj.models.Kamper;

import java.util.List;

@Repository
public class KamperRepository {

    private final JdbcTemplate jdbcTemplate;

    public KamperRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Kamper save(Kamper kamper) {
        if (kamper.getId() == 0) {
            String sql = "INSERT INTO kampery (name, price) VALUES (?, ?)";
            jdbcTemplate.update(sql, kamper.getName(), kamper.getPrice());
        } else {
            String sql = "UPDATE kampery SET name = ?, price = ? WHERE id = ?";
            jdbcTemplate.update(sql, kamper.getName(), kamper.getPrice(), kamper.getId());
        }
        return kamper;
    }

    public Kamper findById(int id) {
        String sql = "SELECT * FROM kampery WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Kamper k = new Kamper();
            k.setId(rs.getInt("id"));
            k.setName(rs.getString("name"));
            k.setPrice(rs.getDouble("price"));
            return k;
        }, id);
    }

    public List<Kamper> findAll() {
        String sql = "SELECT * FROM kampery";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Kamper k = new Kamper();
            k.setId(rs.getInt("id"));
            k.setName(rs.getString("name"));
            k.setPrice(rs.getDouble("price"));
            return k;
        });
    }
    public int update(int id, Kamper kamper) {
        String sql = "UPDATE kampery SET name = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql, kamper.getName(), kamper.getPrice(), id);
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM kampery WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}