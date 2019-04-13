package com.services.reservations;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationsServiceApplicationTests {

	@Test
	public void testFindUser() throws URISyntaxException
	{
		String port = "8088";
		String userID = "1";
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + port + "/user/" + userID;
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		//Verify request succeed
		Assert.assertTrue(result.getBody().contains("userID"));
	}

	@Test
	public void testFindHotel() throws URISyntaxException
	{
		String port = "8089";
		String hotelID = "1";
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + port + "/hotels/" + hotelID;
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		//Verify request succeed
		Assert.assertTrue(result.getBody().contains("hotelId"));
	}

	@Test
	public void testFindRoom() throws URISyntaxException
	{
		String port = "8089";
		String roomID = "1";
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + port + "/rooms/" + roomID;
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		//Verify request succeed
		Assert.assertTrue(result.getBody().contains("roomId"));
	}

	@Test
	public void testAddReservation() throws URISyntaxException, IOException {
		String port = "8087";
		String hotelID = "1", userID = "1", roomID = "1";
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + port + "/addReservation?hotelID=" + hotelID + "&userID=" + userID + "&roomID=" + roomID;
		HttpUriRequest request = new HttpPost(baseUrl);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		//Verify request succeed
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
	}
}
