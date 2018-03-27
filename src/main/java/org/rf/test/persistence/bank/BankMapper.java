package org.rf.test.persistence.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.rf.test.model.Bank;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class BankMapper implements ResultSetMapper<Bank> {

    @Override
    public Bank map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Bank(r.getString("id"),
                r.getString("code"),
                r.getString("name"),
                r.getBoolean("status"));
    }
}
