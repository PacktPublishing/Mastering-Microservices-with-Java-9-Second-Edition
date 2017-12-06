package com.packtpub.mmj.booking.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class BookingControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    //Required to Generate JSON content from Java objects
    public static final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int port;

    /**
     * Test the GET /v1/booking/{id} API
     */
    @Test
    public void testGetById() {
        //API call
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/booking/1", Map.class);

        assertNotNull(response);

        //Asserting API Response
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("1", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("Booking 1", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
    }

    /**
     * Test the GET /v1/booking/{id} API for no content
     */
    @Test
    public void testGetById_NoContent() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/booking/99", HttpMethod.GET, entity, Map.class);

        assertNotNull(responseE);

        // Should return no content as there is no booking with id 99
        assertEquals(HttpStatus.NO_CONTENT, responseE.getStatusCode());
    }

    /**
     * Test the GET /v1/booking API
     */
    @Test
    public void testGetByName() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("name", "Booking");
        ResponseEntity<Map[]> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/booking/?name={name}", HttpMethod.GET, entity, Map[].class, uriVariables);

        assertNotNull(responseE);

        // Should return no content as there is no booking with id 99
        assertEquals(HttpStatus.OK, responseE.getStatusCode());
        Map<String, Object>[] responses = responseE.getBody();
        assertNotNull(responses);

        // Assumed only single instance exist for booking name contains word "Big"
        assertTrue(responses.length == 2);

        Map<String, Object> response = responses[0];
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("1", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("Booking 1", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
    }

    /**
     * Test the POST /v1/booking API
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testAdd() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "TestBkng 3");
        requestBody.put("id", "3");
        requestBody.put("userId", "3");
        requestBody.put("restaurantId", "1");
        requestBody.put("tableId", "1");
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        requestBody.put("date", nowDate.toString());
        requestBody.put("time", nowTime.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/booking", HttpMethod.POST, entity, Map.class, Collections.EMPTY_MAP);

        assertNotNull(responseE);

        // Should return created (status code 201)
        assertEquals(HttpStatus.CREATED, responseE.getStatusCode());

        //validating the newly created booking using API call
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/booking/3", Map.class);

        assertNotNull(response);

        //Asserting API Response
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("3", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("TestBkng 3", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
        String userId = response.get("userId").toString();
        assertNotNull(userId);
        assertEquals("3", userId);
        String restaurantId = response.get("restaurantId").toString();
        assertNotNull(restaurantId);
        assertEquals("1", restaurantId);
        String tableId = response.get("tableId").toString();
        assertNotNull(tableId);
        assertEquals("1", tableId);
        String date1 = response.get("date").toString();
        assertNotNull(date1);
        String[] arrDate = date1.replace("[", "").replace("]", "").split(",");
        assertEquals(nowDate, LocalDate.of(Integer.parseInt(arrDate[0].trim()),
                Integer.parseInt(arrDate[1].trim()), Integer.parseInt(arrDate[2].trim())));
        String time1 = response.get("time").toString();
        assertNotNull(time1);
        String[] arrTime = time1.replace("[", "").replace("]", "").split(",");
        assertEquals(nowTime, LocalTime.of(Integer.parseInt(arrTime[0].trim()),
                Integer.parseInt(arrTime[1].trim()), Integer.parseInt(arrTime[2].trim()), Integer.parseInt(arrTime[3].trim())));
    }

}
