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
        Assert.assertEquals("/WEB-INF/login.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenUser_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        Mockito.when(service.validate(user)).thenReturn(true);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("/WEB-INF/welcome.jsp",mvcResult.getResponse().getForwardedUrl());
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
        Assert.assertEquals("Enter Values",mvcResult.getRequest().getAttribute("error"));
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
        Assert.assertEquals("Id password wrong",mvcResult.getRequest().getAttribute("error"));
    }

    @Test
    public void GivenUser_ShouldReturnRegisterPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("/WEB-INF/register.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenUserDAO_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        user.setRepeatPassword("Bhavesh@357");
        Mockito.when(service.validateRegister(user)).thenReturn(true);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("/WEB-INF/login.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenUserDAO_WhenImProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        user.setRepeatPassword("Bhavesh@357");
        Mockito.when(service.validateRegister(user)).thenReturn(false);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("/WEB-INF/register.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenUserDAO_WhenBlank_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("");
        user.setPassword("");
        user.setRepeatPassword("");
        Mockito.when(service.filter(user)).thenReturn("Enter all Values");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("Enter all Values",mvcResult.getRequest().getAttribute("error"));
    }

    @Test
    public void GivenUserDAO_WhenAlreadyRegistered_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        user.setRepeatPassword("Bhavesh@357");
        Mockito.when(service.filter(user)).thenReturn("Already Registered");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("Already Registered",mvcResult.getRequest().getAttribute("error"));
    }

    @Test
    public void GivenUserDAO_WhenEmailInvalid_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357.gmail.com");
        user.setPassword("Bhavesh@357");
        user.setRepeatPassword("Bhavesh@357");
        Mockito.when(service.filter(user)).thenReturn("Email Invalid");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("Email Invalid",mvcResult.getRequest().getAttribute("error"));
    }

    @Test
    public void GivenUserDAO_WhenPasswordInvalid_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh.357");
        user.setRepeatPassword("Bhavesh.357");
        Mockito.when(service.filter(user)).thenReturn("Password Invalid");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("Password Invalid",mvcResult.getRequest().getAttribute("error"));
    }

    @Test
    public void GivenUserDAO_WhenPasswordDifferent_ShouldReturnMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("bhavesh@357");
        user.setRepeatPassword("Bhavesh@357");
        Mockito.when(service.filter(user)).thenReturn("Password dont match");
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        Assert.assertEquals("Password dont match",mvcResult.getRequest().getAttribute("error"));
    }
}