package com.docsconsole.tutorials.model.request;


import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails
{


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long contactNumber;
    private List<ProductDetails> productDetailList;

    public CustomerDetails(Long id, String firstName, String lastName, String email, Long contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }
    public CustomerDetails(CustomerDetailsEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.contactNumber = entity.getContactNumber();
        this.productDetailList = entity.getProductDetailsEntityList().stream().map(e -> new ProductDetails(e.getId(),e.getProductName(),e.getProductVendorName())).collect(Collectors.toList());
    }
    public CustomerDetails(CustomerDetailsEntity entity,boolean isChildFetchRequired) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.contactNumber = entity.getContactNumber();
        //this.productDetailList = entity.getProductDetailsEntityList().stream().map(e -> new ProductDetails(e.getId(),e.getProductName(),e.getProductVendorName())).collect(Collectors.toList());
    }

}
