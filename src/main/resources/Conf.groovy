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

	jdbc1 {
		driver = "net.sourceforge.jtds.jdbc.Driver"
		url = 'jdbc:jtds:sqlserver://localhost:1433/Benchmark;instance=SQLEXPRESS'
		usr = "benchmark"
		pwd = "benchmark"
		
	}
	jdbc2 {
		driver = "net.sourceforge.jtds.jdbc.Driver"
		url = 'jdbc:jtds:sqlserver://localhost:1433/Benchmark;instance=SQLEXPRESS'
		usr = "benchmark"
		pwd = "benchmark"
		
	}

}

context  = ""