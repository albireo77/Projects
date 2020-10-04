package org.dmx.pn.model;

public enum Operation {

    ADD("+") {
        @Override
        public double execute(double d1, double d2) {
            return d1 + d2;
        }
    },
    SUBTRACT("-") {
        @Override
        public double execute(double d1, double d2) {
            return d1 - d2;
        }
    },
    MULTIPLY("*") {
        @Override
        public double execute(double d1, double d2) {
            return d1 * d2;
        }
    },
    DIVIDE("/") {
        @Override
        public double execute(double d1, double d2) {
            return d1 / d2;
        }
    };

    private final String sign;

    Operation(String sign) {
        this.sign = sign;
    }

    public String sign() {
        return sign;
    }

    public static Operation find(String sign) {
        for (Operation operation : values()) {
            if (operation.sign().equals(sign)) {
                return operation;
            }
        }
        return null;
    }

    public abstract double execute(double d1, double d2);
}
