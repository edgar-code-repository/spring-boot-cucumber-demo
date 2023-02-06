package com.example.demo.base;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class TestBase {

    protected RestTemplate restTemplate = new RestTemplate();
    protected HttpHeaders headers = new HttpHeaders();

}
