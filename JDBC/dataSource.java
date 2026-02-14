com.dbaccess.BasicDataSource ds = new com.dbaccess.BasicDataSource();
ds.setServerName("grinder");
ds.setDatabaseName("CUSTOMER_ACCOUNTS");
ds.setDescription("Customer accounts database for billing");

DataSource.getConnection to get a connection to the company's database, CUSTOMER_ACCOUNTS
  The variable ds now represents the database CUSTOMER_ACCOUNTS installed on the server.
  Any connection produced by the BasicDataSource object ds will be a connection to the database CUSTOMER_ACCOUNTS.
