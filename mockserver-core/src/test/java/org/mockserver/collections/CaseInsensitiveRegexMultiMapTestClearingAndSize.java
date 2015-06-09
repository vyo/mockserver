package org.mockserver.collections;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.multiMap;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveRegexMultiMapTestClearingAndSize {

    @Test
    public void shouldReturnSize() {
        // when
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.size(), is(3));
        assertThat(multiMap.isEmpty(), is(false));
    }

    @Test
    public void shouldReturnSizeWhenEmpty() {
        // when
        CaseInsensitiveRegexMultiMap multiMap = multiMap();

        // then
        assertThat(multiMap.size(), is(0));
        assertThat(multiMap.isEmpty(), is(true));
    }

    @Test
    public void shouldClear() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // when
        multiMap.clear();

        // then
        assertThat(multiMap.size(), is(0));
        assertThat(multiMap.isEmpty(), is(true));
    }
}
