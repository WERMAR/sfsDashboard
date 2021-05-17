package com.youmesh.buildersBoard.util;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtilFactory<T> {

    /**
     * <h3>Description</h3>
     * Generate an Stream of an {@link Iterable}. It use the {@link StreamSupport#stream(Spliterator, boolean)}-Method
     * and generates an non-parallel Stream.
     *
     * @param iterable - {@link Iterable}-Object which convert to an {@link Stream}
     * @param <T>      - Replace by the used Object in the Iterable
     * @return {@link Stream}
     * @see StreamSupport#stream(Spliterator, boolean)
     */
    public static <T> Stream<T> generateStream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
