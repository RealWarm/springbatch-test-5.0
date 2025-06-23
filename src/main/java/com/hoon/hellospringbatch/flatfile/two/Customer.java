package com.hoon.hellospringbatch.flatfile.two;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private String name;
    private int age;
    private String gender;
}