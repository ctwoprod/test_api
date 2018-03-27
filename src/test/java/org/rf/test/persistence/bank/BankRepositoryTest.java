package org.rf.test.persistence.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.rf.test.config.AppConfig;
import org.rf.test.config.AppConfigFactory;
import org.rf.test.model.Bank;
import org.rf.test.persistence.BaseRepository;
import org.rf.test.persistence.HikariDataSourceFactory;
import org.skife.jdbi.v2.DBI;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

@RunWith(JUnit4.class)
public class BankRepositoryTest extends BaseRepository<BankIF> {

	private static final String BANK_ID = "id";
	private static final String BANK_CODE = "code";
	private static final String BANK_NAME = "name";
	private static final Boolean BANK_STATUS = true;
	private AppConfig appConfig = AppConfigFactory.getAppConfig();

	@Bind
	private DataSource dataSource;

	@Inject
	private BankRepository bankRepository;

	@Before
	public void setUp() {
		this.dataSource = HikariDataSourceFactory.getDataSource(appConfig);
		this.dbi = new DBI(dataSource);
		Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
	}

	@After
	public void cleanUp() {
		this.dbi.withHandle(handle -> handle.createStatement("TRUNCATE TABLE bank").execute());
	}

	@Test
	public void testInsertAndGetBank() {
		Bank bank = defaultBank();

		bankRepository.insert(bank);

		Bank actual = bankRepository.get(BANK_ID);
		assertThat(actual).isEqualToComparingFieldByField(bank);
	}

	@Test
	public void testUpdateBank() {
		Bank bank = defaultBank();
		bankRepository.insert(bank);

		bank.setName("New Name");
		bank.setCode("New Code");
		bankRepository.update(bank);

		Bank actual = bankRepository.get(BANK_ID);
		assertThat(actual.getName().equals("New Name")).isTrue();
		assertThat(actual.getCode().equals("New Code")).isTrue();
	}
	
	@Test
	public void testInsertAndDelete() {
		Bank bank = defaultBank();
		bankRepository.insert(bank);
		
		Integer result = bankRepository.delete(BANK_ID);
		assertThat(result == 1);
	}


	@Test
	public void testActivateBank() {
		bankRepository.insert(defaultBank());

		bankRepository.activate(BANK_ID);

		Bank actual = bankRepository.get(BANK_ID);
		assertThat(actual.getStatus()).isTrue();
	}

	@Test
	public void testDeactiveBank() {
		bankRepository.insert(defaultBank());

		bankRepository.deactivate(BANK_ID);

		Bank actual = bankRepository.get(BANK_ID);
		assertThat(actual.getStatus()).isFalse();
	}

	@Test
	public void testGetAll() {
		Bank bank = defaultBank();
		bankRepository.insert(bank);

		List<Bank> actual = bankRepository.getAll();
		assertThat(!actual.isEmpty());
	}

	private Bank defaultBank() {
		return new Bank(BANK_ID, BANK_CODE, BANK_NAME, BANK_STATUS);
	}
}