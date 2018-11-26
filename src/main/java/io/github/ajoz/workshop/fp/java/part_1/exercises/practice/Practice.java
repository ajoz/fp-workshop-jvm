package io.github.ajoz.workshop.fp.java.part_1.exercises.practice;

/*
  -- Practice: Other type of functions --

  part 1:

  Java has access to primitive types, if we would like to have a function that
  returns a boolean then we need a special type for it as Function1<A, boolean>
  is not possible with Java generics.

  Type from A to boolean is called a Predicate, please look at the definition
  below.

  part 2:

  Create a function "foo" that composes two Predicates and returns another
  predicate. The resulting predicate should be based upon the return value
  of the both predicates used.

  Question: Is there a better name than "foo"? What is it?

  part 3:

  Create a function "bar" that composes two Predicates and returns another
  predicate. The resulting predicate should be based upon the return value
  of both predicates used.

  Question: What operation is it? It works differently than "foo"
 */

@FunctionalInterface
interface Predicate<A> {
    boolean test(final A value);

    @SuppressWarnings("unused")
    default Predicate<A> foo(final Predicate<A> other) {
        return (A argA) -> this.test(argA) && other.test(argA);
    }

    @SuppressWarnings("unused")
    default Predicate<A> bar(final Predicate<A> other) {
        return (A argA) -> this.test(argA) || other.test(argA);
    }
}