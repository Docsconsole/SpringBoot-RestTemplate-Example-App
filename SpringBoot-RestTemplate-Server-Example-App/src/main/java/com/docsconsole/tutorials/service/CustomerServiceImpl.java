package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.exception.EntityNotFoundException;
import com.docsconsole.tutorials.model.Customer;
import com.docsconsole.tutorials.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CustomerServiceImpl {

    @Autowired
    CustomerRepository customerRepository;


    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    public List<Customer> getCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;

    }

    public Customer createCustomer(Customer customer) {
        Customer resultedCustomer = customerRepository.save(customer);
        return resultedCustomer;

    }

    public Customer updateCustomer(Customer customer) {
        Customer resultedCustomer = customerRepository.save(customer);
        return resultedCustomer;

    }
    public Customer updateCustomer(Long id, String firstName) {
        Customer resultedCustomer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer does not exist with id: "+id));
        resultedCustomer.setFirstName(firstName);
        customerRepository.save(resultedCustomer);
        return resultedCustomer;

    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer patchCustomer(Customer customer) {
        Customer resultedCustomer = customerRepository.save(customer);
        return resultedCustomer;
    }
}
