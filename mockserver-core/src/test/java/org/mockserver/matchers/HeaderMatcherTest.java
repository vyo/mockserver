package org.mockserver.matchers;

import org.junit.Test;
import org.mockserver.model.*;
import org.mockserver.model.Header;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockserver.matchers.NotMatcher.not;

/**
 * @author jamesdbloom
 */
public class HeaderMatcherTest {

    @Test
    public void shouldMatchMatchingHeader() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("header.*", "header.*")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchMatchingHeaderWhenNotApplied() {
        // given
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
        
        // then - not matcher
        assertFalse(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        // and - not header
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                Not.not(new Header("headerTwoName", "headerTwoValue"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        // and - not matcher and not header
        assertTrue(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                Not.not(new Header("headerTwoName", "headerTwoValue"))
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldMatchMatchingHeaderWithNotHeaderAndNormalHeader() {
        // not matching header
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                Not.not(new Header("headerTwoName", "headerTwoValue"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        // not extra header
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue"),
                Not.not(new Header("headerThree", "headerThreeValueOne"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        // not only header
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("headerThree", "headerThreeValueOne"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        // not all headers (but matching)
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("header.*", ".*"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        // not all headers (but not matching name)
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("header.*", "header.*"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("notHeaderOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("notHeaderTwoName", "headerTwoValue")
        ))));

        // not all headers (but not matching value)
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("header.*", "header.*"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "notHeaderOneValueOne", "notHeaderOneValueTwo"),
                new Header("headerTwoName", "notHeaderTwoValue")
        ))));
    }

    @Test
    public void shouldMatchMatchingHeaderWithOnlyHeader() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("headerThree", "headerThreeValueOne"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerThree", "headerThreeValueOne")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));

        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"))
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldMatchMatchingHeaderWithOnlyHeaderForEmptyList() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new ArrayList<KeyToMultiValue>()
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerThree", "headerThreeValueOne")
        ))));

        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerThree", "headerThreeValueOne")
        )).matches(new ArrayList<KeyToMultiValue>()));

        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("headerThree", "headerThreeValueOne"))
        )).matches(new ArrayList<KeyToMultiValue>()));
    }

    @Test
    public void shouldNotMatchMatchingHeaderWithNotHeaderAndNormalHeader() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                Not.not(new Header("headerTwoName", "headerTwoValue")
                ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchMatchingHeaderWithOnlyNotHeader() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("headerTwoName", "headerTwoValue")
                ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchMatchingHeaderWithOnlyNotHeaderForBodyWithSingleHeader() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                Not.not(new Header("headerTwoName", "headerTwoValue")
                ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldMatchNullExpectation() {
        assertTrue(new MultiValueMapMatcher(null).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchNullExpectationWhenNotApplied() {
        assertFalse(not(new MultiValueMapMatcher(null)).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldMatchEmptyExpectation() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap()).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchEmptyExpectationWhenNotApplied() {
        assertFalse(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap())).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchIncorrectHeaderName() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("INCORRECTheaderTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldMatchIncorrectHeaderNameWhenNotApplied() {
        assertTrue(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("INCORRECTheaderTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchIncorrectHeaderValue() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "INCORRECTheaderTwoValue")
        ))));
    }

    @Test
    public void shouldMatchIncorrectHeaderValueWhenNotApplied() {
        assertTrue(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "INCORRECTheaderTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchIncorrectHeaderNameAndValue() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("INCORRECTheaderTwoName", "INCORRECTheaderTwoValue")
        ))));
    }

    @Test
    public void shouldMatchIncorrectHeaderNameAndValueWhenNotApplied() {
        assertTrue(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("INCORRECTheaderTwoName", "INCORRECTheaderTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchNullHeaderValue() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName")
        ))));
    }

    @Test
    public void shouldMatchNullHeaderValueWhenNotApplied() {
        assertTrue(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName")
        ))));
    }

    @Test
    public void shouldMatchNullHeaderValueInExpectation() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))));
    }

    @Test
    public void shouldNotMatchMissingHeader() {
        assertFalse(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        )).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo")
        ))));
    }

    @Test
    public void shouldMatchMissingHeaderWhenNotApplied() {
        assertTrue(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo"),
                new Header("headerTwoName", "headerTwoValue")
        ))).matches(new ArrayList<KeyToMultiValue>(Arrays.asList(
                new Header("headerOneName", "headerOneValueOne", "headerOneValueTwo")
        ))));
    }

    @Test
    public void shouldMatchNullTest() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap()).matches(null));
    }

    @Test
    public void shouldNotMatchNullTestWhenNotApplied() {
        assertFalse(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap())).matches(null));
    }

    @Test
    public void shouldMatchEmptyTest() {
        assertTrue(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap()).matches(new ArrayList<KeyToMultiValue>()));
    }

    @Test
    public void shouldNotMatchEmptyTestWhenNotApplied() {
        assertFalse(not(new MultiValueMapMatcher(KeyToMultiValue.toMultiMap())).matches(new ArrayList<KeyToMultiValue>()));
    }
}
