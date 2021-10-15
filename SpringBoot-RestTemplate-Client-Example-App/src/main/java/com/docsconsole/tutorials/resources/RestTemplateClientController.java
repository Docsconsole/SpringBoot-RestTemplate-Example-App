package com.docsconsole.tutorials.resources;


import com.docsconsole.tutorials.model.CustomerDetails;
import com.docsconsole.tutorials.model.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import com.docsconsole.tutorials.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/restTemplate")
public class RestTemplateClientController {

    @Autowired
    CustomerDetailsService CustomerDetailsService;

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
    public ResponseEntity createOrderAndUpdateCustomerDetails(@PathVariable Long customerDetailsId, @PathVariable String customerDetailsFirstName, @RequestBody ProductDetails productDetails) {
        CustomerProductDetails resultedCustomerDetails = CustomerDetailsService.createOrderAndUpdateCustomerDetails(customerDetailsId, customerDetailsFirstName, productDetails);
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

    @GetMapping(value = "/exchange/requestEntity/customerDetails")
    public ResponseEntity getCustomerDetailsUsingExchangeRequestEntity() throws URISyntaxException {
        List<CustomerDetails> customerDetailList = CustomerDetailsService.getCustomerDetailsUsingExchangeRequestEntity();
        return ResponseEntity.ok(customerDetailList);
    }

    @GetMapping(value = "/exchange/httpEntity/uriVars/customerDetails/{id}/{firstName}")
    public ResponseEntity getCustomerDetailsExchangeHttpEntityURIVars(@PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.getCustomerDetailsExchangeHttpEntityURIVars(id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping(value = "/exchange/httpEntity/uriVars/typeRef/customerDetails/{id}/{firstName}")
    public ResponseEntity getCustomerDetailsUsingExchangeHttpEntityURIVarsTypeRef(@PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.getCustomerDetailsUsingExchangeHttpEntityURIVarsTypeRef(id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping(value = "/exchange/requestEntity/customerDetails/{id}/{firstName}")
    public ResponseEntity getCustomerDetailsUsingExchangeHttpEntityTypeRef(@PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.getCustomerDetailsUsingExchangeHttpEntityTypeRef(id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping(value = "/exchange/requestEntity/uriVars/customerDetails/{id}/{firstName}")
    public ResponseEntity CreateCustomerDetailsUsingExchangeRequestEntityUriVars(@RequestBody ProductDetails productDetails, @PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.CreateCustomerDetailsUsingExchangeRequestEntityUriVars(productDetails, id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    @PostMapping(value = "/exchange/httpEntity/customerDetails")
    public ResponseEntity createCustomerDetailsUsingExchange(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustomerDetailsUsingExchangeWithHttpEntityEntity(CustomerDetails);
        return ResponseEntity.created(URI.create("CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }

    @PostMapping(value = "/exchange/customerDetails")
    public ResponseEntity CreateCustomerDetailsUsingExchangeRequestEntity(@RequestBody CustomerDetails CustomerDetails) throws URISyntaxException {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.CreateCustomerDetailsUsingExchangeRequestEntity(CustomerDetails);
        return ResponseEntity.created(URI.create("CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }
}