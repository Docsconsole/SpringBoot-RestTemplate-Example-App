package com.docsconsole.tutorials.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails
{


    private Long id;
    private String productName;
    private String productVendorName;


}
