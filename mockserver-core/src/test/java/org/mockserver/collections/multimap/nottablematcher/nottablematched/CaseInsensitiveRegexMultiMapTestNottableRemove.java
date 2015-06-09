package org.mockserver.collections.multimap.nottablematcher.nottablematched;

import org.junit.Test;
import org.mockserver.collections.CaseInsensitiveRegexMultiMap;
import org.mockserver.model.NottableString;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.multiMap;
import static org.mockserver.model.NottableString.not;
import static org.mockserver.model.NottableString.string;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveRegexMultiMapTestNottableRemove {

    @Test
    public void shouldRemoveSingleValueEntry() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new NottableString[]{not("keyOne"), string("keyOne_valueOne")},
                new NottableString[]{not("keyTwo"), string("keyTwo_valueOne"), string("keyTwo_valueTwo")},
                new NottableString[]{not("keyThree"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")}
        );

        // when
        assertThat(multiMap.remove("keyT.*"), is(string("keyOne_valueOne")));

        // then
        assertThat(multiMap.size(), is(2));
        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
        assertThat(multiMap.getAll("keyThree"), empty());
    }

    @Test
    public void shouldRemoveMultiValueEntry() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new NottableString[]{not("keyOne"), string("keyOne_valueOne")},
                new NottableString[]{not("keyTwo"), string("keyTwo_valueOne"), string("keyTwo_valueTwo")},
                new NottableString[]{not("keyThree"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")}
        );

        // when
        assertThat(multiMap.remove("keyO.*"), is(string("keyTwo_valueOne")));

        // then
        assertThat(multiMap.size(), is(3));
        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyTwo_valueTwo"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyOne_valueOne"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyOne_valueOne"), string("keyTwo_valueTwo")));
    }

    @Test
    public void shouldRemoveNoMatchingEntry() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new NottableString[]{not("keyOne"), string("keyOne_valueOne")},
                new NottableString[]{not("keyTwo"), string("keyTwo_valueOne"), string("keyTwo_valueTwo")},
                new NottableString[]{not("keyThree"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")}
        );

        // when
        assertThat(multiMap.remove("key.*"), nullValue());

        // then
        assertThat(multiMap.size(), is(3));
        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyTwo_valueOne"), string("keyTwo_valueTwo"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyOne_valueOne"), string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyOne_valueOne"), string("keyTwo_valueOne"), string("keyTwo_valueTwo")));
    }

//    @Test
//    public void shouldRemoveAllSingleValueEntry() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // when
//        multiMap.removeAll(not("keyT.*"));
//
//        // then
//        assertThat(multiMap.size(), is(2));
//        assertThat(multiMap.getAll("keyOne"), empty());
//        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyTwo_valueOne"), string("keyTwo_valueTwo")));
//        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
//    }
//
//    @Test
//    public void shouldRemoveAllMultiValueEntry() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // when
//        multiMap.removeAll(not("keyOne"));
//
//        // then
//        assertThat(multiMap.size(), is(2));
//        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyOne_valueOne")));
//        assertThat(multiMap.getAll("keyTwo"), empty());
//        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
//    }
//
//    @Test
//    public void shouldRemoveAllNoMatchingEntry() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // when
//        multiMap.removeAll(not("key.*"));
//
//        // then
//        assertThat(multiMap.size(), is(3));
//        assertThat(multiMap.getAll("keyOne"), containsInAnyOrder(string("keyOne_valueOne")));
//        assertThat(multiMap.getAll("keyTwo"), containsInAnyOrder(string("keyTwo_valueOne"), string("keyTwo_valueTwo")));
//        assertThat(multiMap.getAll("keyThree"), containsInAnyOrder(string("keyThree_valueOne"), string("keyThree_valueTwo"), string("keyThree_valueThree")));
//    }
}
