package org.rf.test.service;

import java.util.UUID;

import javax.inject.Inject;

import org.rf.test.engine.TransactionEngine;
import org.rf.test.model.Bank;
import org.rf.test.model.Branch;
import org.rf.test.ws.GenericRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestService {
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);

	private TransactionEngine transactionEngine;

	@Inject
	public TestService(TransactionEngine transactionEngine) {
		this.transactionEngine = transactionEngine;
	}

	/*
	 * test transaction
	 * */
	public GenericRS test() {
		String bankId = UUID.randomUUID().toString();
		String branchId = UUID.randomUUID().toString();
		Bank newBank = buildBank(bankId);
		Branch newBranch = buildBranch(branchId);
		this.transactionEngine.test(newBank, newBranch);
		return new GenericRS();
	}

	private Bank buildBank(String bankId) {
		Bank newBank = new Bank();
		newBank.setId("bankId2");
		newBank.setCode("request.getCode()");
		newBank.setName("request.getName()");
		newBank.setStatus(false);
		return newBank;
	}

	private Branch buildBranch(String id) {
		Branch newBank = new Branch();
		newBank.setId(id);
		// newBank.setCode("b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()b+request.getCode()");
		newBank.setCode("122");
		newBank.setName("b+request.getName()");
		newBank.setStatus(true);
		return newBank;
	}
}
