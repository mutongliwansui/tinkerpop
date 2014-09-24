package com.tinkerpop.gremlin.groovy.function;

import groovy.lang.Closure;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class GBiConsumer<A, B> implements java.util.function.BiConsumer<A, B>, java.io.Serializable {

    private final Closure closure;

    public GBiConsumer(final Closure closure) {
        this.closure = closure;
    }

    @Override
    public void accept(A a, B b) {
        this.closure.call(a, b);
    }

    public static GBiConsumer[] make(final Closure... closures) {
        final GBiConsumer[] functions = new GBiConsumer[closures.length];
        for (int i = 0; i < closures.length; i++) {
            // TODO: Should we do this?
            // closures[i].dehydrate();
            functions[i] = new GBiConsumer(closures[i]);
        }
        return functions;
    }
}
