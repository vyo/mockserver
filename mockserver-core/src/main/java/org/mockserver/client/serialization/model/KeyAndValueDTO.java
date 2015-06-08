package org.mockserver.client.serialization.model;

import org.mockserver.model.KeyAndValue;
import org.mockserver.model.NottableString;

import java.util.List;

/**
 * @author jamesdbloom
 */
public class KeyAndValueDTO extends NotDTO {
    private NottableString name;
    private NottableString value;

    protected KeyAndValueDTO(KeyAndValue keyAndValue, Boolean not) {
        super(not);
        name = keyAndValue.getName();
        value = keyAndValue.getValue();
    }

    protected KeyAndValueDTO() {
    }

    public NottableString getName() {
        return name;
    }

    public NottableString getValue() {
        return value;
    }
}
