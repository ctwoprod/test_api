package org.rf.test.persistence.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.rf.test.model.Branch;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class BranchMapper implements ResultSetMapper<Branch> {

	@Override
	public Branch map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Branch(r.getString("id"), r.getString("code"), r.getString("name"), r.getBoolean("status"));
	}
}
