package com.example.demo.base;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class TestBase {

    protected RestTemplate restTemplate = new RestTemplate();
    protected HttpHeaders headers = new HttpHeaders();
    protected String baseUrl = "http://localhost:8803/books";

    protected void seCreaHeader() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

}
