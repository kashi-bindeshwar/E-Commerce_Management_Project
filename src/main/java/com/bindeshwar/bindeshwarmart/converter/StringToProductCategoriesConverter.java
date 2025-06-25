package com.bindeshwar.bindeshwarmart.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bindeshwar.bindeshwarmart.beans.ProductCategories;
import com.bindeshwar.bindeshwarmart.service.ProductCategoryService;

@Component
public class StringToProductCategoriesConverter implements Converter<String, ProductCategories> {

    private final ProductCategoryService productCategoriesService;

    public StringToProductCategoriesConverter(ProductCategoryService productCategoriesService) {
        this.productCategoriesService = productCategoriesService;
    }

    @Override
    public ProductCategories convert(String source) {
        System.out.println("Converting product category ID: " + source);
        try {
            Long id = Long.parseLong(source);
            return productCategoriesService.findById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
