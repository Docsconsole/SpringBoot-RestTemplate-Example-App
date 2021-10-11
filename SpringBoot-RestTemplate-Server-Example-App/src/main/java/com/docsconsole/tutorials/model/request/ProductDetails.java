package com.docsconsole.tutorials.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails
{


    private Long id;
    private String productName;
    private String productVendorName;


}
