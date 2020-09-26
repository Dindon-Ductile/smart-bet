package com.smartbet.demo.grid.service;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

public class AdvancedGridsComputerTest {
    private AdvancedGridsComputer advancedGridsComputer;

    @Before
    public void setUp() {
        this.advancedGridsComputer = new AdvancedGridsComputer(new ComboBoosterComputer(), lcmComputer);
    }

    @Test
    public void test() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2541);
        integers.add(923);
        integers.add(6757);
        integers.add(2685);
        integers.add(5237);
        integers.add(5237);
        integers.add(5237);
        integers.add(5221);
        integers.add(3290);
        integers.add(428);
        integers.add(708);
        BigInteger l = this.advancedGridsComputer.computeLcmOfIntegerList(integers);
        System.out.println(l);
    }
}