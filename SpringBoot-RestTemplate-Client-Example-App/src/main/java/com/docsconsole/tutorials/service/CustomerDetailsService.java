package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.model.CustomerDetails;
import com.docsconsole.tutorials.model.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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


    public CustomerDetails getCustomerDetailsUsingGetForObject(Long id) {
        return restTemplate.getForObject("http://localhost:8082/customerDetailsApp/api/customerDetails/" + id, CustomerDetails.class);
    }

    public CustomerDetails getCustomerDetailsUsingGetForObjectWithURIVars(Long id) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        return restTemplate.getForObject("http://localhost:8082/customerDetailsApp/api/customerDetails/{id}", CustomerDetails.class, requestMap);
    }

    public ResponseEntity<CustomerDetails> getCustomerDetailsUsingGetForEntity(Long id) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        return restTemplate.getForEntity("http://localhost:8082/customerDetailsApp/api/customerDetails/{id}", CustomerDetails.class, requestMap);
    }

    public CustomerDetails createCustomerDetailsUsingPostForObject(CustomerDetails customerDetails) {
        return restTemplate.postForObject("http://localhost:8082/customerDetailsApp/api/customerDetails", customerDetails, CustomerDetails.class);
    }

    public CustomerProductDetails createOrderAndUpdateCustomerDetails(Long customerDetailsId, String customerDetailsFirstName, ProductDetails productDetails) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("customerDetailsId", customerDetailsId);
        requestMap.put("customerDetailsFirstName", customerDetailsFirstName);
        return restTemplate.postForObject("http://localhost:8082/customerDetailsApp/api/customerDetails/product/{customerId}/{customerFirstName}}", productDetails, CustomerProductDetails.class, requestMap);
    }

    public CustomerDetails createCustomerDetailsUsingPostForEntity(CustomerDetails customerDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.postForEntity("http://localhost:8082/customerDetailsApp/api/customerDetails", customerDetails, CustomerDetails.class).getBody();
    }

    public void putCustomerDetails(CustomerDetails customerDetails) {
        restTemplate.put("http://localhost:8082/customerDetailsApp/api/customerDetails", customerDetails);
    }

    public void deleteCustomerDetails(Long id) {
        Map<String, Object> map = new HashMap();
        map.put("id", id);
        restTemplate.delete("http://localhost:8082/customerDetailsApp/api/customerDetails/{id}", map);
    }


    public List<CustomerDetails> getCustomerDetailsUsingExchangeRequestEntity() throws URISyntaxException {
        String url = "http://localhost:8082/customerDetailsApp/api/customerDetails";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, new URI(url));
        return restTemplate.exchange(requestEntity, List.class).getBody();
    }


    public CustomerDetails getCustomerDetailsExchangeHttpEntityURIVars(Long id, String firstName) {
        String url = "http://localhost:8082/customerDetailsApp/api/customerDetails/{id}/{firstName}";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, CustomerDetails.class, map).getBody();
    }

    public CustomerDetails getCustomerDetailsUsingExchangeHttpEntityURIVarsTypeRef(Long id, String firstName) {
        String url = "http://localhost:8082/customerDetailsApp/api/customerDetails/{id}/{firstName}";
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

    public CustomerDetails CreateCustomerDetailsUsingExchangeRequestEntityUriVars(ProductDetails productDetails, Long id, String firstName) throws URISyntaxException {
        String url = "http://localhost:8082/customerDetailsApp/api/customerDetails/{id}/{firstName}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        RequestEntity requestEntity = new RequestEntity(productDetails, HttpMethod.POST, new URI(url));
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, CustomerDetails.class, map).getBody();
    }

    public CustomerDetails getCustomerDetailsUsingExchangeHttpEntityTypeRef(Long id, String firstName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:8082/customerDetailsApp/api/customerDetails/{id}/{firstName}";
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<CustomerDetails> typeRef = new ParameterizedTypeReference<>() {};
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerDetails> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, typeRef, id, firstName);
        return responseEntity.getBody();

    }

    public CustomerDetails CreateCustomerDetailsUsingExchangeRequestEntity(CustomerDetails customerDetails) throws URISyntaxException {
        String url = "http://localhost:8082/customerDetailsApp/api/customerDetails";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity requestEntity = new RequestEntity(customerDetails, HttpMethod.POST, new URI(url));
        return restTemplate.exchange(requestEntity, CustomerDetails.class).getBody();
    }

    public CustomerDetails createCustomerDetailsUsingExchangeWithHttpEntityEntity(CustomerDetails customerDetails) {
        return restTemplate.exchange("http://localhost:8082/customerDetailsApp/api/customerDetails", HttpMethod.GET, new HttpEntity<CustomerDetails>(customerDetails), CustomerDetails.class).getBody();
    }

}
