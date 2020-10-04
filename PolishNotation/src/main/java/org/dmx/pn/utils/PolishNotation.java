package org.dmx.pn.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.DoubleBinaryOperator;

/**
 * Utility class to evaluate expressions in Polish Notation.
 */
public class PolishNotation {

    private static final Map<String, DoubleBinaryOperator> OPERATIONS = Map.of(
            "+", Double::sum,
            "-", (d1, d2) -> d1 - d2,
            "*", (d1, d2) -> d1 * d2,
            "/", (d1, d2) -> d1 / d2);

   /**
     * Evaluate value from tokens following Polish Notation
     *
     * @param tokens    input tokens
     * @return          evaluated value
     */
    public static double evaluate(String[] tokens) {

        Deque<Double> stack = new ArrayDeque<>();

        for (int i = tokens.length-1; i >= 0; i--) {
            double value;
            String token = tokens[i];
            DoubleBinaryOperator operation = OPERATIONS.get(token);
            if (operation != null) {
                if (stack.size() < 2) {
                    return Double.NaN;
                }
                value = operation.applyAsDouble(stack.pop(), stack.pop());
            } else {
                value = Double.parseDouble(token);
            }
            stack.push(value);
        }
        return stack.size() == 1 ? stack.getFirst() : Double.NaN;
    }

    public static void main(String[] args) throws IOException {

        List<String> lines;

        String filePath = System.getProperty("filePath");
        if (filePath != null) {
            System.out.println("Reading Polish Notation expressions from file " + filePath);
            lines = Files.readAllLines(Paths.get(filePath));
        } else {
            System.out.println("Reading Polish Notation expressions from console");
            System.out.println("Enter expressions line by line. To stop enter empty line: ");
            lines = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                lines.add(line);
            }
        }

        System.out.println("Evaluated expressions:");

        for (String line : lines) {
            String[] tokens = line.split(" ");
            double d = evaluate(tokens);
            if (Double.isNaN(d)) {
                System.out.println("error");
            } else {
                System.out.printf(Locale.US, "%.2f%n", d);
            }
        }
    }
}
