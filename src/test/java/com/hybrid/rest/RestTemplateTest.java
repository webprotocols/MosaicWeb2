package com.hybrid.rest;

import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

	public static void main(String[] args) {
		RestTemplate t = new RestTemplate();
		
		String result = t.getForObject("http://localhost:8080", String.class);
		System.out.println(result);

	}

}
