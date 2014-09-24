package com.tinkerpop.gremlin.process.graph.step.filter;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.Traverser;
import com.tinkerpop.gremlin.process.graph.marker.Reversible;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class DedupStep<S> extends FilterStep<S> implements Reversible {

    public boolean hasUniqueFunction;

    public DedupStep(final Traversal traversal, final Function<Traverser<S>, ?> uniqueFunction) {
        super(traversal);
        final Set<Object> set = new LinkedHashSet<>();
        if (null == uniqueFunction) {
            this.hasUniqueFunction = false;
            this.setPredicate(traverser -> set.add(traverser.get()));
        } else {
            this.hasUniqueFunction = true;
            this.setPredicate(traverser -> set.add(uniqueFunction.apply(traverser)));
        }
    }

    public DedupStep(final Traversal traversal) {
        this(traversal, null);
    }
}
