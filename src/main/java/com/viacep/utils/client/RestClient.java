package com.viacep.utils.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class RestClient {

	public WebTarget webTarget(String baseUrl) {
		return client().target(baseUrl);
	}
	
	private Client client() {
		return ClientBuilder.newClient();
	}
}
