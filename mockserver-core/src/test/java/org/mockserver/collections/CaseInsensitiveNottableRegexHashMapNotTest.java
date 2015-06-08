package org.mockserver.collections;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.mockserver.model.NottableString;

import java.util.Arrays;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;
import static org.mockserver.model.NottableString.not;
import static org.mockserver.model.NottableString.string;
import static org.mockserver.test.Assert.assertSameEntries;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveNottableRegexHashMapNotTest {
    
    @Test
    public void shouldCheckIfContainsAllItemsInSubMap() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // and - a sub map
        CaseInsensitiveNottableRegexHashMap subMapOne = new CaseInsensitiveNottableRegexHashMap();
        subMapOne.put("key-ABC", "valueTwo");
        subMapOne.put("ABC-key", "valueThree");

        // and - another sub map
        CaseInsensitiveNottableRegexHashMap subMapTwo = new CaseInsensitiveNottableRegexHashMap();
        subMapTwo.put("key", "valueOne");
        subMapTwo.put("ABC-key-ABC", "valueFour");
        
        // then
        assertTrue(hashMap.containsAll(subMapOne));
        assertTrue(hashMap.containsAll(subMapTwo));
    }

    @Test
    public void shouldFailIfDoesNotContainsAllItemsInSubMap() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // and - a sub map
        CaseInsensitiveNottableRegexHashMap subMapOne = new CaseInsensitiveNottableRegexHashMap();
        subMapOne.put("NOT-ABD", "valueTwo");
        subMapOne.put("ABC-key", "valueThree");

        // and - another sub map
        CaseInsensitiveNottableRegexHashMap subMapTwo = new CaseInsensitiveNottableRegexHashMap();
        subMapTwo.put("key", "valueOne");
        subMapTwo.put("ABC-key-ABC", "valueFour");
        subMapTwo.put("ABC-NOT-ABC", "valueFour");

        // then
        assertFalse(hashMap.containsAll(subMapOne));
        assertFalse(hashMap.containsAll(subMapTwo));
    }

    @Test
    public void shouldCheckIfContainsAllItemsInSubMapWithNottedKeys() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // and - a sub map
        CaseInsensitiveNottableRegexHashMap subMapOne = new CaseInsensitiveNottableRegexHashMap();
        subMapOne.put(not("NOT-ABD"), string("valueTwo"));
        subMapOne.put("ABC-key", "valueThree");

        // and - another sub map
        CaseInsensitiveNottableRegexHashMap subMapTwo = new CaseInsensitiveNottableRegexHashMap();
        subMapTwo.put("key", "valueOne");
        subMapTwo.put("ABC-key-ABC", "valueFour");
        subMapTwo.put(not("ABC-NOT-ABC"), string("valueFour"));

        // then
        assertTrue(hashMap.containsAll(subMapOne));
        assertTrue(hashMap.containsAll(subMapTwo));
    }

    @Test
    public void shouldFailIfDoesNotContainsAllItemsInSubMapWithNottedKeys() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // and - a sub map
        CaseInsensitiveNottableRegexHashMap subMapOne = new CaseInsensitiveNottableRegexHashMap();
        subMapOne.put("key-ABC", "valueTwo");
        subMapOne.put(not("ABC-key"), string("valueThree"));

        // and - another sub map
        CaseInsensitiveNottableRegexHashMap subMapTwo = new CaseInsensitiveNottableRegexHashMap();
        subMapTwo.put(not("key"), string("valueOne"));
        subMapTwo.put("ABC-key-ABC", "valueFour");

        // then
        assertFalse(hashMap.containsAll(subMapOne));
        assertFalse(hashMap.containsAll(subMapTwo));
    }

    @Test
    public void shouldCheckIfContainsAllItemsInSubMapWithNottedKeysAndNottedValues() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(string(".*key"), not("valueThree"));
        hashMap.put(string(".*key.*"), not("valueFour"));

        // and - a sub map
        CaseInsensitiveNottableRegexHashMap subMapOne = new CaseInsensitiveNottableRegexHashMap();
        subMapOne.put(not("NOT-ABD"), string("valueTwo"));
        subMapOne.put("ABC-key", "notValueThree");

        // and - another sub map
        CaseInsensitiveNottableRegexHashMap subMapTwo = new CaseInsensitiveNottableRegexHashMap();
        subMapTwo.put("key", "valueOne");
        subMapTwo.put(string("key"), not("notValueOne"));
        subMapTwo.put("ABC-key-ABC", "notValueFour");
        subMapTwo.put(not("ABC-NOT-ABC"), string("notValueFour"));

        // then
        assertTrue(hashMap.containsAll(subMapOne));
        assertTrue(hashMap.containsAll(subMapTwo));
    }

    @Test
    public void shouldFailIfDoesNotContainsAllItemsInSubMapWithNottedKeysAndNottedValues() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // and - a sub map
        CaseInsensitiveNottableRegexHashMap subMapOne = new CaseInsensitiveNottableRegexHashMap();
        subMapOne.put("key-ABC", "valueTwo");
        subMapOne.put(not("ABC-key"), string("valueThree"));

        // and - another sub map
        CaseInsensitiveNottableRegexHashMap subMapTwo = new CaseInsensitiveNottableRegexHashMap();
        subMapTwo.put(not("key"), string("valueOne"));
        subMapTwo.put("ABC-key-ABC", "valueFour");

        // then
        assertFalse(hashMap.containsAll(subMapOne));
        assertFalse(hashMap.containsAll(subMapTwo));
    }

    @Test
    public void shouldCheckIfContainsKeyAndValue() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then
        assertTrue(hashMap.containsKeyValue(string("key-ABC"), string("valueTwo")));
        assertTrue(hashMap.containsKeyValue(string("ABC-key"), string("valueThree")));
        assertTrue(hashMap.containsKeyValue(string("key"), string("valueOne")));
        assertTrue(hashMap.containsKeyValue(string("ABC-key-ABC"), string("valueFour")));

        assertFalse(hashMap.containsKeyValue(string("NOT-ABC"), string("valueTwo")));
        assertFalse(hashMap.containsKeyValue(string("ABC-NOT-ABC"), string("valueFour")));
    }

    @Test
    public void shouldCheckIfContainsKeyAndValueWithNottedKeys() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then - true if matching values multiple but notted with only one of multiple
        assertTrue(hashMap.containsKeyValue(not("key"), string("valueOne")));

        // and  - false if matching values but notted
        assertFalse(hashMap.containsKeyValue(not("key-ABC"), string("valueTwo")));
        assertFalse(hashMap.containsKeyValue(not("ABC-key"), string("valueThree")));
        assertFalse(hashMap.containsKeyValue(not("ABC-key-ABC"), string("valueFour")));

        // and - true if not matching values but notted
        assertTrue(hashMap.containsKeyValue(not("NOT-ABC"), string("valueTwo")));
        assertTrue(hashMap.containsKeyValue(not("ABC-NOT-ABC"), string("valueFour")));
    }

    @Test
    public void shouldCheckIfContainsKeyAndValueWithNottedValues() {
        // given - a map
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then - true if matching values
        assertTrue(hashMap.containsKeyValue(string("key-ABC"), string("valueTwo")));
        assertTrue(hashMap.containsKeyValue(string("ABC-key"), string("valueThree")));
        assertTrue(hashMap.containsKeyValue(string("key"), string("valueOne")));
        assertTrue(hashMap.containsKeyValue(string("ABC-key-ABC"), string("valueFour")));
        assertTrue(hashMap.containsKeyValue(string("key"), string("value.*")));

        // and - true if matching values multiple but notted with only one of multiple
        assertTrue(hashMap.containsKeyValue(string("key-ABC"), not("valueTwo")));
        assertTrue(hashMap.containsKeyValue(string("ABC-key"), not("valueThree")));
        assertTrue(hashMap.containsKeyValue(string("key"), not("valueOne")));

        // and - false if matching values but notted
        assertFalse(hashMap.containsKeyValue(string("ABC-key-ABC"), not("valueFour")));
        assertFalse(hashMap.containsKeyValue(string("key"), not("value.*")));

        // and - false if not matching values
        assertFalse(hashMap.containsKeyValue(string("key-ABC"), string("notValueTwo")));
        assertFalse(hashMap.containsKeyValue(string("ABC-key-ABC"), string("notValueFour")));

        // and - true if not matching values but notted
        assertTrue(hashMap.containsKeyValue(string("key-ABC"), not("notValueTwo")));
        assertTrue(hashMap.containsKeyValue(string("ABC-key-ABC"), not("notValueFour")));
    }

    @Test
    public void shouldFindKeyUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then - if matches one or more keys
        assertTrue(hashMap.containsKey(string("key")));
        assertTrue(hashMap.containsKey("key End"));
        assertTrue(hashMap.containsKey(string("Beginning key")));
        assertTrue(hashMap.containsKey("Beginning key End"));
        assertTrue(hashMap.containsKey(string("KEY")));
        assertTrue(hashMap.containsKey("KEY End"));
        assertTrue(hashMap.containsKey(string("Beginning KEY")));
        assertTrue(hashMap.containsKey("Beginning KEY End"));
        assertFalse(hashMap.containsKey(string("AnotherValue")));
    }

    @Test
    public void shouldNotFindKeyUsingRegexInNottedKey() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // and - true if nottable with some keys but not all
        assertTrue(hashMap.containsKey(not("key End")));
        assertTrue(hashMap.containsKey(not("Beginning key")));
        assertTrue(hashMap.containsKey(not("Beginning key End")));
        assertTrue(hashMap.containsKey(not("KEY End")));
        assertTrue(hashMap.containsKey(not("Beginning KEY")));
        assertTrue(hashMap.containsKey(not("Beginning KEY End")));

        // and - true if matches all keys
        assertTrue(hashMap.containsKey(".*"));

        // and - false if nottable with all keys
        assertFalse(hashMap.containsKey(not("key")));
        assertFalse(hashMap.containsKey(not("KEY")));
        assertFalse(hashMap.containsKey(not(".*")));

        // and - true if not matching values but notted
        assertTrue(hashMap.containsKey(not("AnotherValue")));
    }

    @Test
    public void shouldFindKeyIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("KEY", "valueTwo");
        hashMap.put("Key", "valueThree");
        hashMap.put("OtherKey", "valueFour");

        // then - if matches one or more keys
        assertTrue(hashMap.containsKey(string("key")));
        assertTrue(hashMap.containsKey("KEY"));
        assertTrue(hashMap.containsKey(string("Key")));
        assertTrue(hashMap.containsKey("kEy"));
        assertTrue(hashMap.containsKey(string("keY")));
        assertTrue(hashMap.containsKey("kEY"));
        assertTrue(hashMap.containsKey(string("OTHERKEY")));
        assertTrue(hashMap.containsKey("otherkey"));
        assertFalse(hashMap.containsKey(string("notAKey")));
    }

    @Test
    public void shouldNotFindKeyIgnoringCaseInNottedKey() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("KEY", "valueTwo");
        hashMap.put("Key", "valueThree");
        hashMap.put("OtherKey", "valueFour");

        // then - true if matching values multiple but notted with only one of multiple
        assertTrue(hashMap.containsKey(not("key")));
        assertTrue(hashMap.containsKey(not("KEY")));
        assertTrue(hashMap.containsKey(not("Key")));
        assertTrue(hashMap.containsKey(not("kEy")));
        assertTrue(hashMap.containsKey(not("keY")));
        assertTrue(hashMap.containsKey(not("kEY")));
        assertTrue(hashMap.containsKey(not("OTHERKEY")));
        assertTrue(hashMap.containsKey(not("otherkey")));

        // and - false if matching values but notted
        assertTrue(hashMap.containsKey(".*"));
        assertFalse(hashMap.containsKey(not(".*")));

        // and - true if not matching values but notted
        assertTrue(hashMap.containsKey(not("notAKey")));
    }

    @Test
    public void shouldGetValueUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then
        assertEquals(string("valueOne"), hashMap.get(string("key")));
        assertEquals(string("valueTwo"), hashMap.get(string("key End")));
        assertEquals(string("valueThree"), hashMap.get(string("Beginning key")));
        assertEquals(string("valueFour"), hashMap.get(string("Beginning key End")));
    }

    @Test
    public void shouldNotGetValueUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then
        assertEquals(string("valueOne"), hashMap.get(not("does_not_exist")));
    }

    @Test
    public void shouldGetValueIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("KEY", "valueTwo");
        hashMap.put("Key", "valueThree");
        hashMap.put("OtherKey", "valueFour");

        // then
        assertEquals(string("valueOne"), hashMap.get(string("key")));
        assertEquals(string("valueTwo"), hashMap.get(string("KEY")));
        assertEquals(string("valueThree"), hashMap.get(string("Key")));
        assertEquals(string("valueFour"), hashMap.get(string("OtherKey")));
    }

    @Test
    public void shouldNotGetValueIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("KEY", "valueTwo");
        hashMap.put("Key", "valueThree");
        hashMap.put("OtherKey", "valueFour");

        // then
        assertEquals(string("valueOne"), hashMap.get(not("does_not_exist")));
    }

    @Test
    public void shouldGetAllValuesUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree"), string("valueFour")), hashMap.getAll(string("key")));
        assertSameEntries(Arrays.asList(string("valueTwo"), string("valueFour")), hashMap.getAll(string("key End")));
        assertSameEntries(Arrays.asList(string("valueThree"), string("valueFour")), hashMap.getAll(string("Beginning key")));
        assertSameEntries(Arrays.asList(string("valueFour")), hashMap.getAll(string("Beginning key End")));
    }

    @Test
    public void shouldNotGetAllValuesUsingRegex() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("key.*", "valueTwo");
        hashMap.put(".*key", "valueThree");
        hashMap.put(".*key.*", "valueFour");

        // then
        assertThat(hashMap.getAll(not("key")), empty());
        assertThat(hashMap.getAll(not("key End")), contains(string("valueOne"), string("valueThree")));
        assertThat(hashMap.getAll(not("Beginning key")), contains(string("valueOne"), string("valueTwo")));
        assertThat(hashMap.getAll(not("Beginning key End")), contains(string("valueOne"), string("valueTwo"), string("valueThree")));
    }

    @Test
    public void shouldGetAllValuesIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("KEY", "valueTwo");
        hashMap.put("Key", "valueThree");
        hashMap.put("OtherKey", "valueFour");

        // then
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), hashMap.getAll(string("key")));
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), hashMap.getAll(string("KEY")));
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), hashMap.getAll(string("Key")));
        assertSameEntries(Arrays.asList(string("valueFour")), hashMap.getAll(string("OtherKey")));
    }

    @Test
    public void shouldNotGetAllValuesIgnoringCase() {
        // when
        CaseInsensitiveNottableRegexHashMap hashMap = new CaseInsensitiveNottableRegexHashMap();
        hashMap.put("key", "valueOne");
        hashMap.put("KEY", "valueTwo");
        hashMap.put("Key", "valueThree");
        hashMap.put("OtherKey", "valueFour");

        // then
        assertSameEntries(Arrays.asList(string("valueFour")), hashMap.getAll(not("key")));
        assertSameEntries(Arrays.asList(string("valueFour")), hashMap.getAll(not("KEY")));
        assertSameEntries(Arrays.asList(string("valueFour")), hashMap.getAll(not("Key")));
        assertSameEntries(Arrays.asList(string("valueOne"), string("valueTwo"), string("valueThree")), hashMap.getAll(not("OtherKey")));
    }

    @Test
    public void shouldSupportRemoving() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("one_one"), circularMultiMap.remove(string("one")));
        assertNull(circularMultiMap.remove(string("one")));

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
    public void shouldSupportNotRemoving() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("two"), circularMultiMap.remove(not("one")));
        assertNull(circularMultiMap.remove(not("one")));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("two"));
        assertTrue(circularMultiMap.containsKey("one"));
        // - should have correct values
        assertFalse(circularMultiMap.containsValue("two"));
        assertTrue(circularMultiMap.containsValue("one_one"));
    }

    @Test
    public void shouldSupportRemovingWithRegex() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("one_one"), circularMultiMap.remove(string("o[a-z]{2}")));
        assertNull(circularMultiMap.remove(string("one")));

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
    public void shouldSupportNotRemovingWithRegex() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("two"), circularMultiMap.remove(not("o[a-z]{2}")));
        assertNull(circularMultiMap.remove(not("one")));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("two"));
        assertTrue(circularMultiMap.containsKey("one"));
        // - should have correct values
        assertFalse(circularMultiMap.containsValue("two"));
        assertTrue(circularMultiMap.containsValue("one_one"));
    }

    @Test
    public void shouldSupportRemovingWithCaseInsensitivity() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("one_one"), circularMultiMap.remove(string("ONE")));
        assertEquals(string("two"), circularMultiMap.remove(string("Two")));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("one"));
        assertFalse(circularMultiMap.containsKey("two"));
    }

    @Test
    public void shouldSupportNotRemovingWithCaseInsensitivity() {
        // given
        CaseInsensitiveNottableRegexHashMap circularMultiMap = new CaseInsensitiveNottableRegexHashMap();
        circularMultiMap.put("one", "one_one");
        circularMultiMap.put("two", "two");

        // when
        assertEquals(string("two"), circularMultiMap.remove(not("ONE")));
        assertEquals(string("one_one"), circularMultiMap.remove(not("Two")));

        // then
        // - should have correct keys
        assertFalse(circularMultiMap.containsKey("one"));
        assertFalse(circularMultiMap.containsKey("two"));
    }
}
