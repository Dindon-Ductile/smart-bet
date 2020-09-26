package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.grid.domain.BetGrid;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class LcmComputer {

    public BigInteger computeLcmOfIntegerList(List<Integer> element_array) {
        BigInteger lcm_of_array_elements = new BigInteger("1");
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.size(); i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (element_array.get(i) == 0) {
                    return new BigInteger("0");
                } else if (element_array.get(i) < 0) {
                    element_array.set(i, element_array.get(i) * (-1));
                }
                if (element_array.get(i) == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array.get(i) % divisor == 0) {
                    divisible = true;
                    element_array.set(i, element_array.get(i) / divisor);
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements.multiply(new BigInteger(String.valueOf(divisor)));
            } else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == element_array.size()) {
                return lcm_of_array_elements;
            }
        }
    }
}
