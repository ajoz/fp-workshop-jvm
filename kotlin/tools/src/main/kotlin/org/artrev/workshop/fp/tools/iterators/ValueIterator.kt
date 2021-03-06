package org.artrev.workshop.fp.tools.iterators

import java.util.NoSuchElementException

class ValueIterator<A>(private vararg val values: A) : Iterator<A> {
    private var currentIndex: Int = 0

    override fun hasNext() = currentIndex < values.size

    override fun next(): A {
        if (currentIndex >= values.size) {
            throw NoSuchElementException("This iterator has only: " + values.size + " values!")
        }

        return values[currentIndex++]
    }
}