package com.example.hotelmanagementservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelManagementServiceApplicationTests {


    String umsPort = "8088";

    @Test
    public void contextLoads() {
    }

    /*@Test

    public void findUserFromUMS() throws URISyntaxException
    {
        String userID = "1";

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + umsPort + "/user/" + userID;

        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);



        //Verify request succeed

        Assert.assertFalse(result.getBody().isEmpty());

    }*/

}
