package org.dmx.pn.utils;

import org.dmx.pn.model.Operation;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Utility class to evaluate expressions in Polish Notation.
 * This implementation is using {@link Operation} enum.
 */
public class PolishNotation2 {

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
            Operation operation = Operation.find(token);
            if (operation != null) {
                if (stack.size() < 2) {
                    return Double.NaN;
                }
                value = operation.execute(stack.pop(), stack.pop());
            } else {
                value = Double.parseDouble(token);
            }
            stack.push(value);
        }
        return stack.size() == 1 ? stack.getFirst() : Double.NaN;
    }
}
