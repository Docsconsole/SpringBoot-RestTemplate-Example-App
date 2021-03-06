package com.docsconsole.tutorials.resource;

import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import com.docsconsole.tutorials.model.entity.ProductDetailsEntity;
import com.docsconsole.tutorials.model.request.CustomerDetails;
import com.docsconsole.tutorials.model.request.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import com.docsconsole.tutorials.service.CustomerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/restTemplateServer/api")
public class CustomerDetailsController {

    @Autowired
    CustomerDetailsServiceImpl customerDetailsServiceImpl;

    @GetMapping("/customerDetails")
    public ResponseEntity<?> getCustomerDetails() {
        List<CustomerDetails> CustomerDetailList = customerDetailsServiceImpl.getCustomerDetails();
        return ResponseEntity.ok(CustomerDetailList);
    }

    @GetMapping("/customerDetails/{id}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable Long id) {
        CustomerDetails customerDetails = customerDetailsServiceImpl.getCustomerDetails(id);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping("/customerDetails/{id}/{firstName}")
    public ResponseEntity<?> getCustomerDetailsByIdAndFirstName(@PathVariable Long id,@PathVariable String firstName) throws ExecutionException, InterruptedException {
        CustomerDetails customerDetails = customerDetailsServiceImpl.findByIdAndByFirstName(id,firstName);
        return ResponseEntity.ok(customerDetails);
    }

    @PostMapping(value = "/customerDetails")
    public ResponseEntity<?> createCustomerDetails(@RequestBody CustomerDetails customerDetails) {
        CustomerDetails customerDetailsResults = customerDetailsServiceImpl.createCustomerDetails(customerDetails);
        return ResponseEntity.created(URI.create("/restTemplateServer/api/customerDetails/" + customerDetails.getId())).body(customerDetailsResults);
    }
    @PostMapping(value = "/customerDetails/product/{id}/{firstName}")
    public ResponseEntity<?> createOrderAndUpdateCustomerDetails(@RequestBody ProductDetails productDetails,@PathVariable Long id,@PathVariable String firstName) {
        CustomerDetails resultedCustomerDetails = customerDetailsServiceImpl.createProductAndUpdateCustomerDetails(id,firstName, productDetails);
        return ResponseEntity.created(URI.create("/restTemplateServer/api/customerDetails/" + resultedCustomerDetails.getId())).body(resultedCustomerDetails);
    }

    /*@GetMapping(value = "/customerDetails/product/{id}/{firstName}")
    public ResponseEntity<?> createProductAndUpdateCustomerDetails(@PathVariable Long id,@PathVariable String firstName,@RequestBody ProductDetails productDetails) {
        CustomerDetails resultedCustomerDetails = customerDetailsServiceImpl.createProductAndUpdateCustomerDetails(id,firstName, productDetails);
        return ResponseEntity.created(URI.create("/restTemplateServer/api/customerDetails/" + resultedCustomerDetails.getId())).body(resultedCustomerDetails);
    }*/

    @PutMapping(value = "/customerDetails")
    public ResponseEntity<?> updateCustomerDetails(@RequestBody CustomerDetailsEntity customerDetailsEntity) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsServiceImpl.updateCustomer(customerDetailsEntity);
        return ResponseEntity.created(URI.create("/restTemplateServer/api/customerDetails/" + customerDetailsEntity.getId())).body(resultedCustomerDetailsEntity);
    }
    @PutMapping(value = "/customerDetails/{id}/{firstName}")
    public ResponseEntity<?> updateCustomerDetails(@PathVariable Long id,@PathVariable String firstName) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsServiceImpl.updateCustomer(id,firstName);
        return ResponseEntity.created(URI.create("/restTemplateServer/api/customerDetails/" + id)).body(resultedCustomerDetailsEntity);
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
