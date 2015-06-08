package org.mockserver.model;

import org.mockserver.collections.CaseInsensitiveNottableRegexHashMap;

import java.util.Arrays;
import java.util.List;

import static org.mockserver.model.NottableString.string;

/**
 * @author jamesdbloom
 */
public class KeyAndValue extends Not {
    private final NottableString name;
    private final NottableString value;

    public KeyAndValue(String name, String value) {
        this(string(name), string(value));
    }

    public KeyAndValue(NottableString name, NottableString value) {
        this.name = name;
        this.value = value;
    }

    public static CaseInsensitiveNottableRegexHashMap toHashMap(List<? extends KeyAndValue> keyAndValue) {
        CaseInsensitiveNottableRegexHashMap caseInsensitiveRegexHashMap = new CaseInsensitiveNottableRegexHashMap();
        if (keyAndValue != null) {
            for (KeyAndValue keyToMultiValue : keyAndValue) {
                caseInsensitiveRegexHashMap.put(keyToMultiValue.getName(), keyToMultiValue.getValue());
            }
        }
        return caseInsensitiveRegexHashMap;
    }

    public static CaseInsensitiveNottableRegexHashMap toHashMap(KeyAndValue... keyToMultiValues) {
        return toHashMap(Arrays.asList(keyToMultiValues));
    }

    public NottableString getName() {
        return name;
    }

    public NottableString getValue() {
        return value;
    }

}
