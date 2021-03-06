package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.exception.EntityNotFoundException;
import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import com.docsconsole.tutorials.model.entity.ProductDetailsEntity;
import com.docsconsole.tutorials.model.request.CustomerDetails;
import com.docsconsole.tutorials.model.request.ProductDetails;
import com.docsconsole.tutorials.model.response.CustomerProductDetails;
import com.docsconsole.tutorials.repository.CustomerDetailsRepository;
import com.docsconsole.tutorials.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class CustomerDetailsServiceImpl {

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;


    public CustomerDetails getCustomerDetails(Long id) {
        CustomerDetailsEntity customerDetailsEntity = customerDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CustomerDetailsEntity not found with id: " + id));
        return new CustomerDetails(customerDetailsEntity);

    }
    public CustomerDetails findByIdAndByFirstName(Long id,String firstName) throws ExecutionException, InterruptedException {
        Future<CustomerDetailsEntity> customerDetailsEntityFuture = customerDetailsRepository.findByIdAndByFirstName(id,firstName);
        CustomerDetailsEntity customerDetailsEntity =  customerDetailsEntityFuture.get();
        return new CustomerDetails(customerDetailsEntity);

    }

    public List<CustomerDetails> getCustomerDetails() {
        List<CustomerDetailsEntity> customerDetailsEntityList = customerDetailsRepository.findAll();
        List<CustomerDetails> CustomerDetailList = customerDetailsEntityList.stream()
                .map(ce -> new CustomerDetails(ce,false)).collect(Collectors.toList());
          return CustomerDetailList;
    }

    public CustomerDetails createCustomerDetails(CustomerDetails customerDetails) {

        CustomerDetailsEntity customerDetailsEntity = new CustomerDetailsEntity(customerDetails.getFirstName(), customerDetails.getLastName(), customerDetails.getEmail(), customerDetails.getContactNumber());
        List productDetailsList = customerDetails.getProductDetailList().
                stream().map(p -> new ProductDetailsEntity(p.getProductName(),p.getProductVendorName(), customerDetailsEntity)).collect(Collectors.toList());
        customerDetailsEntity.setProductDetailsEntityList(productDetailsList);
        CustomerDetailsEntity savedEntity = customerDetailsRepository.save(customerDetailsEntity);
        CustomerDetailsEntity customerDetailsEntityResults = customerDetailsRepository.findById(savedEntity.getId()).orElseThrow(() -> new EntityNotFoundException("CustomerDetailsEntity not found with id: " + savedEntity.getId()));
        return new CustomerDetails(customerDetailsEntityResults);


    }
    public CustomerDetails createProductAndUpdateCustomerDetails(Long id, String firstName, ProductDetails productDetails) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CustomerDetailsEntity not found with id: " + id));
        resultedCustomerDetailsEntity.setFirstName(firstName);
        customerDetailsRepository.save(resultedCustomerDetailsEntity);
        ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity(productDetails);
        productDetailsEntity.setCustomerDetailsEntity(resultedCustomerDetailsEntity);
        ProductDetailsEntity resultedProductDetailsEntity = productDetailsRepository.save(productDetailsEntity);
        resultedCustomerDetailsEntity.getProductDetailsEntityList().add(resultedProductDetailsEntity);
        CustomerDetails customerDetails = new CustomerDetails(resultedCustomerDetailsEntity);
        return customerDetails;

    }

    public CustomerDetailsEntity updateCustomer(CustomerDetailsEntity customerDetailsEntity) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsRepository.save(customerDetailsEntity);
        return resultedCustomerDetailsEntity;

    }
    public CustomerDetailsEntity updateCustomer(Long id, String firstName) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer does not exist with id: "+id));
        resultedCustomerDetailsEntity.setFirstName(firstName);
        customerDetailsRepository.save(resultedCustomerDetailsEntity);
        return resultedCustomerDetailsEntity;

    }

    public void deleteCustomer(Long id) {
        customerDetailsRepository.deleteById(id);
    }

    public CustomerDetailsEntity patchCustomer(CustomerDetailsEntity customerDetailsEntity) {
        CustomerDetailsEntity resultedCustomerDetailsEntity = customerDetailsRepository.save(customerDetailsEntity);
        return resultedCustomerDetailsEntity;
    }
}
