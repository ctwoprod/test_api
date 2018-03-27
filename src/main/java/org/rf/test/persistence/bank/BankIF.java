package org.rf.test.persistence.bank;

import java.util.List;

import org.rf.test.model.Bank;
import org.rf.test.persistence.BaseIF;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(BankMapper.class)
public interface BankIF extends BaseIF {
	@SqlUpdate("insert into BANK (id, name, code, status) " + "values (:id, :name, :code, :status)")
	int insert(@BindBean Bank bank);

	@SqlQuery("SELECT id, code, name, status FROM bank WHERE status = true")
	List<Bank> getAll();

	@SqlQuery("SELECT id, code, name, status FROM bank WHERE id = :id")
	Bank get(@Bind("id") String bankId);

	@SqlUpdate("UPDATE bank SET status = TRUE WHERE id = :id")
	int activate(@Bind("id") String bankId);

	@SqlUpdate("UPDATE bank SET status = FALSE WHERE id = :id")
	int deactivate(@Bind("id") String bankId);

	@SqlUpdate("UPDATE bank SET " + "code = COALESCE(:code, code), " + "name = COALESCE(:name, name) "
			+ "WHERE id = :id")
	int update(@BindBean Bank bank);
	
	@SqlUpdate("DELETE FROM bank WHERE id = :id")
	int delete(@Bind("id") String bankId);
}
