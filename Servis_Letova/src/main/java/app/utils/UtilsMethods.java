package app.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UtilsMethods {

	public static ResponseEntity<Boolean> sendGet(String url, String token) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization", token);

		HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);

		ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);

		return response;
	}

	public static ResponseEntity<Integer> sendPost(String url, Object body) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.POST, entity, Integer.class);

		return response;
	}

}