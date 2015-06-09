package org.mockserver.matchers;

import org.junit.Test;
import org.mockserver.collections.CaseInsensitiveRegexHashMap;
import org.mockserver.model.KeyAndValue;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockserver.collections.CaseInsensitiveRegexHashMap.hashMap;

public class HashMapMatcherTest {

    @Test
    public void shouldMatchSingleKeyAndValueForEmptyListMatcher() {
        // given
        HashMapMatcher hashMapMatcher = new HashMapMatcher(hashMap(
                new String[]{}
        ));

        // then
        assertThat(hashMapMatcher.matches(Arrays.asList(
                new KeyAndValue("keyOne", "keyOneValue")
        )), is(true));
    }

    @Test
    public void shouldMatchMultipleKeyAndValueForEmptyListMatcher() {
        // given
        HashMapMatcher hashMapMatcher = new HashMapMatcher(hashMap(
                new String[]{}
        ));

        // then
        assertThat(hashMapMatcher.matches(Arrays.asList(
                new KeyAndValue("keyOne", "keyOneValue"),
                new KeyAndValue("keyTwo", "keyTwoValue"),
                new KeyAndValue("keyThree", "keyThreeValue")
        )), is(true));
    }

    @Test
    public void shouldMatchSingleKeyAndValueForSingleItemMatcher() {
        // given
        HashMapMatcher hashMapMatcher = new HashMapMatcher(hashMap(
                new String[]{"keyOne", "keyOneValue"}
        ));

        // then
        assertThat(hashMapMatcher.matches(Arrays.asList(
                new KeyAndValue("keyOne", "keyOneValue")
        )), is(true));
    }

    @Test
    public void shouldMatchMultipleKeyAndValueForSingleItemMatcher() {
        // given
        HashMapMatcher hashMapMatcher = new HashMapMatcher(hashMap(
                new String[]{"keyOne", "keyOneValue"}
        ));

        // then
        assertThat(hashMapMatcher.matches(Arrays.asList(
                new KeyAndValue("keyOne", "keyOneValue"),
                new KeyAndValue("keyTwo", "keyTwoValue"),
                new KeyAndValue("keyThree", "keyThreeValue")
        )), is(true));
    }

    @Test
    public void shouldMatchMultipleKeyAndValueForMultiItemMatcherButSubSet() {
        // given
        HashMapMatcher hashMapMatcher = new HashMapMatcher(hashMap(
                new String[]{"keyOne", "keyOneValue"},
                new String[]{"keyTwo", "keyTwoValue"}
        ));

        // then
        assertThat(hashMapMatcher.matches(Arrays.asList(
                new KeyAndValue("keyOne", "keyOneValue"),
                new KeyAndValue("keyTwo", "keyTwoValue"),
                new KeyAndValue("keyThree", "keyThreeValue")
        )), is(true));
    }

    @Test
    public void shouldMatchMultipleKeyAndValueForMultiItemMatcherButExactMatch() {
        // given
        HashMapMatcher hashMapMatcher = new HashMapMatcher(hashMap(
                new String[]{"keyOne", "keyOneValue"},
                new String[]{"keyTwo", "keyTwoValue"},
                new String[]{"keyThree", "keyThreeValue"}
        ));

        // then
        assertThat(hashMapMatcher.matches(Arrays.asList(
                new KeyAndValue("keyOne", "keyOneValue"),
                new KeyAndValue("keyTwo", "keyTwoValue"),
                new KeyAndValue("keyThree", "keyThreeValue")
        )), is(true));
    }
}