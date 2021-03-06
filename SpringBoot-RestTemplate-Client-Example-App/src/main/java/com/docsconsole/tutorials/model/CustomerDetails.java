package com.docsconsole.tutorials.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long contactNumber;
    private Set<ProductDetails> productDetailList;

}
