package org.rf.test.persistence.bank;

import java.util.List;

import javax.sql.DataSource;

import org.rf.test.model.Branch;
import org.rf.test.persistence.BaseRepository;

import com.google.inject.Inject;

public class BranchRepository extends BaseRepository<BranchIF> {

	@Inject
	public BranchRepository(DataSource dataSource) {
		super(dataSource);
	}

	public int insert(Branch bank) {
		return this.execute(BranchIF.class, bankIf -> bankIf.insert(bank));
	}

	public List<Branch> getAll() {
		return this.execute(BranchIF.class, bankIF -> bankIF.getAll());
	}

	public Branch get(String bankId) {
		return this.execute(BranchIF.class, bankIF -> bankIF.get(bankId));
	}

	public Branch getByCode(String code) {
		return this.execute(BranchIF.class, bankIF -> bankIF.getByCode(code));
	}

	public Branch getByName(String name) {
		return this.execute(BranchIF.class, bankIF -> bankIF.getByName(name));
	}

	public int updateCode(String bankId, String code) {
		return this.execute(BranchIF.class, bankIF -> bankIF.updateCode(bankId, code));
	}

	public int updateName(String bankId, String name) {
		return this.execute(BranchIF.class, bankIF -> bankIF.updateName(bankId, name));
	}

	public int activate(String bankId) {
		return this.execute(BranchIF.class, bankIF -> bankIF.activate(bankId));
	}

	public int deactivate(String bankId) {
		return this.execute(BranchIF.class, bankIF -> bankIF.deactivate(bankId));
	}

}
