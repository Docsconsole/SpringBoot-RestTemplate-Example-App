package com.docsconsole.tutorials.model.response;

import com.docsconsole.tutorials.model.entity.CustomerDetailsEntity;
import com.docsconsole.tutorials.model.entity.ProductDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProductDetails {
    private CustomerDetailsEntity customerDetailsEntity;
    private ProductDetailsEntity productDetailsEntity;

}
