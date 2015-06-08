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
class CaseInsensitiveNottableRegexListHashMap extends LinkedHashMap<NottableString, List<NottableString>> implements Map<NottableString, List<NottableString>> {

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
    public synchronized List<NottableString> get(Object key) {
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

    public synchronized Collection<List<NottableString>> getAll(Object key) {
        List<List<NottableString>> values = new ArrayList<List<NottableString>>();
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

    public synchronized List<NottableString> put(String key, List<NottableString> value) {
        return super.put(string(key), value);
    }

    @Override
    public synchronized List<NottableString> remove(Object key) {
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
