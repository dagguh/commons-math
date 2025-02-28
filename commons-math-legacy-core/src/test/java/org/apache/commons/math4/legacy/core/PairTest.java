/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math4.legacy.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link Pair}.
 */
public class PairTest {

    @Test
    public void testAccessor() {
        final Pair<Integer, Double> p
            = new Pair<>(new Integer(1), new Double(2));
        Assert.assertEquals(new Integer(1), p.getKey());
        Assert.assertEquals(2, p.getValue().doubleValue(), Math.ulp(1d));
    }

    @Test
    public void testAccessor2() {
        final Pair<Integer, Double> p
            = new Pair<>(new Integer(1), new Double(2));

        // Check that both APIs refer to the same data.

        Assert.assertSame(p.getFirst(), p.getKey());
        Assert.assertSame(p.getSecond(), p.getValue());
    }

    @Test
    public void testEquals() {
        Pair<Integer, Double> p1 = new Pair<>(null, null);
        Assert.assertNotEquals(p1, null);

        Pair<Integer, Double> p2 = new Pair<>(null, null);
        Assert.assertEquals(p1, p2);

        p1 = new Pair<>(new Integer(1), new Double(2));
        Assert.assertNotEquals(p1, p2);

        p2 = new Pair<>(new Integer(1), new Double(2));
        Assert.assertEquals(p1, p2);

        Pair<Integer, Float> p3 = new Pair<>(new Integer(1), new Float(2));
        Assert.assertNotEquals(p1, p3);
    }

    @Test
    public void testHashCode() {
        final MyInteger m1 = new MyInteger(1);
        final MyInteger m2 = new MyInteger(1);

        final Pair<MyInteger, MyInteger> p1 = new Pair<>(m1, m1);
        final Pair<MyInteger, MyInteger> p2 = new Pair<>(m2, m2);
        // Same contents, same hash code.
        Assert.assertEquals(p1.hashCode(), p2.hashCode());

        // Different contents, different hash codes.
        m2.set(2);
        Assert.assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("[null, null]", new Pair<>(null, null).toString());
        Assert.assertEquals("[foo, 3]", new Pair<>("foo", 3).toString());
    }

    @Test
    public void testCreate() {
        final Pair<String, Integer> p1 = Pair.create("foo", 3);
        Assert.assertNotNull(p1);
        final Pair<String, Integer> p2 = new Pair<>("foo", 3);
        Assert.assertEquals(p2, p1);
    }

    /**
     * A mutable integer.
     */
    private static class MyInteger {
        private int i;

        MyInteger(int i) {
            this.i = i;
        }

        public void set(int value) {
            this.i = value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MyInteger)) {
                return false;
            } else {
                return i == ((MyInteger) o).i;
            }
        }

        @Override
        public int hashCode() {
            return i;
        }
    }
}
