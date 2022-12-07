package com.example.booklibrary.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    //get total
    //get isbn
    //put
    //delete

    //Books
        //9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"
        //9783110189032L, "Anorganische Chemie", "Erwin Riedel, Christoph Janiak"
        //9783827416148L, "Naturstoffe der chemischen Industrie", "Bernd Schäfer"

    @Test
    void getBooks_ShouldReturnThreeBooks() throws Exception {
        //Given
        String expectedJSON = """
            [
                {
                    "title": "Maßanalyse",
                    "author": "Gerhart Jander, Karl Friedrich Jahr",
                    "isbn": 9783110194470
                },
                {
                    "title": "Anorganische Chemie",
                    "author": "Erwin Riedel, Christoph Janiak",
                    "isbn": 9783110189032
                },
                {
                    "title": "Naturstoffe der chemischen Industrie",
                    "author": "Bernd Schäfer",
                    "isbn": 9783827416148
                }
            ]    
            """;
        //When - Then
        mvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void getBook_ByID() throws Exception {
        //Given
        String expectedJSON = """
               {
                   "title": "Anorganische Chemie",
                   "author": "Erwin Riedel, Christoph Janiak",
                   "isbn": 9783110189032
               }
            """;
        //When - Then
        mvc.perform(MockMvcRequestBuilders.get("/books/9783110189032"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void postCar_TestForPost() throws Exception {
        //Given
        String requestBody = """
               {
                        "title": "Organische Chemie",
                        "author": "Eberhard Breitmaier, Günther Jung",
                        "isbn": 9783135415062
                    }
            """;


        String expectedJSON = """
                [
                    {
                        "title": "Maßanalyse",
                        "author": "Gerhart Jander, Karl Friedrich Jahr",
                        "isbn": 9783110194470
                    },
                    {
                        "title": "Anorganische Chemie",
                        "author": "Erwin Riedel, Christoph Janiak",
                        "isbn": 9783110189032
                    },
                    {
                        "title": "Naturstoffe der chemischen Industrie",
                        "author": "Bernd Schäfer",
                        "isbn": 9783827416148
                    },
                    {
                        "title": "Organische Chemie",
                        "author": "Eberhard Breitmaier, Günther Jung",
                        "isbn": 9783135415062
                    }
                ]
                """;
        //When - Then
        mvc.perform(MockMvcRequestBuilders.put("/books/9783135415062")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void deleteBook_TestForDelete() throws Exception {
        //Given
        String requestBody = "9783110194470";


        String expectedJSON = """
                [
                    {
                        "title": "Anorganische Chemie",
                        "author": "Erwin Riedel, Christoph Janiak",
                        "isbn": 9783110189032
                    },
                    {
                        "title": "Naturstoffe der chemischen Industrie",
                        "author": "Bernd Schäfer",
                        "isbn": 9783827416148
                    }
                ]
                """;
        //When - Then
        mvc.perform(MockMvcRequestBuilders.delete("/books/9783110194470")
                    .content("")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));


    }





}