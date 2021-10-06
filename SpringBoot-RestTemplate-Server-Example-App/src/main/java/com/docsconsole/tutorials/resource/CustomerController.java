package com.docsconsole.tutorials.resource;

import com.docsconsole.tutorials.model.Customer;
import com.docsconsole.tutorials.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customerApp/api")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        Customer customer = customerServiceImpl.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomers() {
        List<Customer> customerList = customerServiceImpl.getCustomers();
        return ResponseEntity.ok(customerList);
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer resultedCustomer = customerServiceImpl.createCustomer(customer);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + customer.getId())).body(resultedCustomer);

    }

    @PutMapping(value = "/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        Customer resultedCustomer = customerServiceImpl.updateCustomer(customer);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + customer.getId())).body(resultedCustomer);
    }
    @PutMapping(value = "/customer/{id}/{firstName}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,@PathVariable String firstName) {
        Customer resultedCustomer = customerServiceImpl.updateCustomer(id,firstName);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + id)).body(resultedCustomer);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerServiceImpl.deleteCustomer(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping(value = "/customer")
    public ResponseEntity<?> patchCustomer(@RequestBody Customer customer) {
        Customer resultedCustomer = customerServiceImpl.patchCustomer(customer);
        return ResponseEntity.ok(resultedCustomer);
    }

}
