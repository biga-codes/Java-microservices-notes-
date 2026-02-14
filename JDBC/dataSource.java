com.dbaccess.BasicDataSource ds = new com.dbaccess.BasicDataSource();
ds.setServerName("grinder");
ds.setDatabaseName("CUSTOMER_ACCOUNTS");
ds.setDescription("Customer accounts database for billing");

DataSource.getConnection to get a connection to the company's database, CUSTOMER_ACCOUNTS
  The variable ds now represents the database CUSTOMER_ACCOUNTS installed on the server.
  Any connection produced by the BasicDataSource object ds will be a connection to the database CUSTOMER_ACCOUNTS.

  After a basic DataSource implementation is deployed by a system administrator,
  it is ready for a programmer to use. This means that a programmer can give the logical data source name that was 
  bound to an instance of a DataSource class, 
  and the JNDI naming service will return an instance of that DataSource class

Connection con = ds.getConnection("fernanda","brewed");

The getConnection method requires only the user name and password because the variable ds has the rest of the information
