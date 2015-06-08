package org.mockserver.model;

import org.junit.Test;
import org.mockserver.collections.CaseInsensitiveNottableRegexHashMap;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockserver.model.Not.not;
import static org.mockserver.model.NottableString.string;

public class KeyAndValueTest {

    @Test
    public void shouldConvertToHashMap() {
        // given
        Cookie cookie = new Cookie("name", "value");

        // when
        CaseInsensitiveNottableRegexHashMap hashMap = KeyAndValue.toHashMap(cookie);

        // then
        assertThat(hashMap.get(string("name")), is(string("value")));
    }

    @Test
    public void shouldConvertNottedCookieToHashMap() {
        // given
        Cookie nottedCookie = not(new Cookie("name", "value"));

        // when
        CaseInsensitiveNottableRegexHashMap hashMap = KeyAndValue.toHashMap(nottedCookie);

        // then
        assertThat(hashMap.get(NottableString.not("name")), is(NottableString.not("value")));
    }

    @Test
    public void shouldConvertListOfNottedCookieToHashMap() {
        // given
        Cookie firstNottedCookie = not(new Cookie("name_one", "value_one"));
        Cookie secondCookie = new Cookie("name_two", "value_two");

        // when
        CaseInsensitiveNottableRegexHashMap hashMap = KeyAndValue.toHashMap(
                Arrays.asList(
                        firstNottedCookie,
                        secondCookie
                )
        );

        // then
        assertThat(hashMap.get(NottableString.not("name_one")), is(NottableString.not("value_one")));
        assertThat(hashMap.get(string("name_two")), is(string("value_two")));
    }

}