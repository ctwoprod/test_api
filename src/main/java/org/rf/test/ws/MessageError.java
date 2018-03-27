package org.rf.test.ws;

import org.rf.test.constant.MessageErrorCode;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MessageError {
	private String code;
	private String message;

	public static MessageError buildGenericError() {
		return new MessageError().setCode(MessageErrorCode.GENERIC_ERROR)
				.setMessage("Some error occured when processing the request");
	}

	public static MessageError buildGenericError(String message) {
		return new MessageError().setCode(MessageErrorCode.GENERIC_ERROR).setMessage(message);
	}

	/* bank */
	public static MessageError buildBankEmpty() {
		return new MessageError().setCode(MessageErrorCode.BANK_NOT_FOUND)
				.setMessage(String.format("Bank List is empty"));
	}

	public static MessageError buildBankNotFound(String bankId) {
		return new MessageError().setCode(MessageErrorCode.BANK_NOT_FOUND)
				.setMessage(String.format("Bank Id %s does not exist", bankId));
	}

	public static MessageError buildBankActivate(String bankId) {
		return new MessageError().setCode(MessageErrorCode.BANK_FAILED_STATUS)
				.setMessage(String.format("Bank Id %s failed to activate", bankId));
	}

	public static MessageError buildBankDeactivate(String bankId) {
		return new MessageError().setCode(MessageErrorCode.BANK_FAILED_STATUS)
				.setMessage(String.format("Bank Id %s failed to deactivate", bankId));
	}
}
