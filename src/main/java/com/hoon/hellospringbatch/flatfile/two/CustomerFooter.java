package com.hoon.hellospringbatch.flatfile.two;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ConcurrentHashMap;



@Slf4j
@RequiredArgsConstructor
public class CustomerFooter implements FlatFileFooterCallback {
    private final ConcurrentHashMap<String, Integer> aggregateCustomers;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.write("총 고객 수 : " + aggregateCustomers.get("TOTAL_CUSTOMERS"));
        writer.write(System.lineSeparator());
        writer.write("총 나이 : " + aggregateCustomers.get("TOTAL_AGES"));
    }
}
