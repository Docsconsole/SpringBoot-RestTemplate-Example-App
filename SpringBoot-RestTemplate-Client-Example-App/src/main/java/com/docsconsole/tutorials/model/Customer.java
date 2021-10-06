package com.docsconsole.tutorials.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long contactNumber;

}
