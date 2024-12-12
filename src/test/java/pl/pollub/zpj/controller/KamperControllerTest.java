package pl.pollub.zpj.controller;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pl.pollub.zpj.Config.WebSecurityConfig;
import pl.pollub.zpj.KamperApplication;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.repository.KamperRepository;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
@SpringBootTest(classes = KamperApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KamperControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private KamperRepository kamperRepository;


    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    @Transactional
    void givenLoggedInUser_whenCreateKamper_thenReturnCreatedKamper() throws Exception {
        String campername = "kamper do test";
        double camperprice = 99999;

        mvc.perform(post("/kampery")
                        .contentType("application/json")
                        .content("{\"name\":\""+campername+"\",\"price\":"+camperprice+"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("\"name\":\""+campername+"\",\"price\":"+camperprice+"}")));
    }

    @Test
    @WithMockUser
    void givenLoggedInUser_whenGetCamperById_thenReturnKamper() throws Exception {
        int camperid = 1;
        String campername = "kamper 1";
        double camperprice = 123;

        mvc.perform(get("/kampery/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("{\"id\":"+camperid+",\"name\":\""+campername+"\",\"price\":"+camperprice+"}")));

    }
    @Test
    void givenNoUser_whenGetAllCamperById_thenRedirectToLogin() throws Exception{
        mvc.perform(get("/kampery/1"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    void givenLoggedInUser_whenGetAllKamper_thenReturnKamper() throws Exception {
        int camperid = 1;
        String campername = "kamper 1";
        double camperprice = 123;

        mvc.perform(get("/kampery"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("{\"id\":"+camperid+",\"name\":\""+campername+"\",\"price\":"+camperprice+"}")));

    }

    @Test
    void givenNoUser_whenGetAllKamper_thenRedirectToLogin() throws Exception{
        mvc.perform(get("/kampery"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    @Transactional
    void whenUpdateKamper_returnUpdatedCamper() throws Exception {
        int camperid = 1;
        String campername = "updated kamper";
        double camperprice = 2000;

        mvc.perform(put("/kampery/"+camperid)
                        .contentType("application/json")
                        .content("{\"name\":\""+campername+"\",\"price\":"+camperprice+"}"))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(get("/kampery/"+camperid))
                .andDo(print())
                .andExpect(content().string("{\"id\":"+camperid+",\"name\":\""+campername+"\",\"price\":"+camperprice+"}"));
    }

    @Test
    @WithMockUser
    @Transactional
    void whenDeleteKamper_returnDeletedKamperNotFound() throws Exception {

        int camperid = 2;

        mvc.perform(delete("/kampery/"+camperid))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(get("/kampery/"+camperid))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    private void createTestKamper(int id, String name,double price){
        Kamper k = new Kamper(id,name,price);
        kamperRepository.save(k);
    }
}