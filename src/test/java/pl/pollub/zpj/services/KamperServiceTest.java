package pl.pollub.zpj.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pollub.zpj.KamperApplication;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.repository.KamperRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class KamperServiceTest {
@Autowired
KamperService kamperService;
    @Test
    void getAllKampers() {
//        KamperService kamperService = new KamperService(kamperRepository);
        assertTrue(kamperService.getAllKampers().isEmpty());
    }
}