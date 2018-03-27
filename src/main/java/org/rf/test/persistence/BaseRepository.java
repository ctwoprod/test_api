package org.rf.test.persistence;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.logging.SLF4JLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.function.Function;

public class BaseRepository<T extends BaseIF> {
    private static final Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    protected DBI dbi;
    public BaseRepository() {}
    public BaseRepository(DataSource dataSource) {
        this.dbi = new DBI(dataSource);
        this.dbi.setSQLLog(new SLF4JLog(LoggerFactory.getLogger(DBI.class), SLF4JLog.Level.DEBUG));
    }

    protected <R> R execute(Class<T> dbiInterface, Function<T, R> sql) {
        T dao = this.dbi.open(dbiInterface);
        try {
            return sql.apply(dao);
        } catch (UnableToExecuteStatementException ex) {
            logger.error("failed execute query", ex);
            throw ex;
        } finally {
            dao.close();
        }
    }

    public DBI getDbi() {
        return this.dbi;
    }
}
