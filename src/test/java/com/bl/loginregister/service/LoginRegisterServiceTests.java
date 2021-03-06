package com.bl.loginregister.service;

import com.bl.loginregister.model.User;
import com.bl.loginregister.model.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginRegisterServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    public void GivenUser_ShouldReturnPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("{\"statusCode\":100,\"message\":\"Successful\",\"object\":\"login\"}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void GivenUser_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");

        User user1 = new User();
        user1.setEmail("bkadam357@gmail.com");
        user1.setPassword("Bhavesh@357");
        Mockito.when(service.validate(user)).thenReturn(user1);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenUser_WhenBlank_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("");
        user.setPassword("");
        Mockito.when(service.filter(user)).thenReturn("Enter Values");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals(400,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenUser_WhenWrong_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("Shivam@gmail.com");
        user.setPassword("Shivam@357");
        Mockito.when(service.filter(user)).thenReturn("Id password wrong");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("{\"statusCode\":-1,\"message\":\"Failed\",\"object\":null}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void GivenUserDAO_ShouldReturnRegisterPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("{\"statusCode\":100,\"message\":\"Successful\",\"object\":\"register\"}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void GivenUserDAO_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");

        User user1 = new User();
        user1.setEmail("bkadam357@gmail.com");
        user1.setPassword("Bhavesh@357");
        Mockito.when(service.validateRegister(user)).thenReturn(user1);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenUserDAO_WhenImProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        Mockito.when(service.validateRegister(user)).thenReturn(null);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("{\"statusCode\":-1,\"message\":\"Failed\",\"object\":null}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void GivenUserDAO_WhenAlreadyRegistered_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        Mockito.when(service.validateRegister(user)).thenReturn(null);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("{\"statusCode\":-1,\"message\":\"Failed\",\"object\":null}",mvcResult.getResponse().getContentAsString());
    }


    @Test
    void givenNullEmail_ShouldReturnMessage() throws Exception {
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON_VALUE).content("{\"email\":\"\",\"password\":\"Bhavesh@57\"}").contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("{\"email\":\"Enter Valid Email with 1 Capital 1 small 1 special character and 1 number\"}",mvcResult.getResponse().getContentAsString());

    }

}
