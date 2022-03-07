package sailingclub.server.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.BankTransfer;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Race;
import sailingclub.server.sqlmanagment.DatabaseManager;
import sailingclub.server.sqlmanagment.SQLTranslator;

/**
 * this class tests the server, the test consist
 * on the validation of the translation of the requests and responses
 * form/to SQL
 */
public class SQLTranslatorStandardTest {
	private SQLTranslator translator;
	
	/**
	 * initialize the test suite
	 */
	@Before
	public void initialize() {
		this.translator = new SQLTranslator();
	}
	
	
	/**
	 * tests all the sql conversion from requests
	 */
	@Test
	public void testSQLConversion() {
		Request selectReq = new Request(Constants.GET_ALL_BOATS, new EmptyPayload());
		Request insertReq = new Request(Constants.INSERT, new BankTransfer("12345", "intesa", "usertest"));
		Request updateReq = new Request(Constants.UPDATE_RACE, new Race(16,LocalDate.now(), 55.6, "TestRace"));
		
		String selRegex = "^(\s*?)SELECT\s*?.*?\s*?FROM([\s]|[^;]|(['\"].*;.*['\"]))*?;\s*?$";
		String insRegex = "(INSERT INTO) (\\S+).*\\((.*?)\\).*(VALUES).*\\((.*?)\\)(.*\\;?)";
		String updRegex = "(?i)^UPDATE .*SET .*";
		
		Pattern selPattern = Pattern.compile(selRegex);
		Pattern insPattern = Pattern.compile(insRegex);
		Pattern updPattern = Pattern.compile(updRegex);
		
		Matcher seletcMatcher = null;
		Matcher insertMatcher = null;
		Matcher updateMatcher = null;
		
		try {
			String selectSql = this.translator.requestToSql(selectReq);
			seletcMatcher = selPattern.matcher(selectSql);
			
			String insertSql = this.translator.requestToSql(insertReq).split(";")[0] + ";";
			insertMatcher = insPattern.matcher(insertSql);
			
			String updateSql = this.translator.requestToSql(updateReq);
			System.out.print(updateSql);
			updateMatcher = updPattern.matcher(updateSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(insertMatcher.matches());
		assertTrue(seletcMatcher.matches());
		assertTrue(updateMatcher.matches());
	}
	
	
	/**
	 * tests the response parsing from db result
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testResponseConversion() {
		try {
			String sql = this.translator.requestToSql(new Request(Constants.GET_ALL_BOATS, new EmptyPayload()));
			DatabaseManager dbm = new DatabaseManager("root", "");
			Response r = this.translator.sqlToResponse(dbm.executeSQLStatement(sql));
			assertEquals(r.getStatusCode(), Constants.SUCCESS);
			assertTrue((ArrayList<Boat>)r.getPayload() instanceof ArrayList<Boat>);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
