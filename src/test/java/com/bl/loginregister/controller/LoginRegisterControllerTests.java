package com.bl.loginregister.controller;


import com.bl.loginregister.model.User;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginRegisterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;

    @Test
    public void GivenLogin_ShouldReturnPage() throws Exception {
        Mockito.when(controller.printLogin()).thenReturn("login");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("/WEB-INF/login.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenLoginPost_WhenImProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(controller.printLogin(Mockito.any(User.class), Mockito.any(HttpServletRequest.class))).thenReturn("login");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("/WEB-INF/login.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenLoginPost_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(controller.printLogin(Mockito.any(User.class), Mockito.any(HttpServletRequest.class))).thenReturn("welcome");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("/WEB-INF/welcome.jsp",mvcResult.getResponse().getForwardedUrl());
    }

    @Test
    public void GivenRegister_ShouldReturnPage() throws Exception {
        Mockito.when(controller.printRegister()).thenReturn("register");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("/WEB-INF/register.jsp",mvcResult.getResponse().getForwardedUrl());
    }




}
