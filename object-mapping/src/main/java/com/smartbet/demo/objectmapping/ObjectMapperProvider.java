package com.smartbet.demo.objectmapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.stereotype.Service;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@Service
public class ObjectMapperProvider {
    public ObjectMapper buildObjectMapper() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(new GuavaModule())
                .setSerializationInclusion(NON_ABSENT);
    }
}
