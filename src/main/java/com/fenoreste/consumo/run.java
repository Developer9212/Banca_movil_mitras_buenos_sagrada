package com.fenoreste.consumo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class run {
	
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String args[]) {
	}
	
	public void prueba_consumo_sms() {
		
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("apiKey","097d24fac1ea2667e8905098b69a8581f1c6d489");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("message","Hola mundo desde Spring boot");
		params.put("numbers","9631562456");
		params.put("country_code","52");
		
		HttpEntity<Map<String, String>> request = null;
		ResponseEntity<String> result = null;
		try {
			request = new HttpEntity<Map<String, String>>(params, headers);
			result = restTemplate.postForEntity("https://api.smsmasivos.com.mx/sms/send", request, String.class);
			System.out.println(result.getBody());
		
		} catch (Exception e) {
			System.out.println("mensaje:" + e.getMessage());
		}
	}
}

	
