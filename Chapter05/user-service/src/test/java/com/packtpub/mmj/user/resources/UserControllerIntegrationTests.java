package com.packtpub.mmj.user.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.mmj.user.UsersApp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class UserControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    //Required to Generate JSON content from Java objects
    public static final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int port;

    /**
     * Test the GET /v1/user/{id} API
     */
    @Test
    public void testGetById() {
        //API call
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/user/1", Map.class);

        assertNotNull(response);

        //Asserting API Response
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("1", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("User Name 1", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
    }

    /**
     * Test the GET /v1/user/{id} API for no content
     */
    @Test
    public void testGetById_NoContent() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/user/99", HttpMethod.GET, entity, Map.class);

        assertNotNull(responseE);

        // Should return no content as there is no user with id 99
        assertEquals(HttpStatus.NO_CONTENT, responseE.getStatusCode());
    }

    /**
     * Test the GET /v1/user API
     */
    @Test
    public void testGetByName() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("name", "User");
        ResponseEntity<Map[]> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/user/?name={name}", HttpMethod.GET, entity, Map[].class, uriVariables);

        assertNotNull(responseE);

        // Should return no content as there is no user with id 99
        assertEquals(HttpStatus.OK, responseE.getStatusCode());
        Map<String, Object>[] responses = responseE.getBody();
        assertNotNull(responses);

        assertTrue(responses.length == 2);

        Map<String, Object> response = responses[0];
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("1", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("User Name 1", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
    }

    /**
     * Test the POST /v1/user API
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testAdd() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Testusr 3");
        requestBody.put("id", "3");
        requestBody.put("address", "Address for 3rd User");
        requestBody.put("city", "City");
        requestBody.put("phoneNo", "9999933333");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/user", HttpMethod.POST, entity, Map.class, Collections.EMPTY_MAP);

        assertNotNull(responseE);

        // Should return created (status code 201)
        assertEquals(HttpStatus.CREATED, responseE.getStatusCode());

        //validating the newly created user using API call
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/user/3", Map.class);

        assertNotNull(response);

        //Asserting API Response
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("3", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("Testusr 3", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
        String address = response.get("address").toString();
        assertNotNull(address);
        assertEquals("Address for 3rd User", address);
        String city = response.get("city").toString();
        assertNotNull(city);
        assertEquals("City", city);
        String phoneNo = response.get("phoneNo").toString();
        assertNotNull(phoneNo);
        assertEquals("9999933333", phoneNo);
    }

}
