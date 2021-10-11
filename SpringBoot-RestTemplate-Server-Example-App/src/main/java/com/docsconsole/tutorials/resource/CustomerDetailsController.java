package com.docsconsole.tutorials.resource;

import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import com.docsconsole.tutorials.model.entity.ProductDetailsEntity;
import com.docsconsole.tutorials.model.request.CustomerDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import com.docsconsole.tutorials.service.CustomerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customerDetailsApp/api")
public class CustomerDetailsController {

    @Autowired
    CustomerDetailsServiceImpl customerDetailsServiceImpl;

    @GetMapping("/customerDetails/{id}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable Long id) {
        CustomerDetails customerDetails = customerDetailsServiceImpl.getCustomerDetails(id);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping("/customerDetails")
    public ResponseEntity<?> getCustomerDetails() {
        List<CustomerDetails> CustomerDetailList = customerDetailsServiceImpl.getCustomerDetails();
        return ResponseEntity.ok(CustomerDetailList);
    }

    @PostMapping(value = "/customerDetails")
    public ResponseEntity<?> createCustomerDetails(@RequestBody CustomerDetails customerDetails) {
        CustomerDetails customerDetailsResults = customerDetailsServiceImpl.createCustomerDetails(customerDetails);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + customerDetails.getId())).body(customerDetailsResults);
    }
    @PostMapping(value = "/order/{customerId}/{customerFirstName}")
    public ResponseEntity<?> createOrderAndUpdateCustomerDetails(@PathVariable Long customerId,@PathVariable String customerFirstName,@RequestBody ProductDetailsEntity productDetailsEntity) {
        CustomerProductDetails resultedCustomerProductDetails = customerDetailsServiceImpl.createOrderAndUpdateCustomerDetails(customerId,customerFirstName, productDetailsEntity);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + resultedCustomerProductDetails.getCustomerDetailsEntity().getId())).body(resultedCustomerProductDetails);
    }

    @PutMapping(value = "/customerDetails")
    public ResponseEntity<?> updateCustomerDetails(@RequestBody CustomerDetailsEntity customerDetailsEntity) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsServiceImpl.updateCustomer(customerDetailsEntity);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + customerDetailsEntity.getId())).body(resultedCustomerDetailsEntity);
    }
    @PutMapping(value = "/customerDetails/{id}/{firstName}")
    public ResponseEntity<?> updateCustomerDetails(@PathVariable Long id,@PathVariable String firstName) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsServiceImpl.updateCustomer(id,firstName);
        return ResponseEntity.created(URI.create("/customer-app/api/customer/" + id)).body(resultedCustomerDetailsEntity);
    }

    @DeleteMapping(value = "/customerDetails/{id}")
    public ResponseEntity<?> deleteCustomerDetails(@PathVariable Long id) {
        customerDetailsServiceImpl.deleteCustomer(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping(value = "/customerDetails")
    public ResponseEntity<?> patchCustomerDetails(@RequestBody CustomerDetailsEntity customerDetailsEntity) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsServiceImpl.patchCustomer(customerDetailsEntity);
        return ResponseEntity.ok(resultedCustomerDetailsEntity);
    }

}
