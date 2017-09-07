package javadw.presenter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;

import javadw.model.Report;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

@WebServlet(asyncSupported = false, urlPatterns = { "/report/*" })
public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Report report = Init.getReport(request.getParameter("report"));
		String type = request.getParameter("type");
		String file = "";

		HashMap<String, Object> params = new HashMap<String, Object>();

		Enumeration<String> attrs = request.getParameterNames();
		while (attrs.hasMoreElements()) {
			String key = attrs.nextElement().toString();
			params.put(key, request.getParameter(key));
		}
		
		if(report == null || type == null) {
			throw new IOException("Report or type not set.");
		}
		
		
		try {
			report.compile();
			report.printReport(params);
			file = report.export(type);
		} catch (JRException e) {
			throw new IOException(e);
		}

		OutputStream out = response.getOutputStream();
		
		if(type.equals("pdf")) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename="
			 + report.getName() + ".pdf");
		}
		else {
			response.setContentType("text/html");
		}		
		Path path = new File(file).toPath();
		Files.copy(path, out);
		out.flush();

		out.close();

	}
}
