package click.snekhome.backend.controller;

import click.snekhome.backend.models.*;
import click.snekhome.backend.repo.CardRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CardRepo cardRepo;

    @Test
    @DirtiesContext
    void expectAllCards_whenGettingCards() throws Exception {
        //GIVEN
        cardRepo.add(new Card(1, "Shop for groceries", Status.OPEN));
        String expected = """
                [
                        {
                            "id": 1,
                            "description": "Shop for groceries",
                            "status": "OPEN"
                        }
                ]
                """;
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
        //THEN
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));

    }

    @Test
    @DirtiesContext
    void expectCard_whenGettingCardById() throws Exception {
        //GIVEN
        cardRepo.add(new Card(1, "Shop for groceries", Status.OPEN));
        String expected = """
                {
                    "id": 1,
                    "description": "Shop for groceries",
                    "status": "OPEN"
                }
                """;
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
        //THEN
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));

    }

    @Test
    @DirtiesContext
    void expectUpdatedCards_whenPostingCard() throws Exception {
        //GIVEN
        String requestBody = """
                {
                    "description":"Shop for groceries",
                    "status":"OPEN"
                }
                """;
        String expected = """
                [
                        {
                            "id": 1,
                            "description": "Shop for groceries",
                            "status": "OPEN"
                        }
                ]
                """;
        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/api/todo").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        //THEN
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));

    }

    @Test
    @DirtiesContext
    void expectUpdatedCard_whenPuttingCard() throws Exception {
        //GIVEN
        cardRepo.add(new Card(1, "Shop for groceries", Status.OPEN));
        String requestBody = """
                {
                    "description":"Clean living room",
                    "status":"OPEN"
                }
                """;
        String expected = """
                {
                    "id": 1,
                    "description": "Clean living room",
                    "status": "OPEN"
                }
                """;
        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/api/todo/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        //THEN
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));

    }

    @Test
    @DirtiesContext
    void expectUpdatedCardList_whenDeletingCard() throws Exception {
        cardRepo.add(new Card(1, "Shop for groceries", Status.OPEN));
        cardRepo.add(new Card(2, "Clean the bathroom", Status.OPEN));

        String expected = """
                [
                        {
                            "id": 2,
                            "description": "Clean the bathroom",
                            "status": "OPEN"
                        }
                ]
                """;
        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
        //THEN
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));

    }

}