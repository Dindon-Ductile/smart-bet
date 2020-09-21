package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

public class AdjustedBetComputerTest {

    @Test
    public void test() {
        double ponderedAverage = (3d + 3d + 2d)/3;
        double average = (3d + 2d)/2;
        OptionalDouble streamAvg = ImmutableList.of(3, 3, 2).stream().mapToDouble(Integer::doubleValue).average();
        System.out.println(ponderedAverage);
        System.out.println(average);
        System.out.println(streamAvg);
    }
}