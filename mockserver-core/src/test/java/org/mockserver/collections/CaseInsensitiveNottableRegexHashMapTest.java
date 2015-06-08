package org.mockserver.collections;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockserver.model.NottableString.string;
import static org.mockserver.test.Assert.assertSameEntries;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveNottableRegexHashMapTest {

    @Test
    public void shouldFindKeyUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        caseInsensitiveRegexHashMap.put("key", "valueOne");
        caseInsensitiveRegexHashMap.put("key.*", "valueTwo");
        caseInsensitiveRegexHashMap.put(".*key", "valueThree");
        caseInsensitiveRegexHashMap.put(".*key.*", "valueFour");

        // then
        assertTrue(caseInsensitiveRegexHashMap.containsKey("key"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("key End"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("Beginning key"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("Beginning key End"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("KEY"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("KEY End"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("Beginning KEY"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("Beginning KEY End"));
        assertFalse(caseInsensitiveRegexHashMap.containsKey("AnotherValue"));
    }

    @Test
    public void shouldFindKeyIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        caseInsensitiveRegexHashMap.put("key", "valueOne");
        caseInsensitiveRegexHashMap.put("KEY", "valueTwo");
        caseInsensitiveRegexHashMap.put("Key", "valueThree");
        caseInsensitiveRegexHashMap.put("OtherKey", "valueFour");

        // then
        assertTrue(caseInsensitiveRegexHashMap.containsKey("key"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("KEY"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("Key"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("kEy"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("keY"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("kEY"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("OTHERKEY"));
        assertTrue(caseInsensitiveRegexHashMap.containsKey("otherkey"));
        assertFalse(caseInsensitiveRegexHashMap.containsKey("notAKey"));
    }

    @Test
    public void shouldGetValueUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        caseInsensitiveRegexHashMap.put("key", "valueOne");
        caseInsensitiveRegexHashMap.put("key.*", "valueTwo");
        caseInsensitiveRegexHashMap.put(".*key", "valueThree");
        caseInsensitiveRegexHashMap.put(".*key.*", "valueFour");

        // then
        assertEquals(string("valueOne"), caseInsensitiveRegexHashMap.get("key"));
        assertEquals(string("valueTwo"), caseInsensitiveRegexHashMap.get("key End"));
        assertEquals(string("valueThree"), caseInsensitiveRegexHashMap.get("Beginning key"));
        assertEquals(string("valueFour"), caseInsensitiveRegexHashMap.get("Beginning key End"));
    }

    @Test
    public void shouldGetValueIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        caseInsensitiveRegexHashMap.put("key", "valueOne");
        caseInsensitiveRegexHashMap.put("KEY", "valueTwo");
        caseInsensitiveRegexHashMap.put("Key", "valueThree");
        caseInsensitiveRegexHashMap.put("OtherKey", "valueFour");

        // then
        assertEquals(string("valueOne"), caseInsensitiveRegexHashMap.get("key"));
        assertEquals(string("valueTwo"), caseInsensitiveRegexHashMap.get("KEY"));
        assertEquals(string("valueThree"), caseInsensitiveRegexHashMap.get("Key"));
        assertEquals(string("valueFour"), caseInsensitiveRegexHashMap.get("OtherKey"));
    }

    @Test
    public void shouldGetAllValuesUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        caseInsensitiveRegexHashMap.put("key", "valueOne");
        caseInsensitiveRegexHashMap.put("key.*", "valueTwo");
        caseInsensitiveRegexHashMap.put(".*key", "valueThree");
        caseInsensitiveRegexHashMap.put(".*key.*", "valueFour");

        // then
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree"), string("valueFour")), caseInsensitiveRegexHashMap.getAll("key"));
        assertSameEntries(Arrays.asList(string("valueTwo"), string("valueFour")), caseInsensitiveRegexHashMap.getAll("key End"));
        assertSameEntries(Arrays.asList(string("valueThree"), string("valueFour")), caseInsensitiveRegexHashMap.getAll("Beginning key"));
        assertSameEntries(Arrays.asList(string("valueFour")), caseInsensitiveRegexHashMap.getAll("Beginning key End"));
    }

    @Test
    public void shouldGetAllValuesIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        caseInsensitiveRegexHashMap.put("key", "valueOne");
        caseInsensitiveRegexHashMap.put("KEY", "valueTwo");
        caseInsensitiveRegexHashMap.put("Key", "valueThree");
        caseInsensitiveRegexHashMap.put("OtherKey", "valueFour");

        // then
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), caseInsensitiveRegexHashMap.getAll("key"));
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), caseInsensitiveRegexHashMap.getAll("KEY"));
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), caseInsensitiveRegexHashMap.getAll("Key"));
        assertSameEntries(Arrays.asList(string("valueFour")), caseInsensitiveRegexHashMap.getAll("OtherKey"));
    }

    @Test
    public void shouldSupportRemoving() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("one_one"), circularMultiMap.remove("one"));
        assertNull(circularMultiMap.remove("one"));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("one"));
        assertTrue(circularMultiMap.containsKey("two"));
        assertEquals(Sets.newHashSet(string("two")), circularMultiMap.keySet());
        // - should have correct values
        assertFalse(circularMultiMap.containsValue("one_one"));
        assertTrue(circularMultiMap.containsValue("two"));
    }

    @Test
    public void shouldSupportRemovingWithRegex() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("one_one"), circularMultiMap.remove("o[a-z]{2}"));
        assertNull(circularMultiMap.remove("one"));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("one"));
        assertTrue(circularMultiMap.containsKey("two"));
        assertEquals(Sets.newHashSet(string("two")), circularMultiMap.keySet());
        // - should have correct values
        assertFalse(circularMultiMap.containsValue("one_one"));
        assertTrue(circularMultiMap.containsValue("two"));
    }

    @Test
    public void shouldSupportRemovingWithCaseInsensitivity() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("one_one"), circularMultiMap.remove("ONE"));
        assertEquals(string("two"), circularMultiMap.remove("Two"));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("one"));
        assertFalse(circularMultiMap.containsKey("two"));
    }
}
