package org.mockserver.collections;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.multiMap;
import static org.mockserver.model.NottableString.string;
import static org.mockserver.model.NottableString.strings;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveRegexMultiMapTestRemove {

    @Test
    public void shouldRemoveNonMultiValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // when
        multiMap.remove("keyOne");

        // then
        assertThat(multiMap.size(), is(2));
        assertThat(multiMap.getAll("keyOne"), empty());
        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyTwo_valueOne"), string("keyTwo_valueTwo")));
        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
    }

    @Test
    public void shouldRemoveMultiValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // when
        multiMap.remove("keyTwo");

        // then
        assertThat(multiMap.size(), is(3));
        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyOne_valueOne")));
        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyTwo_valueTwo")));
        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
    }

    @Test
    public void shouldRemoveAll() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // when
        multiMap.removeAll("keyTwo");

        // then
        assertThat(multiMap.size(), is(2));
        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyOne_valueOne")));
        assertThat(multiMap.getAll("keyTwo"), empty());
        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
    }
}
