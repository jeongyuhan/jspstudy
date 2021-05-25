package db.util;

public interface DBConfig {
	
	public final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // oracle.jdbc.OracleDriver (Driver를 제외해도 괜찮다.)
	public final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final String USER = "spring";
	public final String PASSWORD = "1111";
	
	
}
