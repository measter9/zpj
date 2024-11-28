package pl.pollub.zpj.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.zpj.DTOs.InspectionDto;
import pl.pollub.zpj.models.Inspection;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.repository.InspectionRepostiory;

import java.sql.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InspectionServiceTest {

    @Autowired
    InspectionRepostiory inspectionRepostiory;
    @Autowired
    InspectionService inspectionService;

    @BeforeAll
    static void beforeAll() {

    }

    @AfterAll
    static void afterAll() {
    }

    @Test
    void findByKamperId() {
        Inspection i =  inspectionRepostiory.findById(0).orElse(null);
        System.out.println(i);
        Assertions.assertThat(i.getId()).isEqualTo(0);
    }

    @Test
    void findAll() {
        Iterable<Inspection> inspectionIterable = inspectionRepostiory.findAll();
        System.out.println(inspectionIterable);
    }

    @Test
    void createInspection() {
        Inspection i = new Inspection(0,"inspection Test",new Date(2024,11,21),"opis",null);
        inspectionRepostiory.save(i);
        Assertions.assertThat(inspectionRepostiory.findById(0).orElse(null)).isNotNull();
    }

    @Test
    void deleteInspection() {
    }

    @Test
    void updateInspection() {
        InspectionDto inspectionDto = new InspectionDto("name", new Date(123),"desc",0);
        Inspection i = new Inspection(90,"test",new Date(2134),"opsis",null);
        inspectionRepostiory.save(i);
        try {
            inspectionService.updateInspection(90,inspectionDto);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        Iterable<Inspection> inspectionIterable = inspectionService.findByKamperId(0);


        Assertions.assertThat(inspectionIterable).anyMatch(inspection -> {
            if (inspection.getName() == "name " && inspection.getValidUntil() == new Date(123) && inspection.getDescription() == "desc" && inspection.getKamper() == null){
                return true;
            }
            return false;
        });
        inspectionRepostiory.deleteById(90);
    }
}