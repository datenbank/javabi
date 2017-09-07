package javadw.presenter;

import java.util.List;

import javadw.model.Report;
import javadw.presenter.interfaces.IReportRunnable;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;


public class ReportRunButtonAction implements  Button.ClickListener {

	private static final long serialVersionUID = 1L;
	
	private IReportRunnable view;
	private Report report;
	
	public ReportRunButtonAction(IReportRunnable view, Report report) {
		this.view = view;
		this.report = report;
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		List<String[]> params = view.getParameters();
		StringBuilder sb = new StringBuilder("?");
		for(String[] tf : params) {
			sb.append(tf[0]);
			sb.append("=");
			sb.append(tf[1]);
			sb.append("&");
		}
		
		sb.append("report=").append(report.getName());
		sb.append("&type=").append(view.getPdfChecked() ? "pdf" : "html");
		view.setLocation(Init.getContext()+"/report"+sb.toString());
		
	}

}
