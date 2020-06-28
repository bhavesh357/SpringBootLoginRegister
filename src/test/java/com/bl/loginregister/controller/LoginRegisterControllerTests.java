package com.bl.loginregister.controller;


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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginRegisterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;


    @Test
    public void GivenWrong_ShouldReturnPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/random").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(404,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenLogin_ShouldReturnPage() throws Exception {
        Mockito.when(controller.printLogin()).thenReturn("login");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenLoginPost_WhenImProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(controller.printLogin(Mockito.any(User.class),Mockito.any(HttpServletResponse.class))).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void GivenLoginPost_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(controller.printLogin(Mockito.any(User.class),Mockito.any(HttpServletResponse.class))).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenRegister_ShouldReturnPage() throws Exception {
        Mockito.when(controller.printRegister()).thenReturn("register");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register").
                accept(MediaType.ALL);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void GivenRegisterPost_WhenImProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(controller.printRegister(Mockito.any(UserDAO.class), Mockito.any(HttpServletResponse.class))).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void GivenRegisterPost_WhenProper_ShouldReturnPage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        user.setEmail("bkadam357@gmail.com");
        user.setPassword("Bhavesh@357");

        User user1 = new User();
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(controller.printRegister(Mockito.any(UserDAO.class), Mockito.any(HttpServletResponse.class))).thenReturn(user1);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("{\"id\":null,\"email\":\"bkadam357@gmail.com\",\"password\":\"Bhavesh@357\"}",mvcResult.getResponse().getContentAsString());
    }


}
