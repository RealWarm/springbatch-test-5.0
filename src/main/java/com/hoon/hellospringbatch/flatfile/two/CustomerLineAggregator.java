package com.hoon.hellospringbatch.flatfile.two;

import org.springframework.batch.item.file.transform.LineAggregator;

public class CustomerLineAggregator implements LineAggregator<Customer> {
    @Override
    public String aggregate(Customer item) {
        return "name: " + item.getName() + ", age: " + item.getAge() + ", sex: " + item.getGender();
    }
}
