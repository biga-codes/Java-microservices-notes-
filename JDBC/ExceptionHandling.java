BatchUpdateException is thrown when an error occurs during a batch update operation. 
SQLClientInfoException is thrown when one or more client information properties could not be set on a Connection
The most common warning is a DataTruncation warning, a subclass of SQLWarning. All DataTruncation objects have a SQLState of 01004, 
indicating that there was a problem with reading or writing data. 
DataTruncation methods let you find out in which column or parameter data was truncated, 
whether the truncation was on a read or write operation, how many bytes should have been transferred,
and how many bytes were actually transferred.

public static void getWarningsFromResultSet(ResultSet rs)
    throws SQLException {
    JDBCTutorialUtilities.printWarnings(rs.getWarnings());
}

public static void getWarningsFromStatement(Statement stmt)
    throws SQLException {
    JDBCTutorialUtilities.printWarnings(stmt.getWarnings());
}

public static void printWarnings(SQLWarning warning)
    throws SQLException {

    if (warning != null) {
        System.out.println("\n---Warning---\n");

    while (warning != null) {
        System.out.println("Message: " + warning.getMessage());
        System.out.println("SQLState: " + warning.getSQLState());
        System.out.print("Vendor error code: ");
        System.out.println(warning.getErrorCode());
        System.out.println("");
        warning = warning.getNextWarning();
    }
}

  public static boolean ignoreSQLException(String sqlState) {
    if (sqlState == null) {
      System.out.println("The SQL state is not defined!");
      return false;
    }
    
    if (sqlState.equalsIgnoreCase("X0Y32"))
      return true;
    
    if (sqlState.equalsIgnoreCase("42Y55"))
      return true;
    return false;
  }

public static void printSQLException(SQLException ex) {
    for (Throwable e : ex) {
      if (e instanceof SQLException) {
        if (ignoreSQLException(((SQLException)e).getSQLState()) == false) {
          e.printStackTrace(System.err);
          System.err.println("SQLState: " + ((SQLException)e).getSQLState());
          System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
          System.err.println("Message: " + e.getMessage());
          Throwable t = ex.getCause();
          while (t != null) {
            System.out.println("Cause: " + t);
            t = t.getCause();
          }
        }
      }
    }
  }

public static void alternatePrintSQLException(SQLException ex) {
    while (ex != null) {
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("Error Code: " + ex.getErrorCode());
      System.err.println("Message: " + ex.getMessage());
      Throwable t = ex.getCause();
      while (t != null) {
        System.out.println("Cause: " + t);
        t = t.getCause();
      }
      ex = ex.getNextException();
    }
  }


 Each of these classes has a getWarnings method, which you must invoke in order to see the first warning reported on the calling object.
 If getWarnings returns a warning, you can call the SQLWarning method getNextWarning on it to get any additional warnings. 
Executing a statement automatically clears the warnings from a previous statement, so they do not build up.
