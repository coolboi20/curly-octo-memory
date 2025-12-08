import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class AdventOfCodeProblems {

    /*
    L68
    L30
    R48
    L5
    R60
    L55
    L1
    L99
    R14
    L82
     */

    @Test
    void problem1Part1() throws Exception {
        int[] array = new int[100];
        int dial = 50;
        int counter = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

//        System.out.println(Arrays.toString(array));

        String[] instructions = {"L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"};

        var lines = Files.readAllLines(Path.of("src/test/resources/input.txt"));

        for (String instruction : lines) {
            char movement = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            if (movement == 'L') {
                int result = (dial - value) % array.length;
                if (result < 0) {
                    dial = result + 100;
                } else {
                    dial = result;
                }
            } else {
                dial = (dial + value) % array.length;
            }

            if (dial == 0) {
                counter++;
            }

        }

        System.out.println("Password: " + counter);
    }

    @Test
    void problem1Part2() throws IOException {
        int passwordCounter = 0;
        int dialPosition = 50;
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        var lines = Files.readAllLines(Path.of("src/test/resources/input.txt"));

//        for (var instructionAndValue : getPart1InputData()) {
        for (var instructionAndValue : lines) {
            var instruction = instructionAndValue.charAt(0);
            var value = Integer.parseInt(instructionAndValue.substring(1));

            if (instruction == 'L') {
                int remainder = (dialPosition - value) % array.length;
                int dividedValue = (dialPosition - value) / array.length;
                passwordCounter += (dividedValue < 0) ? -1 * dividedValue : dividedValue;
                if (remainder < 0) {
                    passwordCounter++;
                    dialPosition = remainder + array.length;
                } else if (remainder == 0 && dividedValue != 0) {
                    passwordCounter++;
                    dialPosition = remainder;
                } else {
                    dialPosition -= value;
                }
            } else {
                int quotientWithoutDecimal = (dialPosition + value) / 100;
                int remainder = (dialPosition + value) % 100;
                passwordCounter += quotientWithoutDecimal;
                if (remainder == 0 && quotientWithoutDecimal != 0) {
//                    passwordCounter++;
                    dialPosition = remainder;
                } else {
                    dialPosition = remainder;
                }
            }
//            System.out.println("dial: " + dialPosition);
        }
        System.out.println("password: " + passwordCounter);
    }

    @Test
    void freeThinking() {
//        System.out.println(0 % 100); // -20
        System.out.println((95 + 60) % 100); // -20   }
    }

    private String[] getPart1InputData() {
        return new String[]{"L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"};
    }
}
