package com.taskmanagementsystem.controller;

import com.taskmanagementsystem.model.dto.CommentDto;
import com.taskmanagementsystem.model.enums.Status;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class TestUserController {
    private static final String BASE_URI = "http://localhost:8080/api/v1/task-management-system/user";
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void testUpdateTask(){
        long taskId = 0;
        Status status = Status.COMPLETED;

        given()
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .pathParam("taskId", taskId)
                .pathParam("status", status)
                .when()
                .patch("/update/{taskId}/{status}")
                .then()
                .body("status", equalTo("COMPLETED"))
                .log()
                .all();
    }

    /**
     * Positive test
     *
     * Before start make sure db contains in table App_user row with usermname "test"
     */
    @Test
    public void testComment(){
        CommentDto commentDto = CommentDto.builder()
                .comment("This is a comment")
                .authorUsername("test")
                .createdAt(LocalDateTime.now())
                .taskId(0)
                .build();
        //todo дописать тесты

    }
}
