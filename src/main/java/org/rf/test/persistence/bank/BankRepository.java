package org.rf.test.persistence.bank;

import java.util.List;

import javax.sql.DataSource;

import org.rf.test.model.Bank;
import org.rf.test.persistence.BaseRepository;

import com.google.inject.Inject;

public class BankRepository extends BaseRepository<BankIF> {

	@Inject
	public BankRepository(DataSource dataSource) {
		super(dataSource);
	}

	public int insert(Bank bank) {
		return this.execute(BankIF.class, bankIf -> bankIf.insert(bank));
	}

	public List<Bank> getAll() {
		return this.execute(BankIF.class, bankIF -> bankIF.getAll());
	}

	public Bank get(String bankId) {
		return this.execute(BankIF.class, bankIF -> bankIF.get(bankId));
	}

	public int activate(String bankId) {
		return this.execute(BankIF.class, bankIF -> bankIF.activate(bankId));
	}

	public int deactivate(String bankId) {
		return this.execute(BankIF.class, bankIF -> bankIF.deactivate(bankId));
	}

	public int update(Bank bank) {
		return this.execute(BankIF.class, bankIF -> bankIF.update(bank));
	}

	public int delete(String id) {
		return this.execute(BankIF.class, bankIF -> bankIF.delete(id));
	}
}
