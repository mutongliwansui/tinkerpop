/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.machine.functions;

import org.apache.tinkerpop.machine.coefficients.Coefficient;
import org.apache.tinkerpop.machine.traversers.Traverser;
import org.apache.tinkerpop.util.StringFactory;

import java.util.Set;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public abstract class AbstractFunction<C, S, E> implements CFunction<C> {

    protected final Coefficient<C> coefficient;
    private Set<String> labels;

    public AbstractFunction(final Coefficient<C> coefficient, final Set<String> labels) {
        this.coefficient = coefficient;
        this.labels = labels;
    }

    @Override
    public Coefficient<C> coefficient() {
        return this.coefficient;
    }

    @Override
    public Set<String> labels() {
        return this.labels;
    }

    protected Traverser<C, E> postProcess(final Traverser<C, E> traverser) {
        traverser.coefficient().multiply(this.coefficient.value());
        for (final String label : this.labels) {
            traverser.addLabel(label);
        }
        return traverser;
    }


    @Override
    public String toString() {
        return StringFactory.makeFunctionString(this);
    }
}
