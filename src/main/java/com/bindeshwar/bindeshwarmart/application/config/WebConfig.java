package com.bindeshwar.bindeshwarmart.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bindeshwar.bindeshwarmart.converter.StringToProductCategoriesConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StringToProductCategoriesConverter stringToProductCategoriesConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToProductCategoriesConverter);
    }
}
