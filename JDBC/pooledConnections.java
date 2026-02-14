The ConnectionPoolDataSource object must be deployed first. The following code creates an instance of com.dbaccess.ConnectionPoolDS and sets its properties:

com.dbaccess.ConnectionPoolDS cpds = new com.dbaccess.ConnectionPoolDS();
cpds.setServerName("creamer");
cpds.setDatabaseName("COFFEEBREAK");
cpds.setPortNumber(9040);
cpds.setDescription("Connection pooling for " + "COFFEEBREAK DBMS");

Oracle recommends putting all ConnectionPoolDataSource objects under the subcontext jdbc/pool:

Context ctx = new InitialContext();
ctx.bind("jdbc/pool/fastCoffeeDB", cpds);


com.applogic.PooledDataSource ds = new com.applogic.PooledDataSource();
ds.setDescription("produces pooled connections to COFFEEBREAK");
ds.setDataSourceName("jdbc/pool/fastCoffeeDB");
Context ctx = new InitialContext();
ctx.bind("jdbc/fastCoffeeDB", ds);

import java.sql.*;
import javax.sql.*;
import javax.ejb.*;
import javax.naming.*;

public class ConnectionPoolingBean implements SessionBean {

    public void ejbCreate() throws CreateException {
        ctx = new InitialContext();
        ds = (DataSource)ctx.lookup("jdbc/fastCoffeeDB");
    }

    public void updatePrice(float price, String cofName,
                            String username, String password)
        throws SQLException{

        Connection con;
        PreparedStatement pstmt;
        try {
            con = ds.getConnection(username, password);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE COFFEES " +
                        "SET PRICE = ? " +
                        "WHERE COF_NAME = ?");
            pstmt.setFloat(1, price);
            pstmt.setString(2, cofName);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();

        } finally {
            if (con != null) con.close();
        }
    }

    private DataSource ds = null;
    private Context ctx = null;
}



Use a DataSource object rather than the DriverManager class to get a connection. 
    ds is a DataSource object implemented and deployed so that it will create pooled connections and username and password are variables that represent the credentials of the user that has access to the database:

Connection con = ds.getConnection(username, password);
Use a finally statement to close a pooled connection. The following finally block would appear after the try/catch block that applies to the code in which the pooled connection was used:

try {
    Connection con = ds.getConnection(username, password);
    // ... code to use the pooled
    // connection con
} catch (Exception ex {
    // ... code to handle exceptions
} finally {
    if (con != null) con.close();
}
Otherwise, an application using a pooled connection is identical to an application using a regular connection.
