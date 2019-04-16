package com.nwt.ums;

import com.nwt.ums.Controller.UserController;
import com.nwt.ums.Model.Hotel;
import com.nwt.ums.Model.Role;
import com.nwt.ums.Model.User;
import com.nwt.ums.Services.RoleService;
import com.nwt.ums.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:./application-test.properties")
//@PropertySource("application-test.properties")
@AutoConfigureMockMvc
public class UserManagementServiceApplicationTests {
    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }

//    @Test
//    public void sayHiTest() throws Exception {
//        String url = "http://localhost:8088/reservations/hi";
//       // assertThat(this.loadBalanced.getForObject(url, ResponseEntity.class)).contains()
//    }
//
//    @Test
//    public void testCommunication() {
//        String url = "http://localhost:8088/reservations/hi";
//    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRMSCommunicationSayHi() throws Exception {
        String url = "http://localhost:8088/reservations/hi";
        this.mockMvc.perform(get("/reservations/hi")).andDo(print()).andExpect(content().string(containsString("jo")));
        //assertThat(this.loadBalanced.getForObject(url, ResponseEntity.class)).toString().contains("jo");
    }
    @Test
    public void testFindUserByid() throws Exception {
        Role role = new Role("ROLE_USER", Long.parseLong("0"));
        roleService.save(role);
        User user = new User("testUser", "testPassword", "Test", "User", "testemail@gmail.com", 0.0, 0.0, "", "", "", true);
        user.setRole(role);
        userService.save(user);
        Long id = user.getUserID();

        String url = "/user/" + id;
        //String expected = "{\"userID\":6,\"username\":\"testUser\",\"password\":\"testPassword\",\"firstName\":\"Test\",\"lastName\":\"User\",\"role\":{\"roleID\":5,\"roleName\":\"ROLE_USER\",\"userId\":0},\"email\":\"testemail@gmail.com\",\"longitude\":0.0,\"latitude\":0.0,\"confirmToken\":\"\",\"reactivateToken\":\"\",\"passwordToken\":\"\",\"enabled\":true}";
        this.mockMvc.perform(get(url)).andDo(print()).andExpect(content().string(containsString(user.getFirstName())))
                .andExpect(content().string(containsString(user.getLastName())));

    }

    @Test
    public void testHMSCommunication() throws Exception {
        String url = "/search/hotels";
        assertThat(this.mockMvc.perform(get(url)).andReturn().getResponse().getContentAsString()).contains("ETF");
//        MvcResult mvcResult = resultActions.andReturn();
//        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
//        String result = mockHttpServletResponse.getContentAsString();
//        assertThat(result).contains("ETF");
//        assertThat(result).contains("neki desc2");

//         resultActions.andExpect(content().string(containsString("ETF")))
//                .andExpect(content().string(containsString("neki desc2")));
//        //        this.mockMvc.perform(get(url)).andDo(print()).andExpect(content().string(containsString(user.getFirstName())))
//                .andExpect(content().string(containsString(user.getLastName())));
    }

}
