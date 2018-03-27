package org.rf.test.engine;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.rf.test.model.Bank;
import org.rf.test.model.Branch;
import org.rf.test.persistence.BaseRepository;
import org.rf.test.persistence.bank.BankIF;
import org.rf.test.persistence.bank.BranchIF;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.TransactionCallback;
import org.skife.jdbi.v2.TransactionStatus;

@SuppressWarnings("rawtypes")
public class TransactionEngine extends BaseRepository{

	@Inject
    public TransactionEngine(DataSource dataSource) {
		super(dataSource);
    }
	
	public void test(Bank bank, Branch branch){
    	getDbi().inTransaction(new TestTransaction(bank, branch));
    }
	
	private static class TestTransaction implements TransactionCallback<Void> {
		private Bank bank;
		private Branch branch;
		
		@Inject
	    public TestTransaction(Bank bank, Branch branch) {
			this.bank = bank;
			this.branch = branch;
	    }

		@Override
		public Void inTransaction(Handle handle, TransactionStatus status) throws Exception {
			BankIF bankIf = handle.attach(BankIF.class);
			BranchIF branchIf= handle.attach(BranchIF.class);
			bankIf.insert(bank);
			branchIf.insert(branch);
			return null;
		}
		
	}

}
