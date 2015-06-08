package org.mockserver.collections;

import org.mockserver.matchers.RegexStringMatcher;
import org.mockserver.model.NottableString;

import java.util.*;

import static org.mockserver.model.NottableString.string;

/**
 * Map that uses case insensitive regex expression matching for keys and values
 *
 * @author jamesdbloom
 */
public class CaseInsensitiveNottableRegexHashMap extends LinkedHashMap<NottableString, NottableString> implements Map<NottableString, NottableString> {

    public boolean containsAll(CaseInsensitiveNottableRegexHashMap subSet) {
        for (Entry<NottableString, NottableString> entry : subSet.entrySet()) {
            if (!containsKeyValue(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public synchronized boolean containsKeyValue(NottableString key, NottableString value) {
        boolean result = false;

        for (Entry<NottableString, NottableString> matcherEntry : entrySet()) {
            if (RegexStringMatcher.matches(matcherEntry.getValue(), value, true)
                    && RegexStringMatcher.matches(matcherEntry.getKey(), key, true)) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        boolean result = false;

        if (key instanceof NottableString) {
            if (super.containsKey(key)) {
                result = true;
            } else {
                for (NottableString keyToCompare : keySet()) {
                    if (RegexStringMatcher.matches(((NottableString) key), keyToCompare, true)) {
                        result = true;
                        break;
                    }
                }
            }
        } else if (key instanceof String) {
            result = containsKey(string((String) key));
        }

        return result;
    }

    @Override
    public synchronized NottableString get(Object key) {
        if (key instanceof NottableString) {
            if (super.get(key) != null) {
                return super.get(key);
            } else {
                NottableString nottableString = (NottableString) key;
                for (NottableString keyToCompare : keySet()) {
                    if (nottableString.isNot() != RegexStringMatcher.matches(nottableString.getValue(), keyToCompare.getValue(), true)) {
                        return super.get(keyToCompare);
                    }
                }
            }
        } else if (key instanceof String) {
            return get(string((String) key));
        }
        return null;
    }

    public synchronized Collection<NottableString> getAll(Object key) {
        List<NottableString> values = new ArrayList<NottableString>();
        if (key instanceof NottableString) {
            NottableString nottableString = (NottableString) key;
            for (NottableString keyToCompare : keySet()) {
                if (nottableString.isNot() != RegexStringMatcher.matches(nottableString.getValue(), keyToCompare.getValue(), true)) {
                    values.add(super.get(keyToCompare));
                }
            }
        } else if (key instanceof String) {
            return getAll(string((String) key));
        }
        return values;
    }

    public synchronized NottableString put(String key, String value) {
        return super.put(string(key), string(value));
    }

    public synchronized NottableString put(NottableString key, NottableString value) {
        return super.put(key, value);
    }

    @Override
    public synchronized NottableString remove(Object key) {
        if (key instanceof NottableString) {
            if (super.get(key) != null) {
                return super.remove(key);
            } else {
                NottableString nottableString = (NottableString) key;
                for (NottableString keyToCompare : keySet()) {
                    if (nottableString.isNot() != RegexStringMatcher.matches(nottableString.getValue(), keyToCompare.getValue(), true)) {
                        return super.remove(keyToCompare);
                    }
                }
            }
        } else if (key instanceof String) {
            return remove(string((String) key));
        }
        return null;
    }
}
