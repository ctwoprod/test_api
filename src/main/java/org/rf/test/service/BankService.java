package org.rf.test.service;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.rf.test.model.Bank;
import org.rf.test.persistence.bank.BankRepository;
import org.rf.test.ws.GenericRS;
import org.rf.test.ws.MessageError;
import org.rf.test.ws.bank.CreateBankRQ;
import org.rf.test.ws.bank.CreateBankRS;
import org.rf.test.ws.bank.GetBankListRS;
import org.rf.test.ws.bank.GetBankRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService {
	private static final Logger logger = LoggerFactory.getLogger(BankService.class);

	private BankRepository bankRepository;

	@Inject
	public BankService(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	public CreateBankRS create(CreateBankRQ request) {
		String bankId = UUID.randomUUID().toString();
		Bank newBank = buildBank(request, bankId);
		this.bankRepository.insert(newBank);
		return new CreateBankRS().setBankId(bankId);
	}

	public GenericRS update(String id, CreateBankRQ request) {
		GenericRS response = new GenericRS();
		Bank bank = bankRepository.get(id);
		if (bank == null) {
			return response.setError(MessageError.buildBankEmpty());
		}

		if (StringUtils.isNotBlank(request.getName())) {
			bank.setName(request.getName());
		}
		if (StringUtils.isNotBlank(request.getCode())) {
			bank.setCode(request.getCode());
		}
		bankRepository.update(bank);
		return response;
	}

	public GenericRS activate(String bankId) {
		GenericRS response = new GenericRS();
		if (bankRepository.activate(bankId) > 0) {
			return response;
		}
		return response.setError(MessageError.buildBankActivate(bankId));
	}

	public GenericRS deactivated(String bankId) {
		GenericRS response = new GenericRS();
		if (bankRepository.deactivate(bankId) > 0) {
			return response;
		}
		return response.setError(MessageError.buildBankDeactivate(bankId));
	}

	public GetBankListRS getBankList() {
		GetBankListRS response = new GetBankListRS();

		List<Bank> banks = bankRepository.getAll();
		if (banks.isEmpty()) {
			return response.setError(MessageError.buildBankEmpty());
		}
		return response.setBanks(banks);
	}

	public GetBankRS getBank(String bankId) {
		GetBankRS response = new GetBankRS();
		Bank bank = bankRepository.get(bankId);
		if (bank == null) {
			return response.setError(MessageError.buildBankNotFound(bankId));
		}
		response.setCode(bank.getCode());
		response.setId(bank.getId());
		response.setName(bank.getName());
		response.setStatus(bank.getStatus());
		return response;
	}

	public GenericRS delete(String id) {
		GenericRS response = new GenericRS();
		int countResult = bankRepository.delete(id);
		if (countResult < 1) {
			return response.setError(MessageError.buildBankEmpty());
		}
		return response;
	}

	private Bank buildBank(CreateBankRQ request, String bankId) {
		Bank newBank = new Bank();
		newBank.setId(bankId);
		newBank.setCode(request.getCode());
		newBank.setName(request.getName());
		newBank.setStatus(Boolean.TRUE);
		return newBank;
	}
}
