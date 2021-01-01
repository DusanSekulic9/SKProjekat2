package app.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import app.ServisLetovaApplication;
import app.forms.AvionForm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServisLetovaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Testovi {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	public static HttpHeaders headers = new HttpHeaders();

	@Test
	void napraviAvionTest() {
		AvionForm avionForm = new AvionForm();
		
		avionForm.setKapacitetPutnika(25);
		avionForm.setNaziv("Avion1");

		HttpEntity<AvionForm> entity = new HttpEntity<AvionForm>(avionForm, headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/dodajAvion",
				HttpMethod.POST, entity, String.class);

		//assertEquals("success", response.getBody());
		//assertNotEquals("failed", response.getBody());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}
}
