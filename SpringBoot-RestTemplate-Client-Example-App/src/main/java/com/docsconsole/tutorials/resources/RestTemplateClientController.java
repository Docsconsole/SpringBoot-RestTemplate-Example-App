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
@RequestMapping(value = "/restTemplateClient/customerDetails")
public class RestTemplateClientController {

    @Autowired
    CustomerDetailsService CustomerDetailsService;

    @GetMapping(value = "/all/getForObject")
    public ResponseEntity getCustDetailsUsingGetForObject() {
        List<CustomerDetails> CustomerDetailList = CustomerDetailsService.getCustDetailsUsingGetForObject();
        return ResponseEntity.ok(CustomerDetailList);
    }

    @GetMapping(value = "/getForObject")
    public ResponseEntity getCustDetailsUsingGetForObject1(@RequestParam Long id) {
        CustomerDetails CustomerDetails = CustomerDetailsService.getCustDetailsUsingGetForObject1(id);
        return ResponseEntity.ok(CustomerDetails);
    }

    @GetMapping(value = "/getForObject/{id}")
    public ResponseEntity getCustDetailsGetForObject(@PathVariable Long id) {
        CustomerDetails CustomerDetails = CustomerDetailsService.getCustDetailsGetForObject(id);
        return ResponseEntity.ok(CustomerDetails);
    }

    @GetMapping(value = "/getForEntity/{id}")
    public ResponseEntity getCustDetailsGetForEntity(@PathVariable Long id) {
        return CustomerDetailsService.getCustDetailsGetForEntity(id);
    }

    @PostMapping(value = "/postForEntity")
    public ResponseEntity createCustDetailsPostForEntity(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustDetailsPostForEntity(CustomerDetails);
        return ResponseEntity.created(URI.create("/CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }

    @PostMapping(value = "/postForObject")
    public ResponseEntity createCustDetailsPostForObject(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails customerDetails = CustomerDetailsService.createCustDetailsPostForObject(CustomerDetails);
        return ResponseEntity.ok(customerDetails);
    }

    @PostMapping(value = "/postForObject/{id}/{firstName}")
    public ResponseEntity createProductAndUpdateCustDetails(@PathVariable Long id, @PathVariable String firstName, @RequestBody ProductDetails productDetails) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createProductAndUpdateCustDetails(id, firstName, productDetails);
        return ResponseEntity.ok(resultedCustomerDetails);
    }

    @PutMapping(value = "/put")
    public ResponseEntity putCustDetails(@RequestBody CustomerDetails customerDetails) {
        CustomerDetailsService.putCustDetails(customerDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteCustDetails(@PathVariable Long id) {
        CustomerDetailsService.deleteCustDetails(id);
        return ResponseEntity.noContent().build();
    }
    //get:Using RequestEntity with Params and Headers
    @GetMapping(value = "/exchange/requestEntity1/{id}/{firstName}")
    public ResponseEntity getCustomerDetExchangeRequestEntity1(@PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.getCustomerDetExchangeRequestEntity1(id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    //get:Using HttpEntity with Params, Headers
    @GetMapping(value = "/exchange/httpEntity1/{id}/{firstName}")
    public ResponseEntity getCustDetailsExchangeHttpEntity1(@PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.getCustDetailsExchangeHttpEntity1(id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    //get:Using HttpEntity with Params, Headers and With ParameterizedTypeReference
    @GetMapping(value = "/exchange/httpEntity2/{id}/{firstName}")
    public ResponseEntity getCustomerDetailsExchangeHttpEntity2(@PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.getCustomerDetailsExchangeHttpEntity2(id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    //Using RequestEntity without Params ans Headers
    @PostMapping(value = "/exchange/requestEntity")
    public ResponseEntity CreateCustDetailsExchangeRequestEntity(@RequestBody CustomerDetails CustomerDetails) throws URISyntaxException {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustDetailsExchangeRequestEntity(CustomerDetails);
        return ResponseEntity.created(URI.create("CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }

    //Using RequestEntity With Parameters and Headers
    @PostMapping(value = "/exchange/requestEntity1/{id}/{firstName}")
    public ResponseEntity CreateCustDetailsExchangeRequestEntity1(@RequestBody ProductDetails productDetails, @PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.createCustDetailsExchangeRequestEntity1(productDetails, id, firstName);
        return ResponseEntity.ok(customerDetails);
    }

    //Using RequestEntity With Parameters, Headers and TypeRef
    @PostMapping(value = "/exchange/requestEntity2/{id}/{firstName}")
    public ResponseEntity CreateCustDetailsExchangeRequestEntity2(@RequestBody ProductDetails productDetails, @PathVariable Long id, @PathVariable String firstName) throws URISyntaxException {
        CustomerDetails customerDetails = CustomerDetailsService.createCustDetailsExchangeRequestEntity2(productDetails, id, firstName);
        return ResponseEntity.ok(customerDetails);
    }


    //Using HttpEntity Without Parameters and Headers
    @PostMapping(value = "/exchange/httpEntity1")
    public ResponseEntity CreateCustDetailsExchangeHttpEntity1(@RequestBody CustomerDetails CustomerDetails) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustDetailsExchangeHttpEntity1(CustomerDetails);
        return ResponseEntity.created(URI.create("CustomerDetails/" + CustomerDetails.getId())).body(resultedCustomerDetails);
    }

    //Using HttpEntity With Parameters and Headers
    @PostMapping(value = "/exchange/httpEntity2/{id}/{firstName}")
    public ResponseEntity CreateCustDetailsExchangeHttpEntity2(@RequestBody ProductDetails productDetails, @PathVariable Long id, @PathVariable String firstName) {
        CustomerDetails resultedCustomerDetails = CustomerDetailsService.createCustDetailsExchangeHttpEntity2(productDetails, id, firstName);
        return ResponseEntity.created(URI.create("CustomerDetails/" + resultedCustomerDetails.getId())).body(resultedCustomerDetails);
    }




}