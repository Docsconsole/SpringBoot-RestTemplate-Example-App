package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.model.CustomerDetails;
import com.docsconsole.tutorials.model.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerDetailsService {
    @Autowired(required = true)
    public RestTemplate restTemplate;


    //get:Using GetForObject
    public List<CustomerDetails> getCustDetailsUsingGetForObject() {
        return restTemplate.getForObject("http://localhost:8082/restTemplateServer/api/customerDetails", List.class);
    }

    //get:Using GetForObject With Request Params
    public CustomerDetails getCustDetailsUsingGetForObject1(Long id) {
        return restTemplate.getForObject("http://localhost:8082/restTemplateServer/api/customerDetails/" + id, CustomerDetails.class);
    }
    //get:Using GetForObject with Parameters
    public CustomerDetails getCustDetailsGetForObject(Long id) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        return restTemplate.getForObject("http://localhost:8082/restTemplateServer/api/customerDetails/{id}", CustomerDetails.class, requestMap);
    }
    //get:Using GetForEntity with Params
    public ResponseEntity<CustomerDetails> getCustDetailsGetForEntity(Long id) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        return restTemplate.getForEntity("http://localhost:8082/restTemplateServer/api/customerDetails/{id}", CustomerDetails.class, requestMap);
    }
    //post:Using PostForObject
    public CustomerDetails createCustDetailsPostForObject(CustomerDetails customerDetails) {
        return restTemplate.postForObject("http://localhost:8082/restTemplateServer/api/customerDetails", customerDetails, CustomerDetails.class);
    }

    //post:Using PostForObject with Params
    public CustomerDetails createProductAndUpdateCustDetails(Long id, String firstName, ProductDetails productDetails) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        requestMap.put("firstName", firstName);
        return restTemplate.postForObject("http://localhost:8082/restTemplateServer/api/customerDetails/product/{id}/{firstName}", productDetails, CustomerDetails.class, requestMap);
    }

    //post:Using PostForEntity without Params
    public CustomerDetails createCustDetailsPostForEntity(CustomerDetails customerDetails) {
        return restTemplate.postForEntity("http://localhost:8082/restTemplateServer/api/customerDetails", customerDetails, CustomerDetails.class).getBody();
    }
    //put:Using Put
    public void putCustDetails(CustomerDetails customerDetails) {
        restTemplate.put("http://localhost:8082/restTemplateServer/api/customerDetails", customerDetails);
    }
    //delete:Using Delete
    public void deleteCustDetails(Long id) {
        Map<String, Object> map = new HashMap();
        map.put("id", id);
        restTemplate.delete("http://localhost:8082/restTemplateServer/api/customerDetails/{id}", map);
    }

    //get:Using RequestEntity with Params and Headers
    public CustomerDetails getCustomerDetExchangeRequestEntity1(Long id, String firstName) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        URI uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8082)
                .path("restTemplateServer/api/customerDetails/{id}/{firstName}").buildAndExpand(map).toUri();
        RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, uri);
        return restTemplate.exchange(requestEntity, CustomerDetails.class).getBody();
    }

    //get:Using HttpEntity with Params, Headers
    public CustomerDetails getCustDetailsExchangeHttpEntity1(Long id, String firstName) {
        String url = "http://localhost:8082/restTemplateServer/api/customerDetails/{id}/{firstName}";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, CustomerDetails.class, map).getBody();
    }

    //get:Using HttpEntity with Params, Headers and With ParameterizedTypeReference
    public CustomerDetails getCustomerDetailsExchangeHttpEntity2(Long id, String firstName) {
        String url = "http://localhost:8082/restTemplateServer/api/customerDetails/{id}/{firstName}";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<CustomerDetails> typeRef = new ParameterizedTypeReference<>() {};
        return restTemplate.exchange(url, HttpMethod.GET, entity, typeRef, map).getBody();
    }


    //post:Using RequestEntity without Params and Headers
    public CustomerDetails createCustDetailsExchangeRequestEntity(CustomerDetails customerDetails) throws URISyntaxException {
        String url = "http://localhost:8082/restTemplateServer/api/customerDetails";
        RequestEntity requestEntity = new RequestEntity(customerDetails, HttpMethod.POST, new URI(url));
        return restTemplate.exchange(requestEntity, CustomerDetails.class).getBody();
    }

    //post:Using RequestEntity With Parameters and Headers
    public CustomerDetails createCustDetailsExchangeRequestEntity1(ProductDetails productDetails, Long id, String firstName) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        URI uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8082)
                .path("restTemplateServer/api/customerDetails/product/{id}/{firstName}").buildAndExpand(map).toUri();
        RequestEntity requestEntity = new RequestEntity(productDetails, HttpMethod.POST, uri);
        return restTemplate.exchange(requestEntity, CustomerDetails.class).getBody();
    }

    //post:Using RequestEntity With Parameters, Headers and With ParameterizedTypeReference
    public CustomerDetails createCustDetailsExchangeRequestEntity2(ProductDetails productDetails, Long id, String firstName) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        ParameterizedTypeReference<CustomerDetails> typeRef = new ParameterizedTypeReference<>() {};
        URI uri = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8082)
                .path("restTemplateServer/api/customerDetails/product/{id}/{firstName}").buildAndExpand(map).toUri();
        RequestEntity requestEntity = new RequestEntity(productDetails, HttpMethod.POST, uri);
        return restTemplate.exchange( requestEntity, typeRef).getBody();
    }

    //post:Using HttpEntity Without Parameters and Headers
    public CustomerDetails createCustDetailsExchangeHttpEntity1(CustomerDetails customerDetails) {
        return restTemplate.exchange("http://localhost:8082/restTemplateServer/api/customerDetails", HttpMethod.POST, new HttpEntity<>(customerDetails), CustomerDetails.class).getBody();
    }


    //post:Using HttpEntity Without Request Parameters and Headers
    public CustomerDetails createCustDetailsExchangeHttpEntity2(ProductDetails productDetails, Long id, String firstName) {
        String url = "http://localhost:8082/restTemplateServer/api/customerDetails/product/{id}/{firstName}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        HttpEntity<ProductDetails> httpEntity = new HttpEntity<>(productDetails,headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, CustomerDetails.class,map).getBody();
    }
}
