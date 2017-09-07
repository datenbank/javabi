package javadw.model;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;

public class Report {

	private String name;
	private String displayName;
	private String folder;
	private String group;
	
	private Connection connection;
	private HashMap<String, Object> subDataSources = new HashMap<String, Object>();
	
	private HashMap<String, Object> parameters = new HashMap<String, Object>();

	private JasperPrint jasperPrint;
	
	public Report(String name, String folder, String group, String displayName) {
		
		this.name = name;
		this.folder = folder;
		this.group = group;
		this.displayName = displayName;
	}
	
	
	public String getName() {
		return name;
	}
	public String getFolder() {
		return folder;
	}
	public String getGroup() {
		return group;
	}
	public String getDisplayName() {
		return displayName;
	}
	public HashMap<String, Object> getSubDataSources() {
		return subDataSources;
	}	
	public HashMap<String, Object> getParameters() {
		return parameters;
	}
	public Connection getConnection() {
		return connection;
	}
	
	public void compile() throws JRException {

		if(!new File(folder+""+name+".jasper").exists()) {
			JasperCompileManager
					.compileReportToFile(folder+""+name+".jrxml".toString()
					, folder+""+name+".jasper".toString());
		}
	}


	public void printReport(HashMap<String, Object> params) throws JRException {
		
		Iterator<Entry<String, Object>> it = getSubDataSources().entrySet().iterator();
	    
		while (it.hasNext()) {
	        Map.Entry<String, Object> pair = (Map.Entry<String, Object>)it.next();	        
	        params.put(pair.getKey().toString(), pair.getValue());
	        
	    }			
		
		jasperPrint = (JasperPrint) JasperFillManager
				.fillReport(folder+""+name+".jasper".toString()
				, params
				, (Connection) connection);
		
		
	}

	public String export(String type) throws JRException {
		long current = System.currentTimeMillis();
		
		if(type.equals("pdf"))
			JasperExportManager.exportReportToPdfFile(jasperPrint, folder+""+name+"-"+Long.toString(current).toString()+".pdf");
		else 
			JasperExportManager.exportReportToHtmlFile(jasperPrint, folder+""+name+"-"+Long.toString(current).toString()+".html");
		return folder+""+name+"-"+Long.toString(current)+"."+type;
	}
}
