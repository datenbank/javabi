reports {
	Report1 {
		displayName = "Report One"
		folder = "C:\\reports\\"
		group = "Demo"
		dataSource = "jdbc1"
		parameters = [Parameter1: "Hello World"]
		subDataSources = ["jdbc2"]
	}
	Report2 {
		displayName = "Report Two"
		folder = "C:\\reports\\"
		group = "Demo"
		dataSource = "jdbc1"
		parameters = [Parameter1: "Hello World2"]
		subDataSources = ["jdbc2"]
	}
}

dataSources {

	java.net.URI dbUri = new java.net.URI(System.getenv("DATABASE_URL"));
	
	String username = dbUri.getUserInfo().split(":")[0];
	String password = dbUri.getUserInfo().split(":")[1];
	String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	
	
	jdbc1 {
		driver = "org.postgresql.Driver"
		url = dbUrl
		usr = username
		pwd = password
		
	}
	jdbc2 {
		driver = "net.sourceforge.jtds.jdbc.Driver"
		url = 'jdbc:jtds:sqlserver://localhost:1433/Benchmark;instance=SQLEXPRESS'
		usr = "benchmark"
		pwd = "benchmark"
		
	}

}

context  = ""