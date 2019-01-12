package io.github.ajoz.workshop.fp.java.part_1.solutions.exercise_1;

interface IntegerFunction {
    Integer apply(Integer argument);
}

class Exercise1 {
    // y = x + 1
    static final IntegerFunction f1 = x -> x + 1;

    // y = (x + 2)^2
    static final IntegerFunction f2 = x -> (x + 2) * (x + 2);

    // y = -x + 10
    static final IntegerFunction f3 = x -> -x + 10;

    // y = x^2 + 4x + 1
    static final IntegerFunction f4 = x -> x * x + 4 * x + 1;
}