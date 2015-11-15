package test.mahout.recommend;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.impl.model.MySQLJDBCIDMigrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import camp.mahout.Application;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MySQLJDBCIDMigratorTest {
	
	@Autowired
	private DataSource dataSource;

	@Test
	public void dbIDMigratorTest() throws Exception {
		MySQLJDBCIDMigrator idMigrator = new MySQLJDBCIDMigrator(dataSource);
		idMigrator.storeMapping(1l, "a");
		
		String stringID = idMigrator.toStringID(1l);
		log.info("# stringID - {}", stringID);
		
		assertThat(stringID, is("a"));
	}
	
}
