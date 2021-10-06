package com.docsconsole.tutorials.resources;


import com.docsconsole.tutorials.model.Customer;
import com.docsconsole.tutorials.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/restTemplate")
public class RestTemplateClientController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customer")
    public ResponseEntity getCustomers() {
        String customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);

    }
    @GetMapping(value = "/customer/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);

    }
    @GetMapping(value = "/getForEntity/customer/{id}")
    public ResponseEntity getCustomerUsingGetForEntity(@PathVariable Long id) {
        return customerService.getCustomerUsingGetForEntity(id);
    }

    @PostMapping(value = "/postForEntity/customer")
    public ResponseEntity createCustomerUsingPostForEntity(@RequestBody Customer customer) {
        Customer resultedCustomer = customerService.createCustomerUsingPostForEntity(customer);
        return ResponseEntity.created(URI.create("/customer/" + customer.getId())).body(resultedCustomer);
    }
    @PostMapping(value = "/postForObject/customer")
    public ResponseEntity createCustomerUsingPostForObject(@RequestBody Customer customer) {
        Customer resultedCustomer = customerService.createCustomerUsingPostForObject(customer);
        return ResponseEntity.ok(resultedCustomer);
    }
    @PutMapping(value = "/customer")
    public ResponseEntity PutCustomer(@RequestBody Customer customer) {
        customerService.putCustomer(customer);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/exchange/customer")
    public ResponseEntity createCustomerUsingExchange(@RequestBody Customer customer) {
        Customer resultedCustomer = customerService.createCustomerUsingExchange(customer);
        return ResponseEntity.created(URI.create("customer/" + customer.getId())).body(resultedCustomer);
    }
}