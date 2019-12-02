package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
  //TODO: Write our URL
  private static final String DB_URL = "jdbc:postgres://raja.db.elephantsql.com:5432/rqacvejn ";
//  private static final String DB_URL = "jdbc:postgresql://localhost:5432/ibatech";
//  private static final String DB_URLM = "jdbc:mysql://localhost:3306/logistics";
  private static final String USERNAME = "rqacvejn";
  private static final String USER_PASS = "pyZWYiqIttQCOkgKM44e-eDWU_ntueVG";

  private static Connection connection;

  private DbConnection() {}

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
      } catch (SQLException e) {
        throw new RuntimeException("Something went wrong during connection", e);
      }
    }
    return connection;
  }
}
