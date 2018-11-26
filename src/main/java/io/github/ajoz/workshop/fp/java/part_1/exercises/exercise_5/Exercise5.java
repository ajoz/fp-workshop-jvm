package io.github.ajoz.workshop.fp.java.part_1.exercises.exercise_5;

/*
  -- Other types of Functions --

  part 1:

  Java 8 introduced few additional "function" like types due to its peculiar
  type system.
  - Supplier<T> it replaces the need to have Function<Void, T>
  - Consumer<T> it replaces the need to have Function<T, Void>

  One can treat them as handy "aliases". In most cases they are used to model
  some impure effects like reading from a network resource or writing to a database.

  A pure referential transparent function from Void to T would need to return
  the same result when called each time and it would not be very interesting.

  A pure referential transparent function from T to Void is even less interesting
  as there is only value a Void type can take which is null.

  part 2:

  Create a function for composing a Consumer1 and a Function1

  part 3:

  Create a function for composing a Supplier with a Function1

  part 4:

  Create a function for partially applying the first argument of a curried
  function with the use of a Supplier.

 */

@FunctionalInterface
interface Function1<A, B> {
    B apply(A a);
}

@FunctionalInterface
interface Consumer1<A> {
    void accept(A a);
}

@FunctionalInterface
interface Supplier<A> {
    A get();
}

@SuppressWarnings("unused")
class Exercise5 {
    static <A, B> Consumer1<A> composeConsumer(final Function1<A, B> function,
                                               final Consumer1<B> consumer) {
        return (A argA) -> consumer.accept(function.apply(argA));
    }

    static <A, B> Supplier<B> composeSupplier(final Function1<A, B> function,
                                              final Supplier<A> supplier) {
        return () -> function.apply(supplier.get());
    }

    static <A, B, C> Function1<B, C> applyFirst(final Function1<A, Function1<B, C>> function,
                                                final Supplier<A> supplier) {
        return (B argB) -> function.apply(supplier.get()).apply(argB);
    }

    static <A, B, C> Function1<A, C> applySecond(final Function1<A, Function1<B, C>> function,
                                                 final Supplier<B> supplier) {
        throw new UnsupportedOperationException("Exercise5 applyCurriedFirst is missing!");
    }
}