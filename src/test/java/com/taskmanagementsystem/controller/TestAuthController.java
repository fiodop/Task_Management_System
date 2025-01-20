package com.taskmanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanagementsystem.model.dto.auth.AuthenticationRequest;
import com.taskmanagementsystem.model.dto.auth.RegisterRequest;
import com.taskmanagementsystem.service.AppUserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;




public class TestAuthController {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URI = "http://localhost:8080/api/v1/auth";

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void testRegister() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("test")
                .email("test@test.com")
                .password("test")
                .build();

        given()
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(registerRequest)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .body("token", matchesPattern("^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$"))
                .log()
                .all();
    }

    @Test
    public void testAuthenticate(){
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("test")
                .password("test")
                .build();

        given()
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(request)
                .when()
                .post("/authenticate")
                .then()
                .statusCode(200)
                .body("token", matchesPattern("^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$"))
                .log()
                .all();

    }

}
