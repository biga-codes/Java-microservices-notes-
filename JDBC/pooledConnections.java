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
