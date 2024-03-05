package com.budzko.cookbook.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommandControllerTest {

    private static final String PREFIX = "/command/";
    @Value("${server.servlet.contextPath:}")
    private String contextPath;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStart() throws Exception {
        MvcResult mvcResult = mockMvc.perform(httpPOSTRequestBuilder("start"))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        assertEquals("Start OK", body);
    }

    @Test
    void testStop() throws Exception {
        MvcResult mvcResult = mockMvc.perform(httpPOSTRequestBuilder("stop"))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        assertEquals("Stop OK", body);
    }

    @Test
    void testInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(httpGetRequestBuilder("info"))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.startsWith("Started"));
    }

    @Test
    void testStats() throws Exception {
        MvcResult mvcResult = mockMvc.perform(httpGetRequestBuilder("stats"))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.startsWith("Stats"));
    }

    @Test
    void testNoop() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(getUrlTemplate("noop")).contextPath(contextPath))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        assertEquals("Noop", body);
    }

    @Test
    void testActuator() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(contextPath + "/actuator").contextPath(contextPath))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains("_links"));
    }

    private MockHttpServletRequestBuilder httpGetRequestBuilder(String path) {
        MockHttpServletRequestBuilder builder = get(getUrlTemplate(path));
        return configure(builder);
    }

    private MockHttpServletRequestBuilder httpPOSTRequestBuilder(String path) {
        return configure(post(getUrlTemplate(path)));
    }

    private MockHttpServletRequestBuilder configure(MockHttpServletRequestBuilder builder) {
        return builder
                .contextPath(contextPath)
                .header(HttpHeaders.AUTHORIZATION, basicAuth());
    }

    private String getUrlTemplate(String path) {
        return contextPath + PREFIX + path;
    }

    private String basicAuth() {
        String header = "";
        byte[] encodedCredentials = Base64.getEncoder()
                .encode("user:password".getBytes(StandardCharsets.UTF_8));
        header = "Basic " + new String(encodedCredentials, StandardCharsets.UTF_8);
        return header;
    }
}
