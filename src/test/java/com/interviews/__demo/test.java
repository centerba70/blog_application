package com.interviews.__demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class test {

    @Test
    public  void moveZerosToEnd() {
        int[] nums = {0, 1, 0, 3, 12};
        if (nums != null && nums.length > 0) {
            int zeroCounter = 0;
            for (int i=0; i<nums.length; i++) {
                if (nums[i] == 0) {
                    zeroCounter++;
                    int counter = i;
                    while (counter+1 <= nums.length - -1) {
                        if (nums[counter + 1] != 0) {
                            nums[i] = nums[counter+1];
                            break;
                        } else {
                            counter++;
                        }
                    }
                }
            }
            int var = nums.length - 1;
            while(zeroCounter > 0) {
                nums[var] = 0;
                zeroCounter--;
                var--;
            }
        }
    }
}
