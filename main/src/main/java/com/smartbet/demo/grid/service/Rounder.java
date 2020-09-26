package com.smartbet.demo.grid.service;

public class Rounder {
    public static double roundWith2Decimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
