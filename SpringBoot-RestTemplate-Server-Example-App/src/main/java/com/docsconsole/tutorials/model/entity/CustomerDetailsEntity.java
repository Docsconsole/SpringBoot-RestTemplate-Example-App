package com.docsconsole.tutorials.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CUSTOMER_DETAILS",
        uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
@Getter
@Setter
public class CustomerDetailsEntity
{
    public CustomerDetailsEntity(){
        super();
    }

    public CustomerDetailsEntity(String firstName, String lastName, String email, Long contactNumber){
        this.firstName = firstName;
        this.lastName =  lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Column(name = "CONTACT_NUM")
    private Long contactNumber;

    @OneToMany(mappedBy = "customerDetailsEntity", cascade = CascadeType.ALL )
    private List<ProductDetailsEntity> productDetailsEntityList =new ArrayList<>();

}
