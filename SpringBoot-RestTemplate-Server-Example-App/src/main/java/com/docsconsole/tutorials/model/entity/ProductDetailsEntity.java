package com.docsconsole.tutorials.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "PRODUCT_DETAILS",
        uniqueConstraints = { @UniqueConstraint(columnNames = "PRODUCT_ID") })
@Getter
@Setter
public class ProductDetailsEntity
{

    public ProductDetailsEntity(){
        super();
    }


    public ProductDetailsEntity(String productName, String productVendorName, CustomerDetailsEntity customerDetailsEntity){
        this.productName = productName;
        this.productVendorName =  productVendorName;
        this.customerDetailsEntity = customerDetailsEntity;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID",nullable = false)
    private CustomerDetailsEntity customerDetailsEntity;

    @NotBlank
    @Column(name = "PRODUCT_VENDOR_NAME")
    private String productVendorName;

}
