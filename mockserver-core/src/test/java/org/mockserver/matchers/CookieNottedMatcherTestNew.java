package org.mockserver.matchers;

import org.junit.Test;
import org.mockserver.model.Cookie;
import org.mockserver.model.KeyAndValue;
import org.mockserver.model.Not;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jamesdbloom
 */
public class CookieNottedMatcherTestNew {

    @Test
    public void shouldNotMatchSingleNottedCookieMatcherAndSingleMatchingCookie() {
        assertFalse(new HashMapMatcher(KeyAndValue.toHashMap(
                new Cookie("cookieOneName", "cookieOneValue")
        )).matches(new ArrayList<KeyAndValue>(Arrays.asList(
                new Cookie("cookieOneName", "cookieOneValue")
        ))));
    }

    @Test
    public void shouldMatchSingleNottedCookieMatcherAndSingleNoneMatchingCookie() {
        assertTrue(new HashMapMatcher(KeyAndValue.toHashMap(
                new Cookie("cookieOneName", "cookieOneValue")
        )).matches(new ArrayList<KeyAndValue>(Arrays.asList(
                new Cookie("notCookieOneName", "cookieOneValue")
        ))));

        assertTrue(new HashMapMatcher(KeyAndValue.toHashMap(
                Not.not(new Cookie("cookieOneName", "cookieOneValue"))
        )).matches(new ArrayList<KeyAndValue>(Arrays.asList(
                new Cookie("cookieOneName", "notCookieOneValue")
        ))));
    }

    @Test
    public void shouldNotMatchMultipleNottedCookieMatcherAndMultipleMatchingCookies() {
        assertFalse(new HashMapMatcher(KeyAndValue.toHashMap(
                Not.not(new Cookie("cookie.*", "cookie.*"))
        )).matches(new ArrayList<KeyAndValue>(Arrays.asList(
                new Cookie("cookieOneName", "cookieOneValue"),
                new Cookie("cookieTwoName", "cookieTwoValue")
        ))));
    }

}
