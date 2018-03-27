package org.rf.test.ws.bank;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateBankRS {
    private String bankId;
}
