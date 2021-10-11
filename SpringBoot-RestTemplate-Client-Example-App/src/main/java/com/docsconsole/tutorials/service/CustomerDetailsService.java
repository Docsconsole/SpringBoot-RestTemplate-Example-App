package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.model.CustomerDetails;
import com.docsconsole.tutorials.model.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerDetailsService {
    @Autowired(required = true)
    public RestTemplate restTemplate;

    public String getCustomerDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:8082/customerDetailsApp/api/customerDetails", HttpMethod.GET, entity, String.class).getBody();
    }
    public CustomerDetails getCustomerDetails(Long id) {
        return restTemplate.getForObject("http://localhost:8082/customerDetailsApp/api/customerDetails/"+id, CustomerDetails.class);
    }
    public ResponseEntity<CustomerDetails> getCustomerDetailsUsingGetForEntity(Long id) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        return restTemplate.getForEntity("http://localhost:8082/customerDetailsApp/api/customerDetails/{id}", CustomerDetails.class,requestMap);
    }

    public CustomerDetails createCustomerDetailsUsingPostForObject(CustomerDetails customerDetails) {
        return restTemplate.postForObject("http://localhost:8082/customerDetailsApp/api/customerDetails", customerDetails, CustomerDetails.class);
    }

    public CustomerProductDetails createOrderAndUpdateCustomerDetails(Long customerDetailsId, String customerDetailsFirstName, ProductDetails productDetails) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("customerDetailsId", customerDetailsId);
        requestMap.put("customerDetailsFirstName", customerDetailsFirstName);
        return restTemplate.postForObject("http://localhost:8082/customerDetailsApp/api/order/{customerId}/{customerFirstName}", productDetails, CustomerProductDetails.class,requestMap);
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
    public CustomerDetails createCustomerDetailsUsingExchange(CustomerDetails customerDetails) {
        return restTemplate.exchange("http://localhost:8082/customerDetailsApp/api/customerDetails", HttpMethod.POST, new HttpEntity<CustomerDetails>(customerDetails), CustomerDetails.class).getBody();
    }
    /*public CustomerDetails getCustomerDetailsUsingExchange() {
        return restTemplate.exchange("http://localhost:8082/customerDetailsApp/api/customerDetails", HttpMethod.GET, CustomerDetails.class).getBody();
    }*/

}
