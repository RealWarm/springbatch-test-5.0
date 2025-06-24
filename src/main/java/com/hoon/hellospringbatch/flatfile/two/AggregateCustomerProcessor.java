package com.hoon.hellospringbatch.flatfile.two;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class AggregateCustomerProcessor implements ItemProcessor<Customer, Customer> {
    private final ConcurrentHashMap<String, Integer> aggregateCustomers;

    @Override
    public Customer process(Customer item) {
        aggregateCustomers.putIfAbsent("TOTAL_CUSTOMERS", 0);
        aggregateCustomers.putIfAbsent("TOTAL_AGES", 0);
        aggregateCustomers.put("TOTAL_CUSTOMERS", aggregateCustomers.get("TOTAL_CUSTOMERS") + 1);
        aggregateCustomers.put("TOTAL_AGES", aggregateCustomers.get("TOTAL_AGES") + item.getAge());
        return item;
    }
}