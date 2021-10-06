package com.docsconsole.tutorials.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ID")
        })
@Getter
@Setter
public class Customer {

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

}
