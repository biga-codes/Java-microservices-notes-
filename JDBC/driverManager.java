public Connection getConnection() throws SQLException {

    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", this.userName);
    connectionProps.put("password", this.password);

    if (this.dbms.equals("mysql")) {
        conn = DriverManager.getConnection(
                   "jdbc:" + this.dbms + "://" +
                   this.serverName +
                   ":" + this.portNumber + "/",
                   connectionProps);
    } else if (this.dbms.equals("derby")) {
        conn = DriverManager.getConnection(
                   "jdbc:" + this.dbms + ":" +
                   this.dbName +
                   ";create=true",
                   connectionProps); //appending the url for dbms connection
    }
    System.out.println("Connected to database");
    return conn;//returns the connection status
}
/* MySQL: jdbc:mysql://localhost:3306/, where localhost is the name of the server hosting your database, and 3306 is the port number.
   Java DB: jdbc:derby:testdb;create=true, where testdb is the name of the database to connect to, and create=true instructs the DBMS to create the database.*/
