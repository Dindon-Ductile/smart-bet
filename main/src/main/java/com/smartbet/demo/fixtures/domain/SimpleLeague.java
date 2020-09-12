package com.smartbet.demo.fixtures.domain;

import lombok.Data;

@Data
public class SimpleLeague {
    private final int id;
    private final String name;
    private final String country;
    private final String logoUrl;
    private final String flagUrl;
}
