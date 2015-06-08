package org.mockserver.client.serialization.model;

import org.mockserver.model.KeyToMultiValue;
import org.mockserver.model.NottableString;

import java.util.List;

/**
 * @author jamesdbloom
 */
public class KeyToMultiValueDTO extends NotDTO {
    private NottableString name;
    private List<NottableString> values;

    protected KeyToMultiValueDTO(KeyToMultiValue keyToMultiValue, Boolean not) {
        super(not);
        name = keyToMultiValue.getName();
        values = keyToMultiValue.getValues();
    }

    protected KeyToMultiValueDTO() {
    }

    public NottableString getName() {
        return name;
    }

    public List<NottableString> getValues() {
        return values;
    }
}
