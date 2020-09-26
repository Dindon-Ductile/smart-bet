package com.smartbet.demo.grid.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ComboBoosterComputerTest {
    private ComboBoosterComputer comboBoosterComputer;

    @Before
    public void setUp() {
        this.comboBoosterComputer = new ComboBoosterComputer();
    }

    @Test
    public void shouldComputeComboBoosterFor3Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(3);
        assertThat(comboBooster).isEqualTo(0);
    }

    @Test
    public void shouldComputeComboBoosterFor4Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(4);
        assertThat(comboBooster).isEqualTo(2.5);
    }

    @Test
    public void shouldComputeComboBoosterFor7Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(7);
        assertThat(comboBooster).isEqualTo(10);
    }

    @Test
    public void shouldComputeComboBoosterFor9Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(9);
        assertThat(comboBooster).isEqualTo(15);
    }

    @Test
    public void shouldComputeComboBoosterFor10Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(10);
        assertThat(comboBooster).isEqualTo(20);
    }

    @Test
    public void shouldComputeComboBoosterFor12Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(12);
        assertThat(comboBooster).isEqualTo(30);
    }

    @Test
    public void shouldComputeComboBoosterFor14Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(14);
        assertThat(comboBooster).isEqualTo(40);
    }

    @Test
    public void shouldComputeComboBoosterFor15Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(15);
        assertThat(comboBooster).isEqualTo(50);
    }

    @Test
    public void shouldComputeComboBoosterFor18Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(18);
        assertThat(comboBooster).isEqualTo(80);
    }

    @Test
    public void shouldComputeComboBoosterFor22Entries() {
        double comboBooster = comboBoosterComputer.computePercentage(22);
        assertThat(comboBooster).isEqualTo(100);
    }
}