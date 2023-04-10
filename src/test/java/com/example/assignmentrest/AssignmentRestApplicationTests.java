package com.example.assignmentrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
class AssignmentRestApplicationTests {
    @Test
    public void httpPostRequest_CreateOneAnimal() {
        // Arrange
        var animal = new HashMap<String, String>() {{
            put("date","2023-04-01");
            put("weight", "550.50");
            put("registrationNr", "202");
            put ("origin", "Belgium");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper
                    .writeValueAsString(animal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(requestBody);

        // Make an instance of HttpClient and create a new HttpRequest
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestToPost = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Animal/"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .setHeader("Content-type", "application/json")
                .build();

        // Act
        HttpResponse<String> responseFromPost;
        try {
            responseFromPost = client.send(requestToPost,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertEquals(200, responseFromPost.statusCode() );
    }

    // HTTP Get - All animals - http://localhost:8080/animals
    @Test
    public void httpGetRequest_GetAllAnimals() {
        // Arrange
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Animal/"))
                .build();

        // Act
        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.body());

        // Assert
        assertEquals(200, response.statusCode());
    }


    // HTTP Get - Animal by ID - http://localhost:8080/animals/{id}
    @Test
    public void httpGetRequest_GetAnimalByRegistrationNr_Equals456() {
        // Arrange
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Animal/registrationNr/?registrationNr=456"))
                .build();

        // Act
        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.body());

        // Assert
        assertEquals(200, response.statusCode());
    }

    // HTTP Get - Animal by origin - http://localhost:8080/animals/origin/{origin}
    @Test
    public void httpGetRequest_GetAnimalByOrigin_EqualsAarhus() {
        // Arrange
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Animal/origin/?origin=Aarhus"))
                .build();

        // Act
        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.body());

        // Assert
        assertEquals(200, response.statusCode());
    }

    // HTTP Get - Animal by date - http://localhost:8080/animals/{date}
    @Test
    public void httpGetRequest_GetAnimalByDate() {
        // Arrange
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Animal/date/?date=2023-04-01"))
                .build();

        // Act
        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.body());

        // Assert
        assertEquals(200, response.statusCode());
    }
}
