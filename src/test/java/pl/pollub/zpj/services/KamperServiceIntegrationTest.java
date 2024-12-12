package pl.pollub.zpj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.repository.KamperRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class KamperServiceIntegrationTest {
    @Autowired
    private KamperService kamperService;

    @Autowired
    private KamperRepository kamperRepository;

    private Kamper testKamper;

    @BeforeEach
    public void setUp() {
        testKamper = new Kamper();
        testKamper.setName("Test Kamper");
        testKamper.setPrice(100.0);
        kamperRepository.save(testKamper);
    }

    @Test
    @DisplayName("Test saving a new Kamper")
    public void testSaveKamper() {
        Kamper savedKamper = kamperService.saveKamper(testKamper);
        assertNotNull(savedKamper.getId());
        assertEquals("Test Kamper", savedKamper.getName());
        assertEquals(100.0, savedKamper.getPrice());
    }

    @Test
    @DisplayName("Test updating a Kamper")
    public void testUpdateKamper() {
        testKamper.setName("Updated Kamper");
        testKamper.setPrice(150.0);
        Kamper updatedKamper = kamperService.saveKamper(testKamper);
        assertEquals("Updated Kamper", updatedKamper.getName());
        assertEquals(150.0, updatedKamper.getPrice());
    }

    @Test
    @DisplayName("Test deleting a Kamper")
    public void testDeleteKamper(){
        Kamper savedKamper = kamperService.saveKamper(testKamper);
        assertNotNull(savedKamper.getId(), "The Kamper ID should not be null after saving.");

        Kamper retrievedKamper = kamperRepository.findById(savedKamper.getId());
        assertNotNull(retrievedKamper, "The Kamper should exist in the database.");

        kamperService.deleteKamperById(savedKamper.getId());
        assertNull(kamperRepository.findById(savedKamper.getId()), "The Kamper should be null after deletion.");
    }
}
