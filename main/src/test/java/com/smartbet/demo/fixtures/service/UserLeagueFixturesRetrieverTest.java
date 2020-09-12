package com.smartbet.demo.fixtures.service;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Ignore
public class UserLeagueFixturesRetrieverTest {

    @Test
    public void test() {
        LocalDate of = LocalDate.of(2020, 9, 12);
        String a = of.format(DateTimeFormatter.ofPattern("EEEE d MMM", Locale.FRENCH));
        System.out.println(a);
        Assertions.assertThat(a).isNotNull();
    }
}