package org.rf.test.ws.bank;

import org.rf.test.ws.MessageError;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetBankRS {
	private MessageError error;
	private String id;
    private String code;
    private String name;
    private Boolean status;
}
