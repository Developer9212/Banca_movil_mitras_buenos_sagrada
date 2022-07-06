package com.fenoreste.consumo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.fenoreste.entity.Tablas;
import com.fenoreste.service.ITablasService;

@Service
public class ConsumosHTTPServiceImpl{
	
	@Autowired
	ITablasService tablasService;
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	private static final String basePath="http://192.168.99.37:8080/WSDL_TDD_CSN";
	private static final String urlBalance=basePath+"/tarjetas/service/getBalanceQuery/";
	private static final String urlWithdrawal=basePath+"/tarjetas/service/doWithdrawal/";
	private static final String urlLoadBalance=basePath+"/tarjetas/service/loadBalance/";
	


	/*@Override
	public String getBalanceQuery(String idtarjeta) {
		HttpHeaders headers = new HttpHeaders(); 
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user","ws_sanNico");
		params.put("pass","xZ4XTX3");
		params.put("host","");
		params.put("port", "8080");
		params.put("wsdl","");
		params.put("idtarjeta", "");
		
		HttpEntity<Map<String, String>> request = null;
		ResponseEntity<String> result = null;
		try {
			request = new HttpEntity<Map<String, String>>(params, headers);
			result = restTemplate.postForEntity(urlBalance, request, String.class);
			System.out.println(result.getBody());
			return result.getBody();
		} catch (Exception e) {
			System.out.println("mensaje:" + e.getMessage());
			return e.getMessage();
		}
	}
*/

/*	@Override
	public boolean DoWithdrawal(String idtarjeta, Double monto) {
		HttpHeaders headers = new HttpHeaders(); 
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user","ws_sanNico");
		params.put("pass","xZ4XTX3");
		params.put("host","");
		params.put("port", "8080");
		params.put("wsdl","");
		params.put("idtarjeta", idtarjeta);
		
		HttpEntity<Map<String, String>> request = null;
		ResponseEntity<String> result = null;
		try {
			request = new HttpEntity<Map<String, String>>(params, headers);
			result = restTemplate.postForEntity(urlBalance, request, String.class);
			System.out.println(result.getBody());
			return true;//result.getBody();
		} catch (Exception e) {
			System.out.println("mensaje:" + e.getMessage());
			return false;//e.getMessage();
		}
	}
*/

	/*@Override
	public boolean LoadBalance(String idtarjeta, Double monto) {
		HttpHeaders headers = new HttpHeaders(); 
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user","ws_sanNico");
		params.put("pass","xZ4XTX3");
		params.put("host","");
		params.put("port", "8080");
		params.put("wsdl","");
		params.put("idtarjeta", "");
		
		HttpEntity<Map<String, String>> request = null;
		ResponseEntity<String> result = null;
		try {
			request = new HttpEntity<Map<String, String>>(params, headers);
			result = restTemplate.postForEntity(urlBalance, request, String.class);
			System.out.println(result.getBody());
			return false;//result.getBody();
		} catch (Exception e) {
			System.out.println("mensaje:" + e.getMessage());
			return false;//e.getMessage();
		}
	}*/
	
	
	public String sendSmsMitras(String url,String key,String peticion){
		String resultado_conexion = "";	     	      
		 try {		
	            //Invoco la clase de SSL
	            HttpClient httpClient = new SSLClient();
	            HttpPost http_metodo_put = new HttpPost(url);
	            http_metodo_put.addHeader("Content-Type", "application/json");
	            http_metodo_put.addHeader("apiKey",key);
	            StringEntity entityStringJson = new StringEntity(peticion);
	            entityStringJson.setContentType("application/json");
	            
	            http_metodo_put.setEntity(entityStringJson);
	            HttpResponse response = httpClient.execute(http_metodo_put);
	            if (response != null) {
	                HttpEntity response_entity = response.getEntity();
	                if (response_entity != null) {
	                    resultado_conexion = EntityUtils.toString(response_entity, "utf-8");
	                    System.out.println("result:" + resultado_conexion);
	                }
	            }
	        } catch (Exception ex) {
	            System.out.println("Error en el consumo a STP:" + ex.getMessage());
	        }
		return resultado_conexion;
	}
	
}
