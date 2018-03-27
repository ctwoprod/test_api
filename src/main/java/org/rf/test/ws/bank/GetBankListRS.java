package org.rf.test.ws.bank;

import java.util.List;

import org.rf.test.model.Bank;
import org.rf.test.ws.MessageError;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetBankListRS {
	private MessageError error;
	private List<Bank> banks;
}
