package javadw.presenter;

import javadw.model.Report;
import groovy.sql.Sql

class Init {
	static dataSources = [:]
	static List<Report> reports;

	static String context = ""

	static init(String folder) {
		reports = new ArrayList<Report>()
		def config = new ConfigSlurper().parse(new File("${folder}Conf.groovy").text)

		context = config.context

		config.dataSources.each {
			try {
				def sql = Sql.newInstance(
						config.dataSources."$it.key".url
						, config.dataSources."$it.key".usr
						, config.dataSources."$it.key".pwd
						, config.dataSources."$it.key".driver)


				dataSources.putAt("$it.key", sql.connection)
			} catch(all) {
			}
		}

		config.reports.each {

			def r = new Report(it.key, config.reports."$it.key".folder, config.reports."$it.key".group, , config.reports."$it.key".displayName)
			r.connection = dataSources.getAt(config.reports."$it.key".dataSource)
			r.parameters = config.reports."$it.key".parameters

			config.reports."$it.key".subDataSources.each { subds ->
				r.subDataSources.put(subds, dataSources.getAt(subds))
			}
			reports << r
		}
	}

	static List<Report> getReports() {
		return reports
	}

	static Report getReport(String name) {
		def report
		reports.each {

			if(it.name == name)
				report = it
		}
		return report
	}
}
