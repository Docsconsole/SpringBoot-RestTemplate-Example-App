package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired(required = true)
    public RestTemplate restTemplate;

    public String getCustomers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:8082/customerApp/api/customer", HttpMethod.GET, entity, String.class).getBody();
    }
    public Customer getCustomer(Long id) {
        return restTemplate.getForObject("http://localhost:8082/customerApp/api/customer/"+id, Customer.class);
    }
    public ResponseEntity<Customer> getCustomerUsingGetForEntity(Long id) {
        Map<String, Object> requestMap = new HashMap();
        requestMap.put("id", id);
        return restTemplate.getForEntity("http://localhost:8082/customerApp/api/customer/{id}", Customer.class,requestMap);
    }

    public Customer createCustomerUsingPostForObject(Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.postForObject("http://localhost:8082/customerApp/api/customer", customer, Customer.class);
    }
    public Customer createCustomerUsingPostForEntity(Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.postForEntity("http://localhost:8082/customerApp/api/customer", customer, Customer.class).getBody();
    }
    public void putCustomer(Customer customer) {
        restTemplate.put("http://localhost:8082/customerApp/api/customer", customer);
    }

    public void deleteCustomer(Long id) {
        Map<String, Object> map = new HashMap();
        map.put("id", id);
       restTemplate.delete("http://localhost:8082/customerApp/api/customer/{id}", map);
    }
    public Customer createCustomerUsingExchange(Customer customer) {
        return restTemplate.exchange("http://localhost:8082/customerApp/api/customer", HttpMethod.POST, new HttpEntity<Customer>(customer), Customer.class).getBody();
    }

}
