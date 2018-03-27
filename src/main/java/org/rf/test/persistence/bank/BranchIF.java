package org.rf.test.persistence.bank;

import java.util.List;

import org.rf.test.model.Branch;
import org.rf.test.persistence.BaseIF;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(BranchMapper.class)
public interface BranchIF extends BaseIF {
	@SqlUpdate("insert into branch (id, name, code, status) " + "values (:id, :name, :code, :status)")
	int insert(@BindBean Branch bank);

	@SqlQuery("SELECT id, code, name, status FROM bank WHERE status = true")
	List<Branch> getAll();
	
	@SqlQuery("SELECT id, code, name, status FROM bank WHERE id = :id")
	Branch get(@Bind("id") String bankId);

	@SqlQuery("SELECT id, name, code, status FROM bank " + "WHERE code = :code")
	Branch getByCode(@Bind("code") String code);

	@SqlQuery("SELECT id, name, code, status FROM bank " + "WHERE name = :name")
	Branch getByName(@Bind("name") String name);

	@SqlUpdate("UPDATE bank SET code = :code WHERE id = :id")
	int updateCode(@Bind("id") String bankId, @Bind("code") String code);

	@SqlUpdate("UPDATE bank SET name = :name WHERE id = :id")
	int updateName(@Bind("id") String bankId, @Bind("name") String name);

	@SqlUpdate("UPDATE bank SET status = TRUE WHERE id = :id")
	int activate(@Bind("id") String bankId);

	@SqlUpdate("UPDATE bank SET status = FALSE WHERE id = :id")
	int deactivate(@Bind("id") String bankId);
}
