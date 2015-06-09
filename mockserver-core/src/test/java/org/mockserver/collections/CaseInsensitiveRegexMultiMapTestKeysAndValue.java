package org.mockserver.collections;

import com.google.common.collect.Sets;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockserver.model.NottableString;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.entry;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.multiMap;
import static org.mockserver.model.NottableString.string;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveRegexMultiMapTestKeysAndValue {

    @Test
    public void shouldReturnKeys() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.keySet(), containsInAnyOrder(string("keyOne"), string("keyTwo")));
    }

    @Test
    public void shouldReturnValues() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.values(), containsInAnyOrder(string("keyOne_valueOne"), string("keyTwo_valueOne"), string("keyTwo_valueTwo")));
    }

    @Test
    public void shouldReturnEntrySet() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertEquals(Sets.newHashSet(
                entry("keyOne", "keyOne_valueOne"),
                entry("keyTwo", "keyTwo_valueOne"),
                entry("keyTwo", "keyTwo_valueTwo")
        ), multiMap.entrySet());
    }
}
