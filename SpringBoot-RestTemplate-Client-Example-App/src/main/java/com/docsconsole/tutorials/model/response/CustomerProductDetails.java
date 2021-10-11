package com.docsconsole.tutorials.model.response;

import com.docsconsole.tutorials.model.CustomerDetails;
import com.docsconsole.tutorials.model.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProductDetails {
    private CustomerDetails customerDetails;
    private List<ProductDetails> productDetailList;

}
