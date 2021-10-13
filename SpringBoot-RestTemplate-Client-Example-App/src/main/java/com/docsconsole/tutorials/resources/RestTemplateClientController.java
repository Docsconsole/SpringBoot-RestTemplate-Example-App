package com.docsconsole.tutorials.resources;


import com.docsconsole.tutorials.model.CustomerDetails;
import com.docsconsole.tutorials.model.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import com.docsconsole.tutorials.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/restTemplate")
public class RestTemplateClientController {

    @Autowired
    CustomerDetailsService CustomerDetailsService;

    @GetMapping(value = "/customerDetails")
    public ResponseEntity getCustomerDetailsUsingExchange() {
        String customerDetails = CustomerDetailsService.getCustomerDetailsUsingExchange();
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping(value = "/getForObject/customerDetails")
    public ResponseEntity getCustomerDetailsUsingGetForObject(@RequestParam Long id) {
        CustomerDetails CustomerDetails = CustomerDetailsService.getCustomerDetailsUsingGetForObject(id);
        return ResponseEntity.ok(CustomerDetails);
    }

    @GetMapping(value = "/getForObject/customerDetails/{id}")
    public ResponseEntity getCustomerDetailsUsingGetForObjectWithURIVars(@PathVariable Long id) {
        CustomerDetails CustomerDetails = CustomerDetailsService.getCustomerDetailsUsingGetForObjectWithURIVars(id);
        return ResponseEntity.ok(CustomerDetails);
    }

    @GetMapping(value = "/getForEntity/customerDetails/{id}")
    public ResponseEntity getCustomerDetailsUsingGetForEntity(@PathVariable Long id) {
        return CustomerDetailsService.getCustomerDetailsUsingGetForEntity(id);
    }

    @PostMapping(value = "/postForEntity/customerDetails")
    public ResponseEntity createCustomerDetailsUsingPostForEntity(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustomerDetailsUsingPostForEntity(CustomerDetails);
        return ResponseEntity.created(URI.create("/CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }

    @PostMapping(value = "/postForObject/customerDetails")
    public ResponseEntity createCustomerDetailsUsingPostForObject(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails customerDetails = CustomerDetailsService.createCustomerDetailsUsingPostForObject(CustomerDetails);
        return ResponseEntity.ok(customerDetails);
    }

    @PostMapping(value = "/postForObject/order/{customerDetailsId}/{customerDetailsFirstName}")
    public ResponseEntity createOrderAndUpdateCustomerDetails(@PathVariable Long customerDetailsId,@PathVariable String customerDetailsFirstName,@RequestBody ProductDetails productDetails) {
        CustomerProductDetails resultedCustomerDetails = CustomerDetailsService.createOrderAndUpdateCustomerDetails(customerDetailsId,customerDetailsFirstName,productDetails);
        return ResponseEntity.ok(resultedCustomerDetails);
    }

    @PutMapping(value = "/customerDetails")
    public ResponseEntity PutCustomerDetails(@RequestBody CustomerDetails customerDetails) {
        CustomerDetailsService.putCustomerDetails(customerDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/customerDetails/{id}")
    public ResponseEntity deleteCustomerDetails(@PathVariable Long id) {
        CustomerDetailsService.deleteCustomerDetails(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/exchange/customerDetails")
    public ResponseEntity createCustomerDetailsUsingExchange(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustomerDetailsUsingExchange(CustomerDetails);
        return ResponseEntity.created(URI.create("CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }
}