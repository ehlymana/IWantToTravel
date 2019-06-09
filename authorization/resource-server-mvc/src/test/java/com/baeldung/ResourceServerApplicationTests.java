package com.baeldung;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baeldung.ResourceServerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ResourceServerApplication.class)
@WebAppConfiguration
public class ResourceServerApplicationTests {

    @Test
    public void contextLoads() {
    }

}
