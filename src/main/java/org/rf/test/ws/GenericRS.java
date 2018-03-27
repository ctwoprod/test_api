package org.rf.test.ws;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GenericRS {
    private MessageError error;
}
