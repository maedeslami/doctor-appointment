package com.blubank.doctorappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({"classpath:application-test.properties"})
public abstract class AbstractBaseIT {

    @LocalServerPort
    protected int localPort;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected String getBaseUrl() {
        return String.format("http://localhost:%s/", localPort);
    }

}