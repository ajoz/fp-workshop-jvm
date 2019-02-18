package io.github.ajoz.workshop.fp.part1.exercise4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class Exercise4Test {
    public static class Tuple {
        @Test
        public void should_convert_to_Function1_with_a_tuple() {
            // given:
            final Function2<Integer, Integer, Integer> add = (a, b) -> a + b;

            // when:
            final Function1<Pair<Integer, Integer>, Integer> tested = Exercise4.tuple(add);

            // then:
            assertEquals(add.apply(1, 1), tested.apply(new Pair<>(1, 1)));
            assertEquals(add.apply(0, 0), tested.apply(new Pair<>(0, 0)));
            assertEquals(add.apply(-1, 1), tested.apply(new Pair<>(-1, 1)));
        }

        @Test
        public void should_convert_to_Function2_from_a_Function1_with_a_tuple() {
            // given:
            final Function1<Pair<Integer, Integer>, Integer> add =
                    ab_pair -> ab_pair.first + ab_pair.second;

            // when:
            final Function2<Integer, Integer, Integer> tested = Exercise4.untuple(add);

            // then:
            assertEquals(add.apply(new Pair<>(1, 1)), tested.apply(1, 1));
            assertEquals(add.apply(new Pair<>(0, 0)), tested.apply(0, 0));
            assertEquals(add.apply(new Pair<>(-1, 1)), tested.apply(-1, 1));
        }
    }

    public static class Curry {
        @Test
        public void should_convert_to_Function1_with_a_Function1_return_type() {
            // given:
            final Function2<Integer, Integer, Integer> add = (a, b) -> a + b;

            // when:
            final Function1<Integer, Function1<Integer, Integer>> tested = Exercise4.curry(add);

            // then:
            assertEquals(add.apply(1, 1), tested.apply(1).apply(1));
            assertEquals(add.apply(0, 0), tested.apply(0).apply(0));
            assertEquals(add.apply(-1, 1), tested.apply(-1).apply(1));
        }

        @Test
        public void should_convert_to_Function2_from_a_Function1_with_a_Function1_as_return_type() {
            // given:
            final Function1<Integer, Function1<Integer, Integer>> add =
                    a -> b -> a + b;

            // when:
            final Function2<Integer, Integer, Integer> tested =
                    Exercise4.uncurry(add);

            // then:
            assertEquals(add.apply(1).apply(1), tested.apply(1, 1));
            assertEquals(add.apply(0).apply(0), tested.apply(0, 0));
            assertEquals(add.apply(-1).apply(1), tested.apply(-1, 1));
        }
    }

    public static class Flip {
        @Test
        public void should_flip_Function2() {
            // given:
            final Function2<String, Integer, String> function =
                    (string, integer) -> String.format("%s : %d", string, integer);

            // when:
            final Function2<Integer, String, String> tested = Exercise4.flip(function);

            // then:
            assertEquals(function.apply("foo", 42), tested.apply(42, "foo"));
        }

        @Test
        public void should_flip_Function1_with_a_Tuple_argument() {
            // given:
            final Function1<Pair<String, Integer>, String> function =
                    pair -> String.format("%s : %d", pair.first, pair.second);

            // when:
            final Function1<Pair<Integer, String>, String> tested = Exercise4.flipTupled(function);

            // then:
            assertEquals(function.apply(new Pair<>("foo", 42)), tested.apply(new Pair<>(42, "foo")));
        }

        @Test
        public void should_flip_curried_function() {
            // given:
            final Function1<String, Function1<Integer, String>> function =
                    string -> integer -> String.format("%s : %d", string, integer);

            // when:
            final Function1<Integer, Function1<String, String>> tested = Exercise4.flipCurried(function);

            // then:
            assertEquals(function.apply("foo").apply(42), tested.apply(42).apply("foo"));
        }
    }
}
