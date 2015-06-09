package org.mockserver.collections.multimap.nottable;

import org.junit.Test;
import org.mockserver.collections.CaseInsensitiveRegexMultiMap;
import org.mockserver.model.NottableString;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.multiMap;
import static org.mockserver.model.NottableString.not;
import static org.mockserver.model.NottableString.string;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveRegexMultiMapTestNottableContainsAll {

    @Test
    public void shouldContainAllExactMatchSingleKeyAndSingleValueForNottedKey() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyOne"), string("keyOne_valueOne")}
        )), is(true));
    }

    @Test
    public void shouldContainAllExactMatchSingleKeyAndSingleValueForNottedValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{string("keyOne"), not("notKeyOne_valueOne")}
        )), is(true));
    }

    @Test
    public void shouldContainAllExactMatchSingleKeyAndSingleValueForNottedKeyAndValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyOne"), not("notKeyOne_valueOne")}
        )), is(true));
    }

    @Test
    public void shouldContainAllSubSetSingleKeyAndSingleValueForNottedKey() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyOne"), string("keyOne_valueOne")}
        )), is(true));
    }

    @Test
    public void shouldContainAllSubSetSingleKeyAndSingleValueForNottedValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{string("keyOne"), not("notKeyOne_valueOne")}
        )), is(true));
    }

    @Test
    public void shouldContainAllSubSetSingleKeyAndSingleValueForNottedKeyAndValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyOne"), not("notKeyOne_valueOne")}
        )), is(true));
    }

    @Test
    public void shouldContainAllExactMatchSingleKeyAndMultipleValuesForNottedKey() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyTwo"), string("keyTwo_valueOne"), string("keyTwo_valueTwo")}
        )), is(true));
    }

    @Test
    public void shouldContainAllExactMatchSingleKeyAndMultipleValuesForNottedValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{string("keyTwo"), not("notKeyTwo_valueOne"), string("keyTwo_valueTwo")}
        )), is(true));
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{string("keyTwo"), string("keyTwo_valueOne"), not("notKeyTwo_valueTwo")}
        )), is(true));
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{string("keyTwo"), not("notKeyTwo_valueOne"), not("notKeyTwo_valueTwo")}
        )), is(true));
    }

    @Test
    public void shouldContainAllExactMatchSingleKeyAndMultipleValuesForNottedKeyAndValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyTwo"), string("keyTwo_valueOne"), not("notKeyTwo_valueTwo")}
        )), is(true));
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyTwo"), not("notKeyTwo_valueOne"), string("keyTwo_valueTwo")}
        )), is(true));
        assertThat(multiMap.containsAll(multiMap(
                new NottableString[]{not("notKeyTwo"), not("notKeyTwo_valueOne"), not("notKeyTwo_valueTwo")}
        )), is(true));
    }

//    @Test
//    public void shouldContainAllSubSetSingleKeyAndMultipleValues() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
//        )), is(true));
//    }
//
//    @Test
//    public void shouldContainAllExactMatchMultipleKeyAndMultipleValues() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyOne", "keyOne_valueOne"},
//                new NottableString[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
//        )), is(true));
//    }
//
//    @Test
//    public void shouldContainAllSubSetMultipleKeyAndMultipleValues() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyOne", "keyOne_valueOne"},
//                new NottableString[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
//        )), is(true));
//    }
//
//    @Test
//    public void shouldNotContainAllNotMatchSingleKeySingleEntry() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"notKeyOne", "keyOne_valueOne"}
//        )), is(false));
//    }
//
//    @Test
//    public void shouldNotContainAllNotMatchSingleValueSingleEntry() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyOne", "notKeyOne_valueOne"}
//        )), is(false));
//    }
//
//    @Test
//    public void shouldNotContainAllNotMatchSingleKeyMultipleEntries() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"notKeyOne", "keyOne_valueOne"}
//        )), is(false));
//    }
//
//    @Test
//    public void shouldNotContainAllNotMatchSingleValueMultipleEntries() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyOne", "notKeyOne_valueOne"}
//        )), is(false));
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyTwo", "keyTwo_valueOne", "notKeyTwo_valueTwo"}
//        )), is(false));
//    }
//
//    @Test
//    public void shouldNotContainAllNotMatchMultipleKeysMultipleEntries() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"notKeyOne", "keyOne_valueOne"},
//                new NottableString[]{"notKeyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
//        )), is(false));
//    }
//
//    @Test
//    public void shouldNotContainAllNotMatchMultipleValuesMultipleEntries() {
//        // given
//        CaseInsensitiveRegexMultiMap multiMap = multiMap(
//                new String[]{"keyOne", "keyOne_valueOne"},
//                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
//                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
//        );
//
//        // then
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyOne", "notKeyOne_valueOne"},
//                new NottableString[]{"keyTwo", "keyTwo_valueOne", "notKeyTwo_valueTwo"}
//        )), is(false));
//        assertThat(multiMap.containsAll(multiMap(
//                new NottableString[]{"keyTwo", "notKeyTwo_valueOne", "notKeyTwo_valueTwo"}
//        )), is(false));
//    }

    // nottable matcher
}
