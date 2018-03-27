package org.rf.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.rf.test.config.AppConfig;
import org.rf.test.model.Bank;
import org.rf.test.persistence.bank.BankRepository;
import org.rf.test.ws.GenericRS;
import org.rf.test.ws.bank.CreateBankRQ;
import org.rf.test.ws.bank.CreateBankRS;
import org.rf.test.ws.bank.GetBankListRS;
import org.rf.test.ws.bank.GetBankRS;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {
	private static final String BANK_ID = "bankId";
	private static final String BANK_CODE = "bankCode";
	private static final String BANK_NAME = "bankName";
	private static final Boolean BANK_STATUS = true;

	@Mock
	private BankRepository bankRepository;
	@Mock
	private AppConfig appConfig;

	@InjectMocks
	private BankService target;

	@Test
	public void testGetAllSupportedDeviceShouldSuccess() {
		Bank b1 = new Bank("id1", "name 1", "code 1", true);
		Bank b2 = new Bank("id2", "name 2", "code 2", true);
		List<Bank> banks = new ArrayList<Bank>();
		banks.add(b1);
		banks.add(b2);

		when(bankRepository.getAll()).thenReturn(banks);

		GetBankListRS actual = target.getBankList();

		assertThat(actual.getError()).isNull();
		assertThat(actual.getBanks()).isEqualTo(banks);
	}

	@Test
	public void testGetById() {
		Bank bank = defaultBank();
		when(bankRepository.get(BANK_ID)).thenReturn(bank);

		GetBankRS actual = target.getBank(BANK_ID);

		assertThat(actual.getName()).isEqualTo(BANK_NAME);
		assertThat(actual.getCode()).isEqualTo(BANK_CODE);
		assertThat(actual.getError()).isNull();
	}

	@Test
	public void testUpdateBankSuccess() {
		CreateBankRQ request = new CreateBankRQ();
		Bank bank = defaultBank();
		ArgumentCaptor<Bank> captor = ArgumentCaptor.forClass(Bank.class);
		when(bankRepository.get(BANK_ID)).thenReturn(bank);
		when(bankRepository.update(captor.capture())).thenReturn(1);

		GenericRS actual = target.update(BANK_ID, request);

		assertThat(actual.getError()).isNull();
		assertThat(captor.getValue().getId()).isEqualTo(BANK_ID);
		assertThat(captor.getValue().getName()).isEqualTo(BANK_NAME);
		assertThat(captor.getValue().getCode()).isEqualTo(BANK_CODE);
	}

	@Test
	public void testDeleteBankReturnSuccess() {
		when(bankRepository.delete(BANK_ID)).thenReturn(1);
		GenericRS actual = target.delete(BANK_ID);
		assertThat(actual.getError()).isNull();
	}

	@Test
	public void testCreateBankSuccess() {
		CreateBankRQ request = new CreateBankRQ();
		request.setCode(BANK_CODE);
		request.setName(BANK_NAME);
		ArgumentCaptor<Bank> bankCaptor = ArgumentCaptor.forClass(Bank.class);
		when(bankRepository.insert(bankCaptor.capture())).thenReturn(1);

		CreateBankRS actual = target.create(request);
		Bank capturedValue = bankCaptor.getValue();
		assertThat(capturedValue.getId()).isEqualTo(actual.getBankId());
		assertThat(capturedValue.getName()).isEqualTo(BANK_NAME);
		assertThat(capturedValue.getCode()).isEqualTo(BANK_CODE);
	}

	private Bank defaultBank() {
		return new Bank(BANK_ID, BANK_CODE, BANK_NAME, BANK_STATUS);
	}

}